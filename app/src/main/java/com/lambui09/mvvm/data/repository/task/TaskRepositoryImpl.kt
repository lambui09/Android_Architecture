package com.lambui09.mvvm.data.repository.task

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lambui09.mvvm.data.common.DataResult
import com.lambui09.mvvm.data.model.BannerModel
import com.lambui09.mvvm.data.model.FlashDealModel
import com.lambui09.mvvm.data.model.QuickLikeModel
import com.lambui09.mvvm.data.model.Task
import com.lambui09.mvvm.data.remote.AppApi
import com.lambui09.mvvm.data.repository.BaseRepository
import com.lambui09.mvvm.di.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepositoryImpl(
    @ApplicationContext
    private val context: Context,
    private val api: AppApi
) : BaseRepository(), TaskRepository {

    private val taskCached = Task("Cache Task")

    private val observableTask: Flow<Task> = flow {
        emit(Task("Observable task"))
    }

    override suspend fun getAsynchronousTask(): DataResult<Task> = withResultContext {
        api.getSample()
    }

    override suspend fun getAsynchronousBanner(): DataResult<List<BannerModel>> {
        return withResultContext {
            api.getBannerItem()
        }
    }

    override suspend fun getAsynchronousQuizLink(): DataResult<List<QuickLikeModel>> {
        return withResultContext {
            api.getQuickLinkItem()
        }
    }

    override suspend fun getAsynchronousFlashDeal(): DataResult<List<FlashDealModel>> {
        return withResultContext {
            api.getFlashDealItem()
        }
    }

    override suspend fun getObservableTask(): LiveData<Task> {
        return observableTask.asLiveData()
    }

    override fun getSynchronousTask(): Task {
        return taskCached
    }

}