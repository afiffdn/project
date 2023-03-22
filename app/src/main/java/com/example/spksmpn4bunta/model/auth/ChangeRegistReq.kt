package com.example.spksmpn4bunta.model.auth

import com.google.gson.annotations.SerializedName

data class ChangeRegistReq(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
)
