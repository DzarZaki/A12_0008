package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllKursusResponse(
    val status: Boolean,
    val message: String,
    val data: List<Kursus>
)

@Serializable
data class KursusDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Kursus
)

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

