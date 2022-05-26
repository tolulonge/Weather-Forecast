package com.tolulonge.weatherforecast.core.util

import android.content.Context
import android.graphics.Color
import android.os.Build.VERSION.SDK_INT
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.google.android.material.snackbar.Snackbar
import com.tolulonge.weatherforecast.R
import java.io.InputStream

fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.yellow_700))
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
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setTextColor(ContextCompat.getColor(this.context, R.color.white))
        .setBackgroundTint(ContextCompat.getColor(this.context, R.color.red_700))
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

fun ImageView.loadGifs(gifDrawable: Int) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(gifDrawable)
        .target(this)
        .build()
    imageLoader.enqueue(request)
}

fun ImageView.loadGifs(phenomenon: String) {
    val imageToLoad = when{
        phenomenon.contains("cloud") -> R.drawable.clouds
        phenomenon.contains("clear") -> R.drawable.clouds
        phenomenon.contains("snow") -> R.drawable.snowflake
        phenomenon.contains("shower") || phenomenon.contains("rain") -> R.drawable.rain
        phenomenon.contains("thunder") -> R.drawable.storm
        phenomenon.contains("hail") || phenomenon.contains("glaze")  -> R.drawable.hail
        phenomenon.contains("fog") -> R.drawable.foggy
        phenomenon.contains("mist") -> R.drawable.mist
        phenomenon.contains("sleet") -> R.drawable.sleet
        else -> R.drawable.clouds
    }
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(imageToLoad)
        .target(this)
        .build()
    imageLoader.enqueue(request)
}

fun ImageView.loadHeaderGifs(phenomenon: String){
    val imageToLoad = when{
        phenomenon.contains("cloud") ||  phenomenon.contains("clear")  -> R.drawable.clear_sky
        phenomenon.contains("snow") || phenomenon.contains("hail") || phenomenon.contains("fog")
                || phenomenon.contains("mist") || phenomenon.contains("sleet")  -> R.drawable.main_snow
        phenomenon.contains("shower") || phenomenon.contains("rain") -> R.drawable.main_rain
        phenomenon.contains("thunder") -> R.drawable.main_thunderstorm
        phenomenon.contains("hail") || phenomenon.contains("glaze")  -> R.drawable.main_hail
        else -> R.drawable.clear_sky
    }
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(imageToLoad)
        .target(this)
        .build()
    imageLoader.enqueue(request)
}

fun ImageView.loadWindGifs(direction: String){
    val gifToLoad = when{
        direction.contains("east") -> R.drawable.east_wind
        direction.contains("west") -> R.drawable.west_wind
        direction.contains("north") -> R.drawable.north_wind
        direction.contains("south") -> R.drawable.south_wind
        else -> R.drawable.west_wind
    }
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val request = ImageRequest.Builder(context)
        .data(gifToLoad)
        .target(this)
        .placeholder(R.drawable.east_wind)
        .build()
    imageLoader.enqueue(request)
}

// Returns a particular theme depending on the time of the day
fun getApplicationTheme(phenomenon: String): Int {
    return when {
        phenomenon.contains("cloud") || phenomenon.contains("clear") -> R.style.Theme_WeatherForecast
        phenomenon.contains("snow") || phenomenon.contains("hail") || phenomenon.contains("fog")
                || phenomenon.contains("mist") || phenomenon.contains("sleet") -> R.style.Theme_WeatherForecast_Shower
        phenomenon.contains("shower") || phenomenon.contains("rain") -> R.style.Theme_WeatherForecast_Shower
        phenomenon.contains("thunder") -> R.drawable.main_thunderstorm
        phenomenon.contains("hail") || phenomenon.contains("glaze") -> R.style.Theme_WeatherForecast_Shower
        else -> R.style.Theme_WeatherForecast

    }
}


