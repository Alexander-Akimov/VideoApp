package com.use.dagger.ui.auth

class AuthResource<out T>(
    val status: AuthStatus? = null,
    val data: T? = null,
    val message: String?
) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {

        fun <T> authenticated(data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String, data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): AuthResource<T> {
            return AuthResource(AuthStatus.LOADING, data, null)
        }

        fun <T> logout(): AuthResource<T> {
            return AuthResource(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}