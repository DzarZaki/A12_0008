package com.example.finalpam.repository

import com.example.finalpam.model.AllKursusResponse
import com.example.finalpam.model.Kursus
import com.example.finalpam.model.KursusDetailResponse
import com.example.finalpam.service_api.KursusService
import java.io.IOException

interface KursusRepository {
    suspend fun insertKursus(kursus: Kursus)
    suspend fun getAllKursus(): AllKursusResponse
    suspend fun updateKursus(idKursus: String, kursus: Kursus)
    suspend fun deleteKursus(idKursus: String)
    suspend fun getKursusById(idKursus: String): KursusDetailResponse
}

class NetworkKursusRepository(
    private val kursusService: KursusService
) : KursusRepository {
    override suspend fun insertKursus(kursus: Kursus) {
        kursusService.insertKursus(kursus)
    }

    override suspend fun getAllKursus(): AllKursusResponse {
        return kursusService.getAllKursus()
    }

    override suspend fun updateKursus(idKursus: String, kursus: Kursus) {
        kursusService.updateKursus(idKursus, kursus)
    }

    override suspend fun deleteKursus(idKursus: String) {
        val response = kursusService.deleteKursus(idKursus)
        if (!response.isSuccessful) {
            throw IOException("Failed to delete kursus. HTTP Status code: ${response.code()}")
        }
    }

    override suspend fun getKursusById(idKursus: String): KursusDetailResponse {
        return kursusService.getKursusById(idKursus)
    }
}
