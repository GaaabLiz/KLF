package io.github.gaaabliz.kliz.common.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

object NetworkUtils {

    fun getStringFromUrl(url: URL): String {
        val sb = StringBuilder()
        var line: String?
        val `in`: InputStream = url.openStream()
        try {
            val reader = BufferedReader(InputStreamReader(`in`))
            while (reader.readLine().also { line = it } != null) {
                sb.append(line).append(System.lineSeparator())
            }
        } finally {
            `in`.close()
        }
        return sb.toString()
    }
}