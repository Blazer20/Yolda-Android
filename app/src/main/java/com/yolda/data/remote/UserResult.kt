package com.yolda.data.remote

import com.yolda.common.EMPTY_STRING
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(

    @SerialName("email")
    val email: String,

    @SerialName("name")
    val name: String,

    @SerialName("role")
    val role: String
) {
    constructor(): this(
        email = EMPTY_STRING,
        name = EMPTY_STRING,
        role = EMPTY_STRING
    )
}
