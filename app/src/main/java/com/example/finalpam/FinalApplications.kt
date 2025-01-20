package com.example.finalpam

import android.app.Application
import com.example.finalpam.di.AppContainer
import com.example.finalpam.di.MahasiswaContainer

class FinalApplications : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}