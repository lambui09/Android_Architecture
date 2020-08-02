package com.lambui09.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProgressModel(
    @SerializedName("qty")
    @Expose
    val qty: Long? = null,
    @SerializedName("qty_ordered")
    @Expose
    val qty_ordered: Long? = null,
    @SerializedName("qty_remain")
    @Expose
    val qty_remain: Long? = null,
    @SerializedName("percent")
    @Expose
    val percent: Long? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null
) : Parcelable