package com.example.finalpam.service_api

import com.example.finalpam.model.AllSiswaResponse
import com.example.finalpam.model.Siswa
import com.example.finalpam.model.SiswaDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SiswaService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @POST("siswa/tambahsiswa.php")
    suspend fun insertSiswa(@Body siswa: Siswa)

    @GET("siswa/bacasiswa.php")
    suspend fun getAllSiswa(): List<Siswa>

    @GET("siswa/bacasiswa1.php")
    suspend fun getSiswaById(@Query("id_siswa") idSiswa: String): Siswa

    @PUT("siswa/editsiswa.php")
    suspend fun updateSiswa(@Query("id_siswa") idSiswa: String, @Body siswa: Siswa)

    @DELETE("siswa/hapussiswa.php")
    suspend fun deleteSiswa(@Query("id_siswa") idSiswa: String): Response<Void>
}
