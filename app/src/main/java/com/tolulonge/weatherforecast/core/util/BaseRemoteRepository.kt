package com.tolulonge.weatherforecast.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRemoteRepository {

    companion object {
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
        private const val ERRORS_KEY = "errors"
    }

    /**
     * Abstracts try and catch for all remote calls to handle network exceptions
     */
    suspend inline fun <T> safeApiCall(crossinline callFunction: suspend () -> T): Resource<T> {
        return try {
            val myObject = withContext(Dispatchers.IO) { callFunction.invoke() }
            Resource.Success(myObject)
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                when (e) {
                    is HttpException -> {

                        val body = e.response()?.errorBody()
                        Resource.Error(getErrorMessage(body))
                    }
                    is SocketTimeoutException -> Resource.Error("An error has occurred, try again later.")
                    is IOException -> Resource.Error("Can't refresh weather now, check your internet connection and try again.")
                    else -> Resource.Error(
                        e.localizedMessage ?: "An error has occurred, try again later."
                    )
                }
            }
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        val errorValue = responseBody!!.string()
         return try {
                val jsonObject = JSONObject(errorValue)
                when {
                    jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                    jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                    jsonObject.has(ERRORS_KEY) -> jsonObject.getString(ERRORS_KEY)
                    else -> "Something wrong happened"
                }
            } catch (e: Exception) {
                "Something wrong happened"
            }
    }
}
