package com.lambui09.mvvm.extensions

import android.app.Activity
import android.app.Application
import android.app.Service
import android.app.backup.BackupAgent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils

@ColorInt
fun Context.getSupportColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

@ColorInt
fun Context.getSupportColor(@ColorRes colorRes: Int, alpha: Int): Int {
    return ColorUtils.setAlphaComponent(ContextCompat.getColor(this, colorRes), alpha)
}

fun Context.getSupportDrawable(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

/**
 * Converts dp to pixel in view
 */
fun Context.convertDpToPx(dp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp, this.resources.displayMetrics
)

fun Context.convertDpToIntPx(dp: Float): Int = convertDpToPx(dp).toInt()

/**
 * This method will return true on [Context] implementations known to be able to leak memory.
 * This includes [Activity], [Service], the lesser used and lesser known [BackupAgent], as well as
 * any [ContextWrapper] having one of these as its base context.
 */
fun Context.canLeakMemory(): Boolean = when (this) {
    is Application -> false
    is Activity, is Service, is BackupAgent -> true
    is ContextWrapper -> if (baseContext === this) true else baseContext.canLeakMemory()
    else -> applicationContext === null
}

/**
 * Extension for start activity
 * @receiver Context
 * @param paramsBlock [@kotlin.ExtensionFunctionType] Function1<Intent, Unit>
 */
inline fun <reified A : Activity> Context.gotoActivity(paramsBlock: Intent.() -> Unit = {}) {
    val intent = Intent(this, A::class.java)
    paramsBlock(intent)
    startActivity(intent)
}