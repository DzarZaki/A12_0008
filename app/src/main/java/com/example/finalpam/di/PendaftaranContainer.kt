package com.example.finalpam.di

import com.example.finalpam.repository.NetworkPendaftaranRepository
import com.example.finalpam.repository.PendaftaranRepository
import com.example.finalpam.service_api.PendaftaranService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface PendaftaranAppContainer {
    val pendaftaranRepository: PendaftaranRepository
}

class PendaftaranContainer : PendaftaranAppContainer {

    private val baseUrl = "http://10.0.2.2:80/umyTI/" // localhost diganti IP jika run di HP
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pendaftaranService: PendaftaranService by lazy {
        retrofit.create(PendaftaranService::class.java)
    }

    override val pendaftaranRepository: PendaftaranRepository by lazy {
        NetworkPendaftaranRepository(pendaftaranService)
    }
}
