package com.lambui09.mvvm.data.remote

import com.lambui09.mvvm.data.model.BannerModel
import com.lambui09.mvvm.data.model.FlashDealModel
import com.lambui09.mvvm.data.model.QuickLikeModel
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
    suspend fun getSample(): Task

    @GET("shopping/v2/widgets/quick_link")
    suspend fun getQuickLinkItem(): List<QuickLikeModel>

    @GET("v2/home/banners/v2")
    suspend fun getBannerItem(): List<BannerModel>

    @GET("v2/widget/deals/hot")
    suspend fun getFlashDealItem(): List<FlashDealModel>

}