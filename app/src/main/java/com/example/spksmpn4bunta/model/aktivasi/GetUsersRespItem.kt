package com.example.spksmpn4bunta.model.aktivasi

import com.google.gson.annotations.SerializedName

data class GetUsersRespItem(
    @SerializedName("id_users")
    val id_users: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("sebagai")
    val sebagai: String,
    @SerializedName("is_active")
    val is_active: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("deleted_at")
    val deleted_at: Any,
)