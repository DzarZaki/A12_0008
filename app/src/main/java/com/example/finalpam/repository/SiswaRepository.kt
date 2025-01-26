package com.example.finalpam.repository

import com.example.finalpam.model.AllSiswaResponse
import com.example.finalpam.model.Siswa
import com.example.finalpam.model.SiswaDetailResponse
import com.example.finalpam.service_api.SiswaService
import java.io.IOException

interface SiswaRepository {

    suspend fun insertSiswa(siswa: Siswa)

    suspend fun getSiswa(): List<Siswa>

    suspend fun updateSiswa(idSiswa: String, siswa: Siswa)

    suspend fun deleteSiswa(idSiswa: String)

    suspend fun getSiswaById(idSiswa: String): Siswa
}

class NetworkSiswaRepository(
    private val siswaApiService: SiswaService
) : SiswaRepository {
    override suspend fun insertSiswa(siswa: Siswa) {
        siswaApiService.insertSiswa(siswa)
    }

    override suspend fun updateSiswa(idSiswa: String, siswa: Siswa) {
        siswaApiService.updateSiswa(idSiswa, siswa)
    }

    override suspend fun deleteSiswa(idSiswa: String) {
        try {
            val response = siswaApiService.deleteSiswa(idSiswa)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete siswa. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getSiswa(): List<Siswa> =
        siswaApiService.getAllSiswa()

    override suspend fun getSiswaById(idSiswa: String): Siswa {
        return siswaApiService.getSiswaById(idSiswa)
    }
}
