package com.yolda.data.remote

import kotlinx.serialization.Serializable

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

@Serializable
data class UserData(
    val id: String,
    val email: String,
    val name: String?,
    val photoUrl: String
)
