package com.example.finalpam.repository

import com.example.finalpam.model.Kursus
import com.example.finalpam.service_api.KursusService
import java.io.IOException

interface KursusRepository {

    suspend fun insertKursus(kursus: Kursus)

    suspend fun getKursus(): List<Kursus>

    suspend fun updateKursus(idKursus: String, kursus: Kursus)

    suspend fun deleteKursus(idKursus: String)

    suspend fun getKursusById(idKursus: String): Kursus
}

class NetworkKursusRepository(
    private val kursusApiService: KursusService
) : KursusRepository {
    override suspend fun insertKursus(kursus: Kursus) {
        kursusApiService.insertKursus(kursus)
    }

    override suspend fun updateKursus(idKursus: String, kursus: Kursus) {
        kursusApiService.updateKursus(idKursus, kursus)
    }

    override suspend fun deleteKursus(idKursus: String) {
        try {
            val response = kursusApiService.deleteKursus(idKursus)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kursus. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKursus(): List<Kursus> =
        kursusApiService.getAllKursus()

    override suspend fun getKursusById(idKursus: String): Kursus {
        return kursusApiService.getKursusById(idKursus)
    }
}
