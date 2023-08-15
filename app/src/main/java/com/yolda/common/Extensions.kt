package com.yolda.common

import android.telephony.PhoneNumberUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


fun String.isValidPhoneNumber(): Boolean {
    val pattern = Regex("^[0-9]{1,3}(?:,?[0-9]{3})*$")
    return this.matches(pattern)
}

fun String.phoneNumberFormat(): String {
    val formattedNumber = PhoneNumberUtils.formatNumber(this, "UZ")
    return formattedNumber ?: this
}

inline fun <reified E : Any> E.anyToJson(): String {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(E::class.java).lenient()
    return URLEncoder.encode(adapter.toJson(this), StandardCharsets.UTF_8.toString())
}

fun String.encode(): String =
    this.replace("@", "<at>").replace("%", "<percentage>")
        .replace("+", "<plus>")

fun String.decode(): String = this.replace("<plus>", "+")
    .replace("<percentage>", "%").replace("<at>", "@")

inline fun <reified T : Any> String.fromJsonTo(toType: Class<T>): T? {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val adapter = moshi.adapter(toType)
    return adapter.fromJson(URLDecoder.decode(this, StandardCharsets.UTF_8.toString()))
}
