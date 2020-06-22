package com.lambui09.mvvm.ui.home

import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [HomeModuleBind::class])
class HomeModule {
    //Provider member variable for view model or fragment...
}

@Module
abstract class HomeModuleBind {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}