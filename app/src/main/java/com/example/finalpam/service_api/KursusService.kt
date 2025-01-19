package com.example.finalpam.service_api

import com.example.finalpam.model.AllKursusResponse
import com.example.finalpam.model.Kursus
import com.example.finalpam.model.KursusDetailResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KursusService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    // Insert kursus
    @POST("kursus")
    suspend fun insertKursus(@Body kursus: Kursus): retrofit2.Response<Void>

    // Get semua kursus
    @GET("kursus")
    suspend fun getAllKursus(): AllKursusResponse

    // Get detail kursus berdasarkan ID
    @GET("kursus/{id_kursus}")
    suspend fun getKursusById(@Path("id_kursus") idKursus: String): KursusDetailResponse

    // Update kursus
    @PUT("kursus/{id_kursus}")
    suspend fun updateKursus(
        @Path("id_kursus") idKursus: String,
        @Body kursus: Kursus
    ): retrofit2.Response<Void>

    // Delete kursus
    @DELETE("kursus/{id_kursus}")
    suspend fun deleteKursus(@Path("id_kursus")idKursus: String): retrofit2.Response<Void>
}