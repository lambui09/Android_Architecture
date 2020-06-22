package com.lambui09.mvvm.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.R
import com.lambui09.mvvm.data.repository.task.TaskRepository
import com.lambui09.mvvm.di.ApplicationContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = context.getString(R.string.click_here)
    }
    val text: LiveData<String> = _text
}