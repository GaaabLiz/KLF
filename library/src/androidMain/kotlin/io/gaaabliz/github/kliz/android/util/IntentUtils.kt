package io.gaaabliz.github.kliz.android.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object IntentUtils {

    fun sendEmailIntent(
        context: Context,
        email: String,
        subject: String = "",
        body: String = ""
    ) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "message/rfc822"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, body)
        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }

    fun openUrlIntent(
        context: Context,
        urlString : String
    ) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(urlString)
        context.startActivity(i)
    }
}