package com.lambui09.mvvm.extensions

import kotlin.properties.Delegates

inline fun <T> valueChangedDelegate(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit
) =
    Delegates.observable(initialValue) { _, oldVale, newValue ->
        val notSame = oldVale != newValue
        if (notSame) onChange(newValue)
    }