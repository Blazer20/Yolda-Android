package com.yolda.common

import com.yolda.data.local.CommonSubTypesLocal
import com.yolda.data.local.ElectroSubTypesLocal
import com.yolda.data.remote.Ad
import com.yolda.data.remote.FuelType
import com.yolda.data.remote.Geoposition
import com.yolda.data.remote.SignInResult
import com.yolda.data.remote.SocialMedia
import com.yolda.data.remote.Station
import com.yolda.data.remote.StationBrand
import com.yolda.data.remote.User
import com.yolda.data.remote.UserData

val emptySignInResult = SignInResult(
    data = UserData(
        id = EMPTY_STRING,
        email = EMPTY_STRING,
        name = EMPTY_STRING,
        photoUrl = EMPTY_STRING
    ),
    errorMessage = EMPTY_STRING
)

val emptyUser = User(
    email = EMPTY_STRING,
    name = EMPTY_STRING,
    role = EMPTY_STRING
)

val emptyUserData = UserData(
    id = EMPTY_STRING,
    email = EMPTY_STRING,
    name = EMPTY_STRING,
    photoUrl = EMPTY_STRING
)

val emptyAd = Ad(
    address = EMPTY_STRING,
    description = EMPTY_STRING,
    geoposition = null,
    imageURL = EMPTY_STRING,
    socialMedia = null,
    title = EMPTY_STRING
)

val emptyGeoposition = Geoposition(latitude = ZERO, longitude = ZERO)

val emptySocialMedia = SocialMedia(instagram = EMPTY_STRING)

val emptyStationBrand = StationBrand(legal_name = EMPTY_STRING, name = EMPTY_STRING, stations = emptyList())

val emptyFuelType = FuelType(electro = null, benzine = null, gasoline = null, diesel = null)

val emptyStation = Station(
    address = EMPTY_STRING,
    geoposition = emptyGeoposition,
    imageURL = EMPTY_STRING,
    fuel_types = emptyFuelType,
    legal_id = EMPTY_STRING,
    average_rating = DOUBLE_ZERO,
    rating_reviews_number = ZERO
)

val emptyCommonSubTypesLocal = CommonSubTypesLocal(
    name = EMPTY_STRING,
    price = EMPTY_STRING,
    selected = false
)

val emptyElectroSubTypesLocal = ElectroSubTypesLocal(
    name = EMPTY_STRING,
    electroSubTypes = emptyList()
)

