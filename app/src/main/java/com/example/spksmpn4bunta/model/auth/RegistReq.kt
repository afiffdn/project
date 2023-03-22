package com.example.spksmpn4bunta.model.auth


import com.google.gson.annotations.SerializedName

data class RegistReq(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("sebagai")
    val sebagai: String
)