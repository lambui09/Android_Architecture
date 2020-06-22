package com.lambui09.mvvm.extensions

import android.graphics.Bitmap
import kotlin.math.roundToInt

fun Bitmap.scaleDown(maxImageSize: Float, filter: Boolean): Bitmap {
    val ratio = (maxImageSize / width).coerceAtMost(maxImageSize / height)
    val width = (ratio * width).roundToInt()
    val height = (ratio * height).roundToInt()
    return Bitmap.createScaledBitmap(this, width, height, filter)
}

fun Bitmap.scaleDownByWidth(maxWidth: Float, filter: Boolean): Bitmap {
    if (maxWidth <= width) return this
    val ratio = maxWidth / width
    val width = (ratio * width).roundToInt()
    val height = (ratio * height).roundToInt()
    return Bitmap.createScaledBitmap(this, width, height, filter)
}