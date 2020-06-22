package com.lambui09.mvvm.ui.dashboard

import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [DashboardModuleBind::class])
class DashboardModule {
    //Provider member variable for view model or fragment...
}

@Module
abstract class DashboardModuleBind {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel
}