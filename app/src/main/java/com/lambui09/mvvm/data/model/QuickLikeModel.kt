package com.lambui09.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuickLikeModel(
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("content")
    @Expose
    val content: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("authentication")
    @Expose
    val authentication: Boolean? = null,
    @SerializedName("web_url")
    @Expose
    val webUrl: String? = null,
    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null
) : Parcelable