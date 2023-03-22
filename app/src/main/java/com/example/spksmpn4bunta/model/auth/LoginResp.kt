package com.example.spksmpn4bunta.model.auth

import com.google.gson.annotations.SerializedName

data class LoginResp(
    @SerializedName("id_users")
    val id_users: Int,
    @SerializedName("token")
    val token: String
)
