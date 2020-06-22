package com.lambui09.mvvm.extensions

import androidx.lifecycle.LiveData
import com.lambui09.mvvm.data.common.DataResult

fun <T> getValueFromResultLiveData(data: LiveData<DataResult<T>>): T? {
    val value = data.value
    return if (value is DataResult.Success) value.data else null
}