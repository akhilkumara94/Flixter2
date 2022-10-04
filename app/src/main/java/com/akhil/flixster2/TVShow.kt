package com.akhil.flixster2

import android.support.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
@kotlinx.serialization.Serializable
class TVShow (): java.io.Serializable {
    @JvmField
    @SerializedName("first_air_date")
    var first_air_date: String? = null

    @JvmField
    @SerializedName("original_name")
    var title: String? = null

    @JvmField
    @SerializedName("overview")
    var summary: String? = null

    @JvmField
    @SerializedName("poster_path")
    var multimedia:String? = null
}