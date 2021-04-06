package com.yumeng.libcore.base

import android.util.Log
import com.yumeng.libcore.IResult
import com.yumeng.libcore.response.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException


open class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> BaseResponse<T>): BaseResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> IResult<T>,
        errorMessage: String
    ): IResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            Log.e("BaseRepository", e.message.toString())
            // An exception was thrown when calling the API so we're converting this to an IOException
            if (!errorMessage.isNullOrEmpty()) {
                IResult.Error(IOException(errorMessage, e))
            } else {
                IResult.Error(IOException(e.message.toString(), e))
            }
        }
    }

    suspend fun <T : Any> executeResponse(
        response: BaseResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): IResult<T> {
        return coroutineScope {
            when (response.status) {
//                4 -> {
//                    errorBlock?.let { it() }
//                    IResult.Expired(IOException(response.message))
//                }
                200 -> {
                    successBlock?.let { it() }
                    IResult.Success(response.data)
                }
                else -> {
                    errorBlock?.let { it() }
                    Log.e("netResponse", "response.msg:${response.message}")
                    IResult.Error(IOException(response.message))
                }
            }
        }
    }

}