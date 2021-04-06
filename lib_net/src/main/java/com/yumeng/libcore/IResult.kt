package com.yumeng.libcore

sealed class IResult<out T : Any> {

    data class Success<out T : Any>(val data: T?) : IResult<T>()
    data class Error(val exception: Exception) : IResult<Nothing>()
    data class Expired(val exception: Exception) : IResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Expired -> "Error[exception=$exception]"
        }
    }

}