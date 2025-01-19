package com.example.finalpam.repository

import com.example.finalpam.model.AllInstrukturResponse
import com.example.finalpam.model.Instruktur
import com.example.finalpam.model.InstrukturDetailResponse
import com.example.finalpam.service_api.InstrukturService
import java.io.IOException

interface InstrukturRepository {
    suspend fun insertInstruktur(instruktur: Instruktur)
    suspend fun getAllInstruktur(): AllInstrukturResponse
    suspend fun updateInstruktur(idInstruktur: String, instruktur: Instruktur)
    suspend fun deleteInstruktur(idInstruktur: String)
    suspend fun getInstrukturById(idInstruktur: String): InstrukturDetailResponse
}

class NetworkInstrukturRepository(
    private val instrukturService: InstrukturService
) : InstrukturRepository {
    override suspend fun insertInstruktur(instruktur: Instruktur) {
        instrukturService.insertInstruktur(instruktur)
    }

    override suspend fun getAllInstruktur(): AllInstrukturResponse {
        return instrukturService.getAllInstruktur()
    }

    override suspend fun updateInstruktur(idInstruktur: String, instruktur: Instruktur) {
        instrukturService.updateInstruktur(idInstruktur, instruktur)
    }

    override suspend fun deleteInstruktur(idInstruktur: String) {
        val response = instrukturService.deleteInstruktur(idInstruktur)
        if (!response.isSuccessful) {
            throw IOException("Failed to delete instruktur. HTTP Status code: ${response.code()}")
        }
    }

    override suspend fun getInstrukturById(idInstruktur: String): InstrukturDetailResponse {
        return instrukturService.getInstrukturById(idInstruktur)
    }
}
