package com.example.finalpam.di

import com.example.finalpam.repository.InstrukturRepository
import com.example.finalpam.repository.KursusRepository
import com.example.finalpam.repository.NetworkInstrukturRepository
import com.example.finalpam.repository.NetworkKursusRepository
import com.example.finalpam.repository.NetworkPendaftaranRepository
import com.example.finalpam.repository.NetworkSiswaRepository
import com.example.finalpam.repository.PendaftaranRepository
import com.example.finalpam.repository.SiswaRepository
import com.example.finalpam.service_api.InstrukturService
import com.example.finalpam.service_api.KursusService
import com.example.finalpam.service_api.PendaftaranService
import com.example.finalpam.service_api.SiswaService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

// Interface untuk AppContainer
interface AppContainer {
    val kursusRepository: KursusRepository
    val siswaRepository: SiswaRepository
    val instrukturRepository: InstrukturRepository
    val pendaftaranRepository: PendaftaranRepository
}

// Implementasi AppContainer
class MahasiswaContainer : AppContainer {

    // Base URL untuk API
    private val baseUrl = "http://10.0.2.2:3000/api/" // localhost untuk emulator Android
    private val json = Json { ignoreUnknownKeys = true }

    // Retrofit instance
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    // Service untuk setiap entitas
    private val kursusService: KursusService by lazy {
        retrofit.create(KursusService::class.java)
    }

    private val siswaService: SiswaService by lazy {
        retrofit.create(SiswaService::class.java)
    }

    private val instrukturService: InstrukturService by lazy {
        retrofit.create(InstrukturService::class.java)
    }

    private val pendaftaranService: PendaftaranService by lazy {
        retrofit.create(PendaftaranService::class.java)
    }

    // Repository untuk setiap entitas
    override val kursusRepository: KursusRepository by lazy {
        NetworkKursusRepository(kursusService)
    }

    override val siswaRepository: SiswaRepository by lazy {
        NetworkSiswaRepository(siswaService)
    }

    override val instrukturRepository: InstrukturRepository by lazy {
        NetworkInstrukturRepository(instrukturService)
    }

    override val pendaftaranRepository: PendaftaranRepository by lazy {
        NetworkPendaftaranRepository(pendaftaranService)
    }
}