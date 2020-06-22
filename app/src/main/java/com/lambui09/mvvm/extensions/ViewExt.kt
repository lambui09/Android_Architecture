package com.lambui09.mvvm.extensions

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.lambui09.mvvm.data.common.Event
import com.lambui09.mvvm.ultis.disableViewToPreventMultiTouch
import com.lambui09.mvvm.ultis.hideKeyboard
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


inline fun View.obtainAttrs(
    attrs: AttributeSet?,
    @StyleableRes style: IntArray,
    defaultStyleAttr: Int = 0,
    defStyleRes: Int = 0,
    predicate: TypedArray.() -> Unit
) {
    val a = context.theme.obtainStyledAttributes(attrs, style, defaultStyleAttr, defStyleRes)
    try {
        predicate(a)
    } finally {
        a.recycle()
    }
}

fun EditText.requestFocusAndShowKeyboard(delay: Long = 0) {
    postDelayed({
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        this.setSelection(text.length)
    }, delay)
}

fun EditText.clearFocusAndHideKeyboard(delay: Long = 0, onHided: (() -> Unit)? = null) {
    clearFocus()
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
    postDelayed({
        onHided?.invoke()
    }, delay)
}

@ColorInt
fun View.getSupportColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(context, colorRes)
}

@ColorInt
fun View.getSupportColor(@ColorRes colorRes: Int, alpha: Int): Int {
    return ColorUtils.setAlphaComponent(ContextCompat.getColor(context, colorRes), alpha)
}

fun View.getSupportDrawable(@DrawableRes drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(context, drawableRes)
}

@ColorInt
fun View.getAttrColor(@AttrRes attrRes: Int): Int {
    val typedValue = TypedValue()
    val theme = context.theme
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data
}

fun View.setPaddingLeft(padding: Int) {
    setPadding(padding, paddingTop, paddingRight, paddingBottom)
}

fun View.setPaddingRight(padding: Int) {
    setPadding(paddingLeft, paddingTop, padding, paddingBottom)
}

fun View.setPaddingTop(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, paddingBottom)
}

fun View.setPaddingBottom(padding: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, padding)
}

fun View.setPaddingStart(padding: Int) {
    setPaddingRelative(padding, paddingTop, paddingEnd, paddingBottom)
}

fun View.setPaddingEnd(padding: Int) {
    setPadding(paddingStart, paddingTop, padding, paddingBottom)
}

inline fun <T> View.invalidateDelegate(
    initialValue: T,
    crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit = { _, _, _ -> }
) =
    Delegates.observable(initialValue) { kProperty, oldVale, newValue ->
        val notSame = oldVale != newValue
        if (notSame) {
            onChange(kProperty, oldVale, newValue)
            invalidate()
        }
    }

inline fun <T> View.requestLayoutDelegate(
    initialValue: T,
    crossinline onChange: (property: KProperty<*>, oldValue: T, newValue: T) -> Unit = { _, _, _ -> }
) =
    Delegates.observable(initialValue) { kProperty, oldVale, newValue ->
        val notSame = oldVale != newValue
        if (notSame) {
            onChange(kProperty, oldVale, newValue)
            invalidate()
        }
    }

fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
    var result: Int
    val specMode = View.MeasureSpec.getMode(measureSpec)
    val specSize = View.MeasureSpec.getSize(measureSpec)
    if (specMode == View.MeasureSpec.EXACTLY) {
        result = specSize
    } else {
        result = desiredSize
        if (specMode == View.MeasureSpec.AT_MOST) {
            result = result.coerceAtMost(specSize)
        }
    }
    if (result < desiredSize) {
        Log.e("TargetStatsView", "The view is too small, the content might get cut")
    }
    return View.MeasureSpec.makeMeasureSpec(result, specMode)
}

fun View.doOnApplyWindowInsets(f: (View, WindowInsetsCompat, InitialPadding) -> Unit) {
    // Create a snapshot of the view's padding state
    val initialPadding = recordInitialPaddingForView(this)
    // Set an actual OnApplyWindowInsetsListener which proxies to the given
    // lambda, also passing in the original padding state
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        f(v, insets, initialPadding)
        // Always return the insets, so that children can also use them
        insets
    }
    // request some insets
    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        // We're already attached, just request as normal
        requestApplyInsets()
    } else {
        // We're not attached to the hierarchy, add a listener to
        // request when we are
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

data class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)


fun View.setOnSingleClickListener(listener: (view: View) -> Unit) {
    setOnClickListener { v ->
        disableViewToPreventMultiTouch(v)
        hideKeyboard(v)
        listener(v)
    }
}

/**
 * Transforms static java function Snackbar.make() to an extension function on View.
 */
fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).show()
}

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int = Snackbar.LENGTH_SHORT
) {

    snackbarEvent.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            showSnackbar(context.getString(it), timeLength)
        }
    })
}