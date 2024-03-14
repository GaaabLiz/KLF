package io.github.gaaabliz.kliz.firebase.android

import android.app.NotificationManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/*
abstract class BaseFcmService(
    private val logTag: String
) : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        addWarnLog(logTag, "NEW TOKEN RECEIVED: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        /* Controllo se messaggio contiene payload */
        addDebugLog(logTag, "Nuovo messaggio FCM da: \"${remoteMessage.from}\".")
        if (remoteMessage.data.isNotEmpty()) {
            addDebugLog(logTag,"Nuovo messaggio FCM contiene payload: \"${remoteMessage.data}\".")
        } else {
            addDebugLog(logTag,"Nuovo messaggio FCM non contiene payload: \"${remoteMessage.data}\".")
        }

        /* controllo preferenze */
        val notOk = true

        /* controllo se a msg è associata notifica */
        if(remoteMessage.notification != null && notOk) {

            /* prendo informazioni allegate a notifica e stampo log */
            val not = remoteMessage.notification!!
            val typeNotification = remoteMessage.data[NotificationFaw.ParamType.TYPE.name]
            val slidePayload = remoteMessage.data[NotificationFaw.ParamType.SLIDE_PAYLOAD.name]
            addDebugLog(logTag,"Nuovo messaggio contiene title notifica: \"${not.title}\".")
            addDebugLog(logTag,"Nuovo messaggio contiene body notifica: \"${not.body}\".")
            addDebugLog(logTag,"Nuovo messaggio contiene dato tipo notifica: \"${typeNotification}\".")
            addDebugLog(logTag,"Nuovo messaggio contiene dato slide payload: \"${slidePayload}\".")

            /* creo notifica */
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            when(typeNotification) {
                NotificationFaw.Type.UPDATE.name -> {
                    addDebugLog(logTag,"Nuovo messaggio contiene notifica di tipo UPDATE.")
                    NotificationFaw.createUpdateNotification(
                        context = this,
                        notificationManager = notificationManager,
                        remoteMessage = remoteMessage,
                    )
                }
                NotificationFaw.Type.REMEMBER_SLIDE.name -> {
                    addDebugLog(logTag,"Nuovo messaggio contiene notifica di tipo REMEMBER_SLIDE.")
                    NotificationFaw.createSlideNotification(
                        context = this,
                        notificationManager = notificationManager,
                        remoteMessage = remoteMessage,
                    )
                }
                else -> {
                    addDebugLog(logTag,"Nuovo messaggio contiene notifica di tipo GENERALE.")
                    NotificationFaw.createGeneralNotification(
                        context = this,
                        notificationManager = notificationManager,
                        remoteMessage = remoteMessage,
                    )
                }
            }


        } else {
            addDebugLog(logTag,"Messaggio non contiene notifica.")
        }
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
    }
}

 */