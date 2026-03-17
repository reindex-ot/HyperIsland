package com.example.hyperisland

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.hyperisland.xposed.toRounded
import io.github.d4viddf.hyperisland_kit.HyperIslandNotification
import io.github.d4viddf.hyperisland_kit.HyperPicture
import io.github.d4viddf.hyperisland_kit.models.ImageTextInfoLeft
import io.github.d4viddf.hyperisland_kit.models.ImageTextInfoRight
import io.github.d4viddf.hyperisland_kit.models.PicInfo
import io.github.d4viddf.hyperisland_kit.models.TextInfo

object HyperIslandHelper {
    private const val TAG = "HyperIslandHelper"
    private const val CHANNEL_ID = "hyperisland_channel"
    private const val NOTIFICATION_ID = 1001

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "HyperIsland",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "小米超级岛测试通知"
                setShowBadge(true)
            }
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    /** 获取应用自身图标并转为圆角 Icon */
    private fun getAppIcon(context: Context): Icon {
        return try {
            val iconResId = context.packageManager
                .getApplicationInfo(context.packageName, 0).icon
            Icon.createWithResource(context, iconResId).toRounded(context)
        } catch (_: Exception) {
            Icon.createWithResource(context, android.R.drawable.sym_def_app_icon)
        }
    }

    fun sendIslandNotification(
        context: Context,
        title: String,
        content: String,
    ): Boolean {
        return try {
            createNotificationChannel(context)
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val pendingIntent = PendingIntent.getActivity(
                context, 0,
                Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val appIcon = getAppIcon(context)

            val islandBuilder = HyperIslandNotification.Builder(context, "hyper_island_test", title)

            // 大小岛图标 & 焦点通知图标均使用应用自身图标
            islandBuilder.addPicture(HyperPicture("key_island_icon", appIcon))
            islandBuilder.addPicture(HyperPicture("key_focus_icon", appIcon))

            islandBuilder.setIconTextInfo(
                picKey  = "key_focus_icon",
                title   = title,
                content = content,
            )

            islandBuilder.setIslandFirstFloat(true)
            islandBuilder.setEnableFloat(true)
            islandBuilder.setShowNotification(true)
            islandBuilder.setIslandConfig(timeout = 5)

            // 小岛：仅图标
            islandBuilder.setSmallIsland("key_island_icon")

            // 大岛：左侧图标+标题，右侧内容
            islandBuilder.setBigIslandInfo(
                left = ImageTextInfoLeft(
                    type     = 1,
                    picInfo  = PicInfo(type = 1, pic = "key_island_icon"),
                    textInfo = TextInfo(title = title),
                ),
                right = ImageTextInfoRight(
                    type     = 2,
                    textInfo = TextInfo(title = content, narrowFont = true),
                ),
            )

            val resourceBundle = islandBuilder.buildResourceBundle()

            val notifBuilder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
                setSmallIcon(
                    context.packageManager.getApplicationInfo(context.packageName, 0).icon
                )
                setContentTitle(title)
                setContentText(content)
                setAutoCancel(true)
                setContentIntent(pendingIntent)
                addExtras(resourceBundle)
            }

            val notification = notifBuilder.build()
            notification.extras.putAll(resourceBundle)
            flattenActionsToExtras(resourceBundle, notification.extras)
            notification.extras.putString(
                "miui.focus.param",
                fixTextButtonJson(islandBuilder.buildJsonParam())
            )

            // 先取消旧通知，让 HyperOS 将下一次 notify 视为全新通知以触发岛动画
            notificationManager.cancel(NOTIFICATION_ID)
            notificationManager.notify(NOTIFICATION_ID, notification)

            Log.d(TAG, "Island notification sent: $title | $content")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Error sending island notification", e)
            false
        }
    }

    /**
     * 将 textButton 数组里新库输出的 "actionIntent"+"actionIntentType"
     * 替换为 HyperOS V3 协议所需的 "action" 字段，否则按钮点击无响应。
     */
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

    /** 将 buildResourceBundle() 里嵌套的 "miui.focus.actions" 展开到 extras 顶层 */
    private fun flattenActionsToExtras(resourceBundle: android.os.Bundle, extras: android.os.Bundle) {
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
