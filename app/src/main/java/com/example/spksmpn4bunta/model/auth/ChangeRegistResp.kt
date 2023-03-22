package com.example.spksmpn4bunta.model.auth

import com.google.gson.annotations.SerializedName

data class ChangeRegistResp(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
)
