package com.example.finalpam.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllInstrukturResponse(
    val status: Boolean,
    val message: String,
    val data: List<Instruktur>
)

@Serializable
data class InstrukturDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Instruktur
)

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

