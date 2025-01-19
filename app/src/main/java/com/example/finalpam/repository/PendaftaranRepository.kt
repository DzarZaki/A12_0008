package com.example.finalpam.repository

import com.example.finalpam.model.AllPendaftaranResponse
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.model.PendaftaranDetailResponse
import com.example.finalpam.service_api.PendaftaranService
import java.io.IOException

interface PendaftaranRepository {
    suspend fun insertPendaftaran(pendaftaran: Pendaftaran)
    suspend fun getAllPendaftaran(): AllPendaftaranResponse
    suspend fun updatePendaftaran(idPendaftaran: String, pendaftaran: Pendaftaran)
    suspend fun deletePendaftaran(idPendaftaran: String)
    suspend fun getPendaftaranById(idPendaftaran: String): PendaftaranDetailResponse
}

class NetworkPendaftaranRepository(
    private val pendaftaranService: PendaftaranService
) : PendaftaranRepository {
    override suspend fun insertPendaftaran(pendaftaran: Pendaftaran) {
        pendaftaranService.insertPendaftaran(pendaftaran)
    }

    override suspend fun getAllPendaftaran(): AllPendaftaranResponse {
        return pendaftaranService.getAllPendaftaran()
    }

    override suspend fun updatePendaftaran(idPendaftaran: String, pendaftaran: Pendaftaran) {
        pendaftaranService.updatePendaftaran(idPendaftaran, pendaftaran)
    }

    override suspend fun deletePendaftaran(idPendaftaran: String) {
        val response = pendaftaranService.deletePendaftaran(idPendaftaran)
        if (!response.isSuccessful) {
            throw IOException("Failed to delete pendaftaran. HTTP Status code: ${response.code()}")
        }
    }

    override suspend fun getPendaftaranById(idPendaftaran: String): PendaftaranDetailResponse {
        return pendaftaranService.getPendaftaranById(idPendaftaran)
    }
}
