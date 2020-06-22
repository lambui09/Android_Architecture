package com.lambui09.mvvm.data.repository.task

import androidx.lifecycle.LiveData
import com.lambui09.mvvm.data.common.DataResult
import com.lambui09.mvvm.data.model.Task

interface TaskRepository {

    suspend fun getAsynchronousTask() : DataResult<Task>

    suspend fun getObservableTask() : LiveData<Task>

    fun getSynchronousTask() : Task

}