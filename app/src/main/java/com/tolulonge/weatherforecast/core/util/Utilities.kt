package com.tolulonge.weatherforecast.core.util

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.tolulonge.weatherforecast.R
import java.io.InputStream

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.orange_500))
        .show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showSnackBarWithAction(message: String, actionText: String, action: (() -> Unit)) {
    Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
        .setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.blue_500))
        .setAction(actionText) {
            action.invoke()
        }.setActionTextColor(Color.YELLOW)
        .show()
}

fun readFileFromAssets(fileName: String, c: Context): String? {
    var inputStream: InputStream? = null
     try {
        inputStream = c.assets.open(fileName)

        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
       return String(buffer)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }
    return null
}


