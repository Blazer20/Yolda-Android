package com.yolda.data.remote

import com.google.gson.annotations.SerializedName
import com.yolda.common.DOUBLE_ZERO
import com.yolda.common.EMPTY_STRING
import com.yolda.common.ZERO
import com.yolda.common.emptyFuelType
import com.yolda.common.emptyGeoposition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationBrand(

    @SerialName("legal_name")
    val legal_name: String,

    @SerialName("name")
    val name: String,

    val stations: List<Station>

) { constructor() : this(legal_name = EMPTY_STRING, name = EMPTY_STRING, stations = emptyList()) }

@Serializable
data class Station(

    @SerialName("address")
    val address: String,

    @SerialName("geoposition")
    val geoposition: Geoposition,

    @SerialName("imageURL")
    val imageURL: String,

    @SerializedName("fuel_types")
    val fuel_types: FuelType,

    @SerialName("legal_id")
    val legal_id: String,

    @SerialName("average_rating")
    val average_rating: Double?,

    @SerialName("rating_reviews_number")
    val rating_reviews_number: Int?
) {
    constructor() : this(
        address = EMPTY_STRING,
        geoposition = emptyGeoposition,
        imageURL = EMPTY_STRING,
        fuel_types = emptyFuelType,
        legal_id = EMPTY_STRING,
        average_rating = DOUBLE_ZERO,
        rating_reviews_number = ZERO
    )
}

@Serializable
data class FuelType(

    @SerialName("electro")
    val electro: Electro?,

    @SerialName("benzine")
    val benzine: Benzine?,

    @SerialName("gasoline")
    val gasoline: Gasoline?,

    @SerialName("diesel")
    val diesel: Diesel?

) {
    constructor() : this(electro = null, benzine = null, gasoline = null, diesel = null)
}

@Serializable
data class Electro(

    @SerialName("gbt_ac")
    val gbt_ac: GbtAC?,

    @SerialName("gbt_dc")
    val gbt_dc: GbtDC?,

    @SerialName("ccs_combo_1")
    val ccs_combo_first: CCSComboFirst?,

    @SerialName("ccs_combo_2")
    val ccs_combo_second: CCSComboSecond?,

    @SerialName("type_one")
    val type_one: TypeOne?,

    @SerialName("type_two")
    val type_two: TypeTwo?,

    @SerialName("chademo")
    val chademo: Chademo?
) {
    constructor() : this(
        gbt_ac = null,
        gbt_dc = null,
        ccs_combo_first = null,
        ccs_combo_second = null,
        type_one = null,
        type_two = null,
        chademo = null
    )
}

@Serializable
data class GbtAC(
    @SerialName("seven_kW")
    val seven_kW: String?
) {
    constructor() : this(seven_kW = EMPTY_STRING)
}

@Serializable
data class GbtDC(

    @SerialName("thirtyTwo_kW")
    val thirtyTwo_kW: String?,

    @SerialName("sixty_kW")
    val sixty_kW: String?,

    @SerialName("eighty_kW")
    val eighty_kW: String?,

    @SerialName("hundred_kW")
    val hundred_kW: String?,

    @SerialName("oneHundredTwenty_kW")
    val oneHundredTwenty_kW: String?,

    @SerialName("oneHundredSixty_kW")
    val oneHundredSixty_kW: String?,

    @SerialName("twoHundred_kW")
    val twoHundred_kW: String?,

    @SerialName("twoHundredForty_kW")
    val twoHundredForty_kW: String?

) {
    constructor() : this(
        thirtyTwo_kW = EMPTY_STRING,
        sixty_kW = EMPTY_STRING,
        eighty_kW = EMPTY_STRING,
        hundred_kW = EMPTY_STRING,
        oneHundredTwenty_kW = EMPTY_STRING,
        oneHundredSixty_kW = EMPTY_STRING,
        twoHundred_kW = EMPTY_STRING,
        twoHundredForty_kW = EMPTY_STRING,
    )
}

@Serializable
data class CCSComboFirst(

    @SerialName("eighty_kW")
    val eighty_kW: String?,

    @SerialName("oneHundredSixty_kW")
    val oneHundredSixty_kW: String?

) {
    constructor() : this(eighty_kW = EMPTY_STRING, oneHundredSixty_kW = EMPTY_STRING)
}

@Serializable
data class CCSComboSecond(

    @SerialName("sixty_kW")
    val sixty_kW: String?,

    @SerialName("eighty_kW")
    val eighty_kW: String?,

    @SerialName("oneHundredSixty_kW")
    val oneHundredSixty_kW: String?

) {
    constructor() : this(
        sixty_kW = EMPTY_STRING,
        eighty_kW = EMPTY_STRING,
        oneHundredSixty_kW = EMPTY_STRING
    )
}

@Serializable
data class TypeOne(

    @SerialName("sixteen_kW")
    val sixteen_kW: String?

) {
    constructor() : this(sixteen_kW = EMPTY_STRING)
}

@Serializable
data class TypeTwo(

    @SerialName("seven_kW")
    val seven_kW: String?

) {
    constructor() : this(seven_kW = EMPTY_STRING)
}

@Serializable
data class Chademo(

    @SerialName("forty_kW")
    val forty_kW: String?,

    @SerialName("fifty_kW")
    val fifty_kW: String?

) {
    constructor() : this(
        forty_kW = EMPTY_STRING,
        fifty_kW = EMPTY_STRING
    )
}

@Serializable
data class Benzine(

    @SerialName("ai80")
    val ai80: String?,

    @SerialName("ai91")
    val ai91: String?,

    @SerialName("ai92")
    val ai92: String?,

    @SerialName("ai95")
    val ai95: String?,

    @SerialName("ai100")
    val ai100: String?

) {
    constructor() : this(
        ai80 = EMPTY_STRING,
        ai91 = EMPTY_STRING,
        ai92 = EMPTY_STRING,
        ai95 = EMPTY_STRING,
        ai100 = EMPTY_STRING
    )
}

@Serializable
data class Gasoline(

    @SerialName("metan")
    val metan: String?,

    @SerialName("propan")
    val propan: String?

) {
    constructor() : this(
        metan = EMPTY_STRING,
        propan = EMPTY_STRING
    )
}

@Serializable
data class Diesel(

    @SerialName("eco")
    val eco: String?,

    @SerialName("gtl")
    val gtl: String?,

    @SerialName("k5")
    val k5: String?,

    ) {
    constructor() : this(
        eco = EMPTY_STRING,
        gtl = EMPTY_STRING,
        k5 = EMPTY_STRING,
    )
}
