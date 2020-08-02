package com.lambui09.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BannerModel(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("content")
    @Expose
    val content: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null,
    @SerializedName("mobile_url")
    @Expose
    val mobileUrl: String? = null,
    @SerializedName("ratio")
    @Expose
    val ratio: Float? = null

) : Parcelable