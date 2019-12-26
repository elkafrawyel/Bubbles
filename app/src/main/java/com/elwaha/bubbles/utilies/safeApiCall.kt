package com.elwaha.bubbles.utilies

import com.elwaha.bubbles.R


suspend fun <T : Any> safeApiCall(call: suspend () -> DataResource<T>): DataResource<T> {
    return try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
        DataResource.Error(Injector.getApplicationContext().getString(R.string.generalError))
    }
}

suspend fun <T : Any> safeApiCall(call: suspend () -> DataResource<T>, errorMessage: String): DataResource<T> {
    return try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
        DataResource.Error(errorMessage)
    }
}
