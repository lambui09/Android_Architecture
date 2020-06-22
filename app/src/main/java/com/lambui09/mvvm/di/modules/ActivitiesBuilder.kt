package com.lambui09.mvvm.di.modules

import com.lambui09.mvvm.ui.main.MainActivity
import com.lambui09.mvvm.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun binMainActivity() : MainActivity
}