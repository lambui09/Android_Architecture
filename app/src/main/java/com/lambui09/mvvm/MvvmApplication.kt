package com.lambui09.mvvm

import com.lambui09.mvvm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class MvvmApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.factory().create(applicationContext)
    }
}