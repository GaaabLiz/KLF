package io.gaaabliz.github.kliz.android.model

data class NotificChannelInfo(
    val channelId : String,
    val systemName : String,
    val name : String,
    val importance : Int = android.app.NotificationManager.IMPORTANCE_DEFAULT,
    val settingTextTitle : String? = null,
    val settingTextDescription : String? = null,
    val enabledLights : Boolean = true,
)
