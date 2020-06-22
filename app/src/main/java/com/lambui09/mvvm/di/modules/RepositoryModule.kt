package com.lambui09.mvvm.di.modules

import android.content.Context
import com.lambui09.mvvm.data.remote.AppApi
import com.lambui09.mvvm.data.repository.task.TaskRepository
import com.lambui09.mvvm.data.repository.task.TaskRepositoryImpl
import com.lambui09.mvvm.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getTaskRepository(@ApplicationContext context: Context, api: AppApi): TaskRepository {
        return TaskRepositoryImpl(context, api)
    }
}