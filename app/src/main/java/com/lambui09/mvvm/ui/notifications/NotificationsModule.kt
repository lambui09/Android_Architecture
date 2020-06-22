package com.lambui09.mvvm.ui.notifications

import androidx.lifecycle.ViewModel
import com.lambui09.mvvm.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [NotificationsModuleBind::class])
class NotificationsModule {
    //Provider member variable for view model or fragment...
}

@Module
abstract class NotificationsModuleBind {

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(viewModel: NotificationsViewModel): ViewModel
}