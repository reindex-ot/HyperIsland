package com.example.hyperisland.xposed

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import de.robv.android.xposed.XposedBridge
import io.github.d4viddf.hyperisland_kit.HyperIslandNotification
import io.github.d4viddf.hyperisland_kit.HyperPicture
import io.github.d4viddf.hyperisland_kit.models.ImageTextInfoLeft
import io.github.d4viddf.hyperisland_kit.models.ImageTextInfoRight
import io.github.d4viddf.hyperisland_kit.models.PicInfo
import io.github.d4viddf.hyperisland_kit.models.TextInfo

/**
 * SystemUI 进程内超级岛发送调度器。
 *
 * ## 原理
 * HyperOS 会抑制前台应用自身发出的岛通知。将通知改由 SystemUI（system UID）发出，
 * 可绕过该限制。[GenericProgressHook] 在 SystemUI 进程初始化时调用 [register]，
 * 注册一个受权限保护的 BroadcastReceiver；HyperIsland 应用通过 [sendBroadcast]
 * 触发它，由此以 SystemUI 身份发出岛通知。
 *
 * ## 其他 Xposed 模块的使用方式
 * 若模块本身已运行在 SystemUI 进程，直接调用 [post]：
 * ```kotlin
 * IslandDispatcher.post(
 *     context,
 *     IslandRequest(title = "标题", content = "内容", iconPackage = pkg)
 * )
 * ```
 *
 * ## 跨进程使用方式（从任意应用）
 * ```kotlin
 * IslandDispatcher.sendBroadcast(
 *     context,
 *     IslandRequest(title = "标题", content = "内容")
 * )
 * ```
 */
object IslandDispatcher {

    /** 广播 Action，由 HyperIsland 应用发出，由 SystemUI 进程内 Receiver 接收。*/
    const val ACTION   = "com.example.hyperisland.ACTION_SHOW_ISLAND"

    /**
     * 广播发送方所需权限（signature 级）。
     * 只有与 HyperIsland 使用相同签名的应用才能获得此权限并触发 Receiver。
     */
    const val PERM     = "com.example.hyperisland.SEND_ISLAND"

    /** 默认通知 ID。固定 ID 保证同一时刻只有一条岛通知存在。*/
    const val NOTIF_ID = 0x48594944  // "HYID"

    private const val CHANNEL_ID   = "hyperisland_dispatcher"
    private const val CHANNEL_NAME = "HyperIsland 超级岛"
    private const val TAG          = "HyperIsland[Dispatcher]"

    @Volatile private var registered = false

