package com.lambui09.mvvm.ui.task_detail

import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [TaskDetailModuleBind::class])
class TaskDetailModule {
    //Provider member variable for view model or fragment...
}

@Module
abstract class TaskDetailModuleBind {

    @Binds
    @IntoMap
    @ViewModelKey(TaskDetailViewModel::class)
    abstract fun bindTaskDetailViewModel(viewModel: TaskDetailViewModel): ViewModel
}