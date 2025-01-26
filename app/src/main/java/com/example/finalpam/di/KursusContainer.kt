package com.example.finalpam.di

import com.example.finalpam.repository.KursusRepository
import com.example.finalpam.repository.NetworkKursusRepository
import com.example.finalpam.service_api.KursusService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface KursusAppContainer {
    val kursusRepository: KursusRepository
}

class KursusContainer : KursusAppContainer {

    private val baseUrl = "http://10.0.2.2:80/umyTI/" // localhost diganti IP jika run di HP
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val kursusService: KursusService by lazy {
        retrofit.create(KursusService::class.java)
    }

    override val kursusRepository: KursusRepository by lazy {
        NetworkKursusRepository(kursusService)
    }
}
