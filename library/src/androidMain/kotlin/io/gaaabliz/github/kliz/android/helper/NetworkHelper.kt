package io.gaaabliz.github.kliz.android.helper

import android.annotation.SuppressLint
import android.content.Context
import io.gaaabliz.github.kliz.android.util.NetworkUtils
import io.github.gaaabliz.kliz.common.model.ConnectionStatus


object NetworkHelper {
    /**
     * Metodo per controllare se la connessione a internet è presente nel telefono.
     * Se la connessione non ce, ogni tot secondi ne viene ri-controllata la presenza.
     * Il delay tra un tentativo e un altro e il numero di tentativi sono specificati
     * nel file di configurazione dell'app (config.xml).
     */
    @SuppressLint("MissingPermission")
    @Deprecated("Use coroutines instead")
    fun checkAndRepeatConnections(
        context: Context,
        delayEachConnection : Long,
        maxConnectionTry : Int
    ): ConnectionStatus {
        var status: ConnectionStatus = ConnectionStatus.NOT_CONNECTED
        var counterConnectionTry = 0

        /* effettuo tentativi */
        for (i in 1..maxConnectionTry) {

            /* controllo connessione */
            if(NetworkUtils.checkInternetConnection(context)) {
                status = ConnectionStatus.CONNECTED
                break
            } else {
                counterConnectionTry++
                status = ConnectionStatus.NOT_CONNECTED
            }

            /* setto ritardo */
            try {
                Thread.sleep(delayEachConnection)
            }catch (e:InterruptedException) {
            }

        }
        return status
    }
}



