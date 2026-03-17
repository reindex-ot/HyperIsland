package com.example.hyperisland

import android.content.Context
import android.util.Log
import com.example.hyperisland.xposed.IslandDispatcher
import com.example.hyperisland.xposed.IslandRequest

/**
 * HyperIsland 应用侧超级岛发送入口。
 *
 * 通过 [IslandDispatcher.sendBroadcast] 将请求发往 SystemUI 进程，
 * 由 SystemUI（system UID）实际发出通知，绕过 HyperOS 对前台应用的岛抑制。
 *
 * 若模块未激活（SystemUI 侧 Receiver 未注册），广播会被静默丢弃。
 */
object HyperIslandHelper {
    private const val TAG = "HyperIslandHelper"

    /**
     * 发送超级岛通知。
     *
     * @param context  应用 Context
     * @param title    主标题（大岛左侧 / 焦点通知标题）
     * @param content  副标题（大岛右侧 / 焦点通知内容）
     */
    fun sendIslandNotification(
        context: Context,
        title: String,
        content: String,
    ) {
        try {
            IslandDispatcher.sendBroadcast(
                context,
                IslandRequest(
                    title       = title,
                    content     = content,
                    iconPackage = context.packageName,
                )
            )
            Log.d(TAG, "Island request sent: $title | $content")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send island request", e)
        }
    }
}
