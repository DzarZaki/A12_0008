package com.example.finalpam.di

import com.example.finalpam.repository.NetworkSiswaRepository
import com.example.finalpam.repository.SiswaRepository
import com.example.finalpam.service_api.SiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface SiswaAppContainer {
    val siswaRepository: SiswaRepository
}

class SiswaContainer : SiswaAppContainer {

    private val baseUrl = "http://10.0.2.2:80/umyTI/" // localhost diganti IP jika run di HP
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val siswaService: SiswaService by lazy {
        retrofit.create(SiswaService::class.java)
    }

    override val siswaRepository: SiswaRepository by lazy {
        NetworkSiswaRepository(siswaService)
    }
}