    // ── 广播接收器（运行在 SystemUI 进程）────────────────────────────────────

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action != ACTION) return
            try {
                val request = IslandRequest.fromIntent(intent)
                XposedBridge.log("$TAG onReceive: title=${request.title}")
                post(context.applicationContext ?: context, request)
            } catch (e: Exception) {
                XposedBridge.log("$TAG onReceive error: ${e.message}")
            }
        }
    }

    // ── 初始化（由 GenericProgressHook 在 SystemUI 加载时调用）───────────────

    /**
     * 在 SystemUI 进程中注册广播接收器，应在 [GenericProgressHook.handleLoadPackage]
     * 成功 hook 后调用一次。重复调用安全（幂等）。
     */
    fun register(context: Context) {
        if (registered) return
        val appCtx = context.applicationContext ?: context
        createChannel(appCtx)

        val filter = IntentFilter(ACTION)
        // 要求发送方持有 PERM 权限（signature 级，仅 HyperIsland 自身可持有）。
        // Context.RECEIVER_EXPORTED 是 Android 13 新增的必填标志，低版本无此常量。
        if (Build.VERSION.SDK_INT >= 33) {
            appCtx.registerReceiver(receiver, filter, PERM, null, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("UnspecifiedRegisterReceiverFlag")
            appCtx.registerReceiver(receiver, filter, PERM, null)
        }
        registered = true
        XposedBridge.log("$TAG registered in pid=${android.os.Process.myPid()}")
    }

    // ── 公开 API ──────────────────────────────────────────────────────────────

    /**
     * [进程内直接调用]
     * 在 SystemUI 进程内立即发出岛通知。其他运行在 SystemUI 进程内的 Xposed 模块
     * 可直接调用，无需广播，效率更高。
     */
    fun post(context: Context, request: IslandRequest) {
        try {
            val nm = context.getSystemService(NotificationManager::class.java) ?: return
            createChannel(context)

            val appIcon = resolveIcon(context, request.iconPackage)

            val islandBuilder = HyperIslandNotification.Builder(
                context, "hyper_island_dispatch", request.title
            )

            // 大岛图标、小岛图标、焦点通知图标均使用应用自身图标
            islandBuilder.addPicture(HyperPicture("key_island_icon", appIcon))
            islandBuilder.addPicture(HyperPicture("key_focus_icon",  appIcon))

            islandBuilder.setIconTextInfo(
                picKey  = "key_focus_icon",
                title   = request.title,
                content = request.content,
            )
            islandBuilder.setIslandFirstFloat(request.firstFloat)
            islandBuilder.setEnableFloat(request.enableFloat)
            islandBuilder.setShowNotification(true)
            islandBuilder.setIslandConfig(timeout = request.timeoutSecs)

            // 小岛：仅图标
            islandBuilder.setSmallIsland("key_island_icon")

            // 大岛：左侧图标+标题，右侧内容
            islandBuilder.setBigIslandInfo(
                left = ImageTextInfoLeft(
                    type     = 1,
                    picInfo  = PicInfo(type = 1, pic = "key_island_icon"),
                    textInfo = TextInfo(title = request.title),
                ),
                right = ImageTextInfoRight(
                    type     = 2,
                    textInfo = TextInfo(title = request.content, narrowFont = true),
                ),
            )

            val resourceBundle = islandBuilder.buildResourceBundle()

            val notif = Notification.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(request.title)
                .setContentText(request.content)
                .setAutoCancel(true)
                .build()

            notif.extras.putAll(resourceBundle)
            flattenActionsToExtras(resourceBundle, notif.extras)
            notif.extras.putString(
                "miui.focus.param",
                fixTextButtonJson(islandBuilder.buildJsonParam())
            )

            // 先取消同 ID 的旧通知，让 HyperOS 视为全新通知并触发岛动画
            nm.cancel(request.notifId)
            nm.notify(request.notifId, notif)

            XposedBridge.log("$TAG posted: ${request.title} | ${request.content} | id=${request.notifId}")
        } catch (e: Exception) {
            XposedBridge.log("$TAG post error: ${e.message}")
        }
    }

    /**
     * [跨进程调用]
     * 从任意进程向 SystemUI 进程发送岛展示请求。
     * 调用方无需持有任何额外权限；权限验证由 SystemUI 侧的 Receiver 注册时指定。
     *
     * 要求：HyperIsland 应用已在 Manifest 声明 [PERM] 权限并 uses-permission。
     */
    fun sendBroadcast(context: Context, request: IslandRequest) {
        val intent = Intent(ACTION).apply {
            putExtras(request.toBundle())
        }
        // 不传 receiverPermission；安全性由 Receiver 注册时的 broadcastPermission 保证
        context.sendBroadcast(intent)
    }

    // ── 内部工具 ──────────────────────────────────────────────────────────────

    /**
     * 解析图标：优先使用 [iconPackage] 对应的应用启动图标（圆角处理），
     * 失败时降级为系统默认图标。
     */
    private fun resolveIcon(context: Context, iconPackage: String): Icon {
        val pkg = iconPackage.ifBlank { "com.example.hyperisland" }
        return try {
            InProcessController.getAppIcon(context, pkg)
                ?.toRounded(context)
                ?: fallbackIcon(context)
        } catch (_: Exception) {
            fallbackIcon(context)
        }
    }

    private fun fallbackIcon(context: Context): Icon =
        Icon.createWithResource(context, android.R.drawable.sym_def_app_icon)

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = context.getSystemService(NotificationManager::class.java) ?: return
        if (nm.getNotificationChannel(CHANNEL_ID) != null) return
        nm.createNotificationChannel(
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                setShowBadge(false)
            }
        )
    }

    /** 修正新库输出的 textButton JSON，将 "actionIntent" 字段替换为协议所需的 "action"。*/
    private fun fixTextButtonJson(jsonParam: String): String {
        return try {
            val json = org.json.JSONObject(jsonParam)
            val pv2  = json.optJSONObject("param_v2") ?: return jsonParam
            val btns = pv2.optJSONArray("textButton") ?: return jsonParam
            for (i in 0 until btns.length()) {
                val btn = btns.getJSONObject(i)
                val key = btn.optString("actionIntent").takeIf { it.isNotEmpty() } ?: continue
                btn.put("action", key)
                btn.remove("actionIntent")
                btn.remove("actionIntentType")
            }
            json.toString()
        } catch (_: Exception) { jsonParam }
    }

    /** 将 buildResourceBundle() 里嵌套的 "miui.focus.actions" 展开到 extras 顶层。*/
    private fun flattenActionsToExtras(resourceBundle: Bundle, extras: Bundle) {
        val nested = resourceBundle.getBundle("miui.focus.actions") ?: return
        for (key in nested.keySet()) {
            val action: Notification.Action? = if (Build.VERSION.SDK_INT >= 33)
                nested.getParcelable(key, Notification.Action::class.java)
            else
                @Suppress("DEPRECATION") nested.getParcelable(key)
            if (action != null) extras.putParcelable(key, action)
        }
    }
}
