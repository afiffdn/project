package com.example.spksmpn4bunta.datastore

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("sebagai")
    val sebagai: String
)
