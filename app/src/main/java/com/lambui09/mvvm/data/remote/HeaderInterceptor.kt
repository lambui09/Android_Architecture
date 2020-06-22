package com.lambui09.mvvm.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Setup base header here")
    }

}