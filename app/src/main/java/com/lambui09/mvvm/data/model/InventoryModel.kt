package com.lambui09.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InventoryModel(
    @SerializedName("product_virtual_type")
    @Expose
    val product_virtual_type: String? = null,
    @SerializedName("fulfillment_type")
    @Expose
    val fulfillment_type: String? = null
) : Parcelable