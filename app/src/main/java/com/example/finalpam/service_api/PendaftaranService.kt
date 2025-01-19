package com.example.finalpam.service_api

import com.example.finalpam.model.AllPendaftaranResponse
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.model.PendaftaranDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PendaftaranService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("pendaftaran")
    suspend fun insertPendaftaran(@Body pendaftaran: Pendaftaran): Response<Void>

    @GET("pendaftaran")
    suspend fun getAllPendaftaran(): AllPendaftaranResponse

    @GET("pendaftaran/{id_pendaftaran}")
    suspend fun getPendaftaranById(@Path("id_pendaftaran") idPendaftaran: String): PendaftaranDetailResponse

    @PUT("pendaftaran/{id_pendaftaran}")
    suspend fun updatePendaftaran(
        @Path("id_pendaftaran") idPendaftaran: String,
        @Body pendaftaran: Pendaftaran
    ): Response<Void>

    @DELETE("pendaftaran/{id_pendaftaran}")
    suspend fun deletePendaftaran(@Path("id_pendaftaran") idPendaftaran: String): Response<Void>
}