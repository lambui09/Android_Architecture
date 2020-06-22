package com.lambui09.mvvm.ui.base.activity

import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @MainThread
    inline fun <reified VM : ViewModel> appViewModels() = viewModels<VM> { viewModelFactory }

    open fun replaceFragmentToLayout(fragment: Fragment, @IdRes containerViewId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        transaction.replace(containerViewId, fragment)
        transaction.commit()
    }

    protected fun removeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().remove(fragment).commit()
    }
}