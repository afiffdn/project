package com.example.spksmpn4bunta.model.alternatif

import com.google.gson.annotations.SerializedName

data class PostAlternatifResp(
    @SerializedName("bahasa_indo")
    val bahasa_indo: Int,
    @SerializedName("bahasa_ing")
    val bahasa_ing: Int,
    @SerializedName("biologi")
    val biologi: Int,
    @SerializedName("ekonomi")
    val ekonomi: Int,
    @SerializedName("fisika")
    val fisika: Int,
    @SerializedName("izin")
    val izin: Int,
    @SerializedName("ket_bahasa_indo")
    val ket_bahasa_indo: Int,
    @SerializedName("ket_bahasa_ing")
    val ket_bahasa_ing: Int,
    @SerializedName("ket_biologi")
    val ket_biologi: Int,
    @SerializedName("ket_ekonomi")
    val ket_ekonomi: Int,
    @SerializedName("ket_fisika")
    val ket_fisika: Int,
    @SerializedName("ket_kimia")
    val ket_kimia: Int,
    @SerializedName("ket_matematika")
    val ket_matematika: Int,
    @SerializedName("ket_pend_agama")
    val ket_pend_agama: Int,
    @SerializedName("ket_pend_pancasila")
    val ket_pend_pancasila: Int,
    @SerializedName("ket_penjas")
    val ket_penjas: Int,
    @SerializedName("ket_prakarya_dan_kwh")
    val ket_prakarya_dan_kwh: Int,
    @SerializedName("ket_sejarah_indo")
    val ket_sejarah_indo: Int,
    @SerializedName("ket_seni_budaya")
    val ket_seni_budaya: Int,
    @SerializedName("ket_sosiologi")
    val ket_sosiologi: Int,
    @SerializedName("kimia")
    val kimia: Int,
    @SerializedName("mtk")
    val mtk: Int,
    @SerializedName("olahraga")
    val olahraga: Int,
    @SerializedName("pend_agama")
    val pend_agama: Int,
    @SerializedName("pend_pancasila")
    val pend_pancasila: Int,
    @SerializedName("penjas")
    val penjas: Int,
    @SerializedName("pmr")
    val pmr: Int,
    @SerializedName("prakarya_dan_kwh")
    val prakarya_dan_kwh: Int,
    @SerializedName("pramuka")
    val pramuka: Int,
    @SerializedName("sakit")
    val sakit: Int,
    @SerializedName("sejarah_indo")
    val sejarah_indo: Int,
    @SerializedName("semester")
    val semester: Int,
    @SerializedName("seni_budaya")
    val seni_budaya: Int,
    @SerializedName("sikap_sosial")
    val sikap_sosial: Int,
    @SerializedName("sikap_spiritual")
    val sikap_spiritual: Int,
    @SerializedName("sosiologi")
    val sosiologi: Int,
    @SerializedName("tanpa_keterangan")
    val tanpa_keterangan: Int,
    @SerializedName("users_id")
    val users_id: String
)