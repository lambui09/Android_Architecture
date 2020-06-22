package com.lambui09.mvvm.di

import android.content.Context
import com.lambui09.mvvm.MvvmApplication
import com.lambui09.mvvm.di.modules.ActivitiesBuilder
import com.lambui09.mvvm.di.modules.AppModule
import com.lambui09.mvvm.di.modules.RepositoryModule
import com.lambui09.mvvm.di.modules.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivitiesBuilder::class,
        RepositoryModule::class,
        ViewModelBuilder::class
    ]
)
interface AppComponent : AndroidInjector<MvvmApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}