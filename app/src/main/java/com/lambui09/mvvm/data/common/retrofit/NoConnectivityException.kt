package com.lambui09.mvvm.data.common.retrofit

import android.content.Context
import com.lambui09.mvvm.R

import java.io.IOException

class NoConnectivityException(context: Context) : IOException(context.getString(R.string.no_network))