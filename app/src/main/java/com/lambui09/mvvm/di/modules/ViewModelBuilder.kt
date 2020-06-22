package com.lambui09.mvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lambui09.mvvm.di.viewmodel.ViewModelFactory
import com.lambui09.mvvm.di.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

@Module
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(PlaceHolderViewModel::class)
    abstract fun bindPlaceHolderViewModel(viewModel: PlaceHolderViewModel): ViewModel

    /**
     * If using BaseActivity without ViewModel, The [ViewModelFactory.creators] will is emptied.
     * Make a place holder viewModel to make sure a map of viewModels not empty in activity scope
     * Purpose to avoid crashed if [com.lambui09.mvvm.ui.base.activity.BaseActivity.viewModelFactory] is empty
     */
    class PlaceHolderViewModel @Inject constructor() : ViewModel()
}