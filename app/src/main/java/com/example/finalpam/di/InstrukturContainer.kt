package com.example.finalpam.di

import InstrukturService
import com.example.finalpam.repository.InstrukturRepository
import com.example.finalpam.repository.NetworkInstrukturRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface InstrukturAppContainer {
    val instrukturRepository: InstrukturRepository
}

class InstrukturContainer : InstrukturAppContainer {

    private val baseUrl = "http://10.0.2.2:80/umyTI/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val instrukturService: InstrukturService by lazy {
        retrofit.create(InstrukturService::class.java)
    }

    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }
}
