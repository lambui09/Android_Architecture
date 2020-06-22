package com.lambui09.mvvm.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import okio.BufferedSource
import java.nio.charset.Charset

class ErrorInterceptor(private val gson: Gson) : Interceptor {

    @Throws(RuntimeException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        if (response.isSuccessful) {
            return response
        }
        //Parcel error data here
        val body = response.body
        if (body != null) {
            try {
                val source: BufferedSource = body.source()
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer: Buffer = source.buffer
                val charset: Charset? = body.contentType()?.charset(Charset.forName("UTF-8"))
                // Clone the existing buffer is they can only read once so we still want to pass the original one to the chain.
                val json: String = buffer.clone().readString(charset ?: Charset.forName("UTF-8"))
                val errorData = gson.fromJson(json, ApiError::class.java)
                throw ApiException(response.code, errorData)
            } catch (parseJsonEx: JsonSyntaxException) {
                return response
            }
        }
        return response
    }
}

class ApiException(val code: Int, val error: ApiError) : RuntimeException() {

    override val message: String? by lazy {
        return@lazy ("${code}: ${error.message}")
    }
}

/**
 * Sample error with single property is message
 */
data class ApiError(
    @SerializedName("message")
    val message: String
)