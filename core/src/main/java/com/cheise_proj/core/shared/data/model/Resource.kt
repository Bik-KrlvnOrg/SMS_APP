package com.cheise_proj.core.shared.data.model

/***
 * Manage incoming data state
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(status = Status.SUCCESS, data = data, message = null)
        }

        fun <T> error(message: String?, data: T? = null): Resource<T> {
            return Resource(status = Status.ERROR, data = data, message = message)
        }

        fun <T> loading(): Resource<T> {
            return Resource(status = Status.LOADING, data = null, message = null)
        }
    }
}

enum class Status {
    LOADING, ERROR, SUCCESS
}