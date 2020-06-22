package com.lambui09.mvvm.ultis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.lambui09.mvvm.R
import com.lambui09.mvvm.data.Constants.DELAY_BUTTON_CLICKED

/**
 * Get an error alert dialog builder
 * with the title and positive button text by default
 * @return [AlertDialog.Builder] for consumer can custom other properties
 */
fun getErrorAlertDialogBuilder(context: Context): AlertDialog.Builder {
    return AlertDialog.Builder(context)
        .setTitle(R.string.error)
        // Specifying a listener allows you to take an action before dismissing the dialog.
        // The dialog is automatically dismissed when a dialog button is clicked.
        .setPositiveButton(android.R.string.ok, null)
}

fun createChoosePhotoFromGalleryIntent(title: CharSequence): Intent {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    return Intent.createChooser(intent, title)
}


fun disableViewToPreventMultiTouch(v: View?) {
    v?.let {
        v.isClickable = false
        Handler().postDelayed({ v.isClickable = true }, DELAY_BUTTON_CLICKED)
    }
}

fun hideKeyboard(v: View?) {
    v?.let {
        val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun getScreenWidth(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun getScreenHeight(activity: Activity): Int {
    val displayMetrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}