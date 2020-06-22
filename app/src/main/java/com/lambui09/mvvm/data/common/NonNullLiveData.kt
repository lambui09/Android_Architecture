package com.lambui09.mvvm.data.common

import androidx.lifecycle.MutableLiveData

/**
 * This class is subclass of MutableLiveData but it provider a non null value
 * @param T: Any Obligatory default value
 * @constructor
 */
class NonNullLiveData<T : Any>(initialValue: T) : MutableLiveData<T>(initialValue) {
    override fun getValue(): T = super.getValue()!!
}