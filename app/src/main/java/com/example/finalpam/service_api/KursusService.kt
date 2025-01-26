package com.example.finalpam.service_api

import com.example.finalpam.model.Kursus
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface KursusService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @POST("kursus/tambahkursus.php")
    suspend fun insertKursus(@Body kursus: Kursus): Response<Void>

    @GET("kursus/bacakursus.php")
    suspend fun getAllKursus(): List<Kursus>

    @GET("kursus/baca1kursus.php")
    suspend fun getKursusById(@Query("id_kursus") idKursus: String): Kursus

    @PUT("kursus/editkursus.php")
    suspend fun updateKursus(@Query("id_kursus") idKursus: String, @Body kursus: Kursus) : Response<Void>

    @DELETE("kursus/hapuskursus.php")
    suspend fun deleteKursus(@Query("id_kursus") idKursus: String): Response<Void>
}
