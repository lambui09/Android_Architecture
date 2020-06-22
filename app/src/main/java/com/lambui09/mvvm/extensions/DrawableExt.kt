package com.lambui09.mvvm.extensions

import android.graphics.drawable.Drawable
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

inline fun <T> Drawable.invalidateDelegate(
    initialValue: T,
    crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit = { _, _, _ -> }
) =
    Delegates.observable(initialValue) { kProperty, oldVale, newValue ->
        val notSame = oldVale != newValue
        if (notSame) {
            onChange(kProperty, oldVale, newValue)
            invalidateSelf()
        }
    }

fun Drawable.clone(): Drawable? = this.constantState?.newDrawable()?.mutate()