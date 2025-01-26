package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllPendaftaranResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pendaftaran>
)

@Serializable
data class PendaftaranDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Pendaftaran
)

@Serializable
data class Pendaftaran(
    val idPendaftaran: String,
    @SerialName("id_siswa")
    val idSiswa: String,
    @SerialName("id_kursus")
    val idKursus: String,
    @SerialName("tanggal_pendaftaran")
    val tanggalpendaftaran: String,
    val status: String
)
