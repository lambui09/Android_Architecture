package com.lambui09.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlashDealModel(
    @SerializedName("status")
    @Expose
    val status: Long? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("status")
    @Expose
    val tags: String? = null,
    @SerializedName("discount_percent")
    @Expose
    val discount_percent: Long? = null,
    @SerializedName("special_price")
    @Expose
    val special_price: Long? = null,
    @SerializedName("special_from_date")
    @Expose
    val special_from_date: Long? = null,
    @SerializedName("from_date")
    @Expose
    val from_date: String? = null,
    @SerializedName("special_to_date")
    @Expose
    val special_to_date: String? = null,
    @SerializedName("progress")
    @Expose
    val progress: ProgressModel? = null,
    @SerializedName("product")
    @Expose
    val product: ProductModel? = null,
    @SerializedName("url_attendant_input_form")
    @Expose
    val url_attendant_input_form: String? = null,
    @SerializedName("master_id")
    @Expose
    val master_id: Long? = null,
    @SerializedName("seller_product_id")
    @Expose
    val seller_product_id: Long? = null,
    @SerializedName("price_prefix")
    @Expose
    val price_prefix: String? = null
) : Parcelable