package com.lambui09.mvvm.extensions

import android.content.Context
import android.util.TypedValue

fun Number.dpToPx(context: Context): Int {
    return dpToPxF(context).toInt()
}

fun Number.dpToPxF(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        context.resources.displayMetrics
    )
}

fun Boolean.toInt() = if (this) 1 else 0

infix fun Int.hasFlag(flag: Int) = (this and flag) == flag