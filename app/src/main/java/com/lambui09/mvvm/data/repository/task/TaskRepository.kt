package com.lambui09.mvvm.data.repository.task

import androidx.lifecycle.LiveData
import com.lambui09.mvvm.data.common.DataResult
import com.lambui09.mvvm.data.model.BannerModel
import com.lambui09.mvvm.data.model.FlashDealModel
import com.lambui09.mvvm.data.model.QuickLikeModel
import com.lambui09.mvvm.data.model.Task

interface TaskRepository {

    suspend fun getAsynchronousTask(): DataResult<Task>
    suspend fun getAsynchronousBanner(): DataResult<List<BannerModel>>
    suspend fun getAsynchronousQuizLink(): DataResult<List<QuickLikeModel>>
    suspend fun getAsynchronousFlashDeal(): DataResult<List<FlashDealModel>>
    suspend fun getObservableTask(): LiveData<Task>

    fun getSynchronousTask(): Task


}