package com.tgw.myapplication.coroutine

import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>();
}

class LoginResponseParser {
}

class LoginRespository(private val responseParser: LoginResponseParser) {
    private val loginUrl = ""

    fun makeLoginRequest(jsonBody: String) {
        val url = URL(loginUrl)
        (url.openConnection() as? HttpURLConnection)?.run {

        }
    }
}