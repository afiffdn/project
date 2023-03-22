package com.example.spksmpn4bunta.model.datadiri

import com.google.gson.annotations.SerializedName

data class DataDiriReq(
    @SerializedName("nisn")
    val nisn: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("jenis_kelamin")
    val jenis_kelamin: String,
    @SerializedName("agama")
    val agama: String,
    @SerializedName("kelas")
    val kelas: String,
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("nip")
    val nip: String,
    @SerializedName("jabatan")
    val jabatan: String,
)