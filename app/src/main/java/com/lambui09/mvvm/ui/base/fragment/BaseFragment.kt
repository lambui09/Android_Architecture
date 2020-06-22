package com.lambui09.mvvm.ui.base.fragment

import androidx.annotation.MainThread
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @MainThread
    inline fun <reified VM : ViewModel> appActivityViewModels() = activityViewModels<VM> { viewModelFactory }

    /**
     * Using noinline keyword to avoid crash when the ViewModel force getParentFragment immediately
     */
    @MainThread
    inline fun <reified VM : ViewModel> appViewModels(noinline ownerProducer: () -> ViewModelStoreOwner = { this }) =
        viewModels<VM>(ownerProducer) { viewModelFactory }
}