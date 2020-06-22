package com.lambui09.mvvm.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.data.repository.task.TaskRepository
import com.lambui09.mvvm.di.ApplicationContext
import javax.inject.Inject

/**
 * Note: DO NOT use Context of activity in the ViewModel to avoid memory leak
 */
class MainViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val taskRepository: TaskRepository
): ViewModel() {

}