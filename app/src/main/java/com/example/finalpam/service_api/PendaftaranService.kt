package com.example.finalpam.service_api

import com.example.finalpam.model.Pendaftaran
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PendaftaranService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @POST("pendaftaran/tambahpendaftaran.php")
    suspend fun insertPendaftaran(@Body pendaftaran: Pendaftaran)

    @GET("pendaftaran/bacapendaftaran.php")
    suspend fun getAllPendaftaran(): List<Pendaftaran>

    @GET("pendaftaran/baca1pendaftaran.php")
    suspend fun getPendaftaranById(@Query("id_pendaftaran") idPendaftaran: String): Pendaftaran

    @PUT("pendaftaran/editpendaftaran.php")
    suspend fun updatePendaftaran(@Query("id_pendaftaran") idPendaftaran: String, @Body pendaftaran: Pendaftaran)

    @DELETE("pendaftaran/hapuspendaftaran.php")
    suspend fun deletePendaftaran(@Query("id_pendaftaran") idPendaftaran: String): Response<Void>
}
