package com.example.hyperisland.xposed

import android.content.Intent
import android.os.Bundle

/**
 * 超级岛展示请求，跨进程通过 Intent extras 传递。
 *
 * ### 两种使用方式
 * - **SystemUI 进程内（其他 Xposed 模块）**：
 *   ```kotlin
 *   IslandDispatcher.post(context, IslandRequest(title = "标题", content = "内容"))
 *   ```
 * - **外部进程（HyperIsland 应用或其他模块）**：
 *   ```kotlin
 *   IslandDispatcher.sendBroadcast(context, IslandRequest(title = "标题", content = "内容"))
 *   ```
 */
data class IslandRequest(
    /** 主标题（大岛左侧 / 焦点通知标题）*/
    val title: String,
    /** 副标题（大岛右侧 / 焦点通知内容）*/
    val content: String,
    /**
     * 用于显示的图标所属包名。
     * - 空字符串：使用 HyperIsland 自身图标
     * - 其他包名：使用指定应用的启动图标
     */
    val iconPackage: String = "",
    /** 通知 ID；相同 ID 的旧通知会先被取消以触发岛动画。*/
    val notifId: Int = IslandDispatcher.NOTIF_ID,
    /** 岛自动收起超时，单位秒，默认 5。*/
    val timeoutSecs: Int = 5,
    /** 首次弹出时是否自动展开大岛。*/
    val firstFloat: Boolean = true,
    /** 后续更新时是否自动展开大岛。*/
    val enableFloat: Boolean = true,
) {
    fun toBundle(): Bundle = Bundle().apply {
        putString(KEY_TITLE,       title)
        putString(KEY_CONTENT,     content)
        putString(KEY_ICON_PKG,    iconPackage)
        putInt(KEY_NOTIF_ID,       notifId)
        putInt(KEY_TIMEOUT,        timeoutSecs)
        putBoolean(KEY_FIRST_FLOAT, firstFloat)
        putBoolean(KEY_ENABLE_FLOAT, enableFloat)
    }

    companion object {
        private const val KEY_TITLE        = "title"
        private const val KEY_CONTENT      = "content"
        private const val KEY_ICON_PKG     = "iconPackage"
        private const val KEY_NOTIF_ID     = "notifId"
        private const val KEY_TIMEOUT      = "timeoutSecs"
        private const val KEY_FIRST_FLOAT  = "firstFloat"
        private const val KEY_ENABLE_FLOAT = "enableFloat"

        fun fromBundle(b: Bundle) = IslandRequest(
            title       = b.getString(KEY_TITLE, ""),
            content     = b.getString(KEY_CONTENT, ""),
            iconPackage = b.getString(KEY_ICON_PKG, ""),
            notifId     = b.getInt(KEY_NOTIF_ID, IslandDispatcher.NOTIF_ID),
            timeoutSecs = b.getInt(KEY_TIMEOUT, 5),
            firstFloat  = b.getBoolean(KEY_FIRST_FLOAT, true),
            enableFloat = b.getBoolean(KEY_ENABLE_FLOAT, true),
        )

        fun fromIntent(intent: Intent) = fromBundle(intent.extras ?: Bundle())
    }
}
