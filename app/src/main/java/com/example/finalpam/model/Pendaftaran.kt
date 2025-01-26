package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



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
