package com.mohamed.nbateamsandplayers.utils

import androidx.lifecycle.LiveData
import java.lang.Exception

open class Resource<out T>(val status: Status, val data: T?, val exception: Exception?) {
    enum class Status{
        SUCCESS,
        ERROR,
        LOADING
    }

    fun isSuccess(): Boolean {
        return status == Status.SUCCESS
    }

    fun isError(): Boolean {
        return status == Status.ERROR
    }

    fun isLoading(): Boolean {
        return status == Status.LOADING
    }

    companion object{
        fun <T> success(data: T): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: Exception?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, exception)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}