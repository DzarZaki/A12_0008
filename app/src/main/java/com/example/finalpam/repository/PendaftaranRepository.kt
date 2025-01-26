package com.example.finalpam.repository

import com.example.finalpam.model.AllPendaftaranResponse
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.model.PendaftaranDetailResponse
import com.example.finalpam.service_api.PendaftaranService
import java.io.IOException

interface PendaftaranRepository {

    suspend fun insertPendaftaran(pendaftaran: Pendaftaran)

    suspend fun getPendaftaran(): List<Pendaftaran>

    suspend fun updatePendaftaran(idPendaftaran: String, pendaftaran: Pendaftaran)

    suspend fun deletePendaftaran(idPendaftaran: String)

    suspend fun getPendaftaranById(idPendaftaran: String): Pendaftaran
}

class NetworkPendaftaranRepository(
    private val pendaftaranApiService: PendaftaranService
) : PendaftaranRepository {
    override suspend fun insertPendaftaran(pendaftaran: Pendaftaran) {
        pendaftaranApiService.insertPendaftaran(pendaftaran)
    }

    override suspend fun updatePendaftaran(idPendaftaran: String, pendaftaran: Pendaftaran) {
        pendaftaranApiService.updatePendaftaran(idPendaftaran, pendaftaran)
    }

    override suspend fun deletePendaftaran(idPendaftaran: String) {
        try {
            val response = pendaftaranApiService.deletePendaftaran(idPendaftaran)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete pendaftaran. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPendaftaran(): List<Pendaftaran> =
        pendaftaranApiService.getAllPendaftaran()

    override suspend fun getPendaftaranById(idPendaftaran: String): Pendaftaran {
        return pendaftaranApiService.getPendaftaranById(idPendaftaran)
    }
}
