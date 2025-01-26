package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Instruktur(
    @SerialName("id_instruktur")
    val idInstruktur: String,
    @SerialName("nama_instruktur")
    val nama: String,
    val email: String,
    @SerialName("nomor_telepon")
    val nomorTelepon: String,
    val deskripsi: String
)

