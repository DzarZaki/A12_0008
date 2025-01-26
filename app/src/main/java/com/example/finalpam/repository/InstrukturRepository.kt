package com.example.finalpam.repository

import InstrukturService
import com.example.finalpam.model.Instruktur

import java.io.IOException

interface InstrukturRepository {

    suspend fun insertInstruktur(instruktur: Instruktur)

    suspend fun getInstruktur(): List<Instruktur>

    suspend fun updateInstruktur(idInstruktur: String, instruktur: Instruktur)

    suspend fun deleteInstruktur(idInstruktur: String)

    suspend fun getInstrukturById(idInstruktur: String): Instruktur
}

class NetworkInstrukturRepository(
    private val instrukturApiService: InstrukturService
) : InstrukturRepository {
    override suspend fun insertInstruktur(instruktur: Instruktur) {
        instrukturApiService.insertInstruktur(instruktur)
    }

    override suspend fun updateInstruktur(idInstruktur: String, instruktur: Instruktur) {
        instrukturApiService.updateInstruktur(idInstruktur, instruktur)
    }

    override suspend fun deleteInstruktur(idInstruktur: String) {
        try {
            val response = instrukturApiService.deleteInstruktur(idInstruktur)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete instruktur. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getInstruktur(): List<Instruktur> =
        instrukturApiService.getAllInstruktur()

    override suspend fun getInstrukturById(idInstruktur: String): Instruktur {
        return instrukturApiService.getInstrukturById(idInstruktur)
    }
}
