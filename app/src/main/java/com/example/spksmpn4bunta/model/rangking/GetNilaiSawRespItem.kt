package com.example.spksmpn4bunta.model.rangking

import com.google.gson.annotations.SerializedName

//data class GetNilaiSawResp(
//    @SerializedName("nilai")
//    val nilai: List<GetNilaiSawRespItem>
//)


data class GetNilaiSawRespItem(
    @SerializedName("users_id")
    val users_id: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("hasil")
    val hasil: String
)