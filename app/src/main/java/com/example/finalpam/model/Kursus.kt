package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class Kursus(
    @SerialName("id_kursus")
    val idKursus: String,
    @SerialName("nama_kursus")
    val namaKursus: String,
    val deskripsi: String,
    val kategori: String,
    val harga: Double
)

