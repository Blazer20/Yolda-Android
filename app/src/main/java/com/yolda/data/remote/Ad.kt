package com.yolda.data.remote

import com.yolda.common.EMPTY_STRING
import com.yolda.common.ZERO
import com.yolda.common.emptyGeoposition
import com.yolda.common.emptySocialMedia
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ad(

    @SerialName("address")
    val address: String,

    @SerialName("description")
    val description: String,

    @SerialName("geoposition")
    val geoposition: Geoposition?,

    @SerialName("imageURL")
    val imageURL: String,

    @SerialName("socialMedia")
    val socialMedia: SocialMedia?,

    @SerialName("title")
    val title: String
) {
    constructor(): this(
        address = EMPTY_STRING,
        description = EMPTY_STRING,
        geoposition = emptyGeoposition,
        imageURL = EMPTY_STRING,
        socialMedia = emptySocialMedia,
        title = EMPTY_STRING
    )
}

@Serializable
data class Geoposition(

    @SerialName("latitude")
    val latitude: Int,

    @SerialName("longitude")
    val longitude: Int
) { constructor(): this(latitude = ZERO, longitude = ZERO) }

@Serializable
data class SocialMedia(

    @SerialName("instagram")
    val instagram: String
) { constructor(): this(instagram = EMPTY_STRING) }
