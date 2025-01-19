package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllSiswaResponse(
    val status: Boolean,
    val message: String,
    val data: List<Siswa>
)

@Serializable
data class SiswaDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Siswa
)

@Serializable
data class Siswa(
    @SerialName("id_siswa")
    val idSiswa: String,
    @SerialName("nama_siswa")
    val nama: String,
    val email: String,
    @SerialName("nomor_telepon")
    val nomorTelepon: String
)

