package com.example.finalpam.service_api

import com.example.finalpam.model.AllInstrukturResponse
import com.example.finalpam.model.Instruktur
import com.example.finalpam.model.InstrukturDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface InstrukturService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )

    @POST("instruktur")
    suspend fun insertInstruktur(@Body instruktur: Instruktur) : Response<Void>


    @GET("instruktur")
    suspend fun getAllInstruktur(): AllInstrukturResponse

    @GET("instruktur/{id_instruktur}")
    suspend fun getInstrukturById(@Path("id_instruktur") idInstruktur: String): InstrukturDetailResponse

    @PUT("instruktur/{id_instruktur}")
    suspend fun updateInstruktur(
        @Path("id_instruktur") idInstruktur: String,
        @Body instruktur: Instruktur
    ) : retrofit2.Response<Void>


    @DELETE("instruktur/{id_instruktur}")
    suspend fun deleteInstruktur(@Path("id_instruktur") idInstruktur: String) : Response<Void>

}
