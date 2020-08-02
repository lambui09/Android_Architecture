package com.lambui09.mvvm.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    @SerializedName("id")
    @Expose
    val id: String? = null,
    @SerializedName("sku")
    @Expose
    val sku: String? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("url_path")
    @Expose
    val url_path: String? = null,
    @SerializedName("price")
    @Expose
    val price: String? = null,
    @SerializedName("list_price")
    @Expose
    val list_price: String? = null,
    @SerializedName("badges")
    @Expose
    val badges: List<String>? = null,
    @SerializedName("discount")
    @Expose
    val discount: Long? = null,
    @SerializedName("rating_average")
    @Expose
    val rating_average: Long? = null,
    @SerializedName("review_count")
    @Expose
    val review_count: Long? = null,
    @SerializedName("order_count")
    @Expose
    val order_count: Long? = null,
    @SerializedName("thumbnail_url")
    @Expose
    val thumbnail_url: String? = null,
    @SerializedName("is_visible")
    @Expose
    val is_visible: String? = null,
    @SerializedName("is_fresh")
    @Expose
    val is_fresh: String? = null,
    @SerializedName("is_flower")
    @Expose
    val is_flower: String? = null,
    @SerializedName("is_gift_card")
    @Expose
    val is_gift_card: String? = null,
    @SerializedName("url_attendant_input_form")
    @Expose
    val url_attendant_input_form: String? = null,
    @SerializedName("master_id")
    @Expose
    val master_id: String? = null,
    @SerializedName("seller_product_id")
    @Expose
    val seller_product_id: String? = null,
    @SerializedName("price_prefix")
    @Expose
    val price_prefix: String? = null
) : Parcelable