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

interface SiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("siswa")
    suspend fun insertSiswa(@Body siswa: Siswa): Response<Void>

    @GET("siswa")
    suspend fun getAllSiswa(): AllSiswaResponse

    @GET("siswa/{id_siswa}")
    suspend fun getSiswaById(@Path("id_siswa") idSiswa: String): SiswaDetailResponse

    @PUT("siswa/{id_siswa}")
    suspend fun updateSiswa(
        @Path("id_siswa") idSiswa: String,
        @Body siswa: Siswa
    ): Response<Void>

    @DELETE("siswa/{id_siswa}")
    suspend fun deleteSiswa(@Path("id_siswa") idSiswa: String): Response<Void>
}