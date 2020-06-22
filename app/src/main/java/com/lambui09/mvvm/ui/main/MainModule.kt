package com.lambui09.mvvm.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.di.ActivityContext
import com.lambui09.mvvm.di.viewmodel.ViewModelKey
import com.lambui09.mvvm.ui.dashboard.DashboardFragment
import com.lambui09.mvvm.ui.dashboard.DashboardModule
import com.lambui09.mvvm.ui.home.HomeFragment
import com.lambui09.mvvm.ui.home.HomeModule
import com.lambui09.mvvm.ui.notifications.NotificationsFragment
import com.lambui09.mvvm.ui.notifications.NotificationsModule
import com.lambui09.mvvm.ui.task_detail.TaskDetailFragment
import com.lambui09.mvvm.ui.task_detail.TaskDetailModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @ActivityContext
    abstract fun bindActivityContext(activity: MainActivity): Context

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun bindDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector(modules = [NotificationsModule::class])
    abstract fun bindNotificationsFragment(): NotificationsFragment

    @ContributesAndroidInjector(modules = [TaskDetailModule::class])
    abstract fun bindTaskDetailFragment(): TaskDetailFragment
}