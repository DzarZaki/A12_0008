package com.example.finalpam.repository

import com.example.finalpam.model.AllSiswaResponse
import com.example.finalpam.model.Siswa
import com.example.finalpam.model.SiswaDetailResponse
import com.example.finalpam.service_api.SiswaService
import java.io.IOException

interface SiswaRepository {
    suspend fun insertSiswa(siswa: Siswa)
    suspend fun getAllSiswa(): AllSiswaResponse
    suspend fun updateSiswa(idSiswa: String, siswa: Siswa)
    suspend fun deleteSiswa(idSiswa: String)
    suspend fun getSiswaById(idSiswa: String): SiswaDetailResponse
}

class NetworkSiswaRepository(
    private val siswaService: SiswaService
) : SiswaRepository {
    override suspend fun insertSiswa(siswa: Siswa) {
        siswaService.insertSiswa(siswa)
    }

    override suspend fun getAllSiswa(): AllSiswaResponse {
        return siswaService.getAllSiswa()
    }

    override suspend fun updateSiswa(idSiswa: String, siswa: Siswa) {
        siswaService.updateSiswa(idSiswa, siswa)
    }

    override suspend fun deleteSiswa(idSiswa: String) {
        val response = siswaService.deleteSiswa(idSiswa)
        if (!response.isSuccessful) {
            throw IOException("Failed to delete siswa. HTTP Status code: ${response.code()}")
        }
    }

    override suspend fun getSiswaById(idSiswa: String): SiswaDetailResponse {
        return siswaService.getSiswaById(idSiswa)
    }
}
