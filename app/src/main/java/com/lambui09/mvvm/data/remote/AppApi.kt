package com.lambui09.mvvm.data.remote

import com.lambui09.mvvm.data.model.Task
import retrofit2.http.GET

interface AppApi {

    /**
     * This is an example method, which calls to server with kotlin coroutines.
     *
     * Since the release of Retrofit2 version 2.6.0,
     * there has been added native support for coroutines,
     * this helps to achieve better performance and reduces boilerplate significantly.
     */
    @GET("/task")
    suspend fun getSample() : Task

}