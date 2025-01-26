package com.example.finalpam

import android.app.Application
import com.example.finalpam.di.InstrukturAppContainer
import com.example.finalpam.di.InstrukturContainer
import com.example.finalpam.di.KursusAppContainer
import com.example.finalpam.di.KursusContainer
import com.example.finalpam.di.PendaftaranAppContainer
import com.example.finalpam.di.PendaftaranContainer
import com.example.finalpam.di.SiswaAppContainer
import com.example.finalpam.di.SiswaContainer

class FinalApplications : Application() {
    lateinit var instrukturContainer: InstrukturAppContainer
    lateinit var kursusContainer: KursusAppContainer
    lateinit var pendaftaranContainer: PendaftaranAppContainer
    lateinit var siswaContainer: SiswaAppContainer

    override fun onCreate() {
        super.onCreate()
        instrukturContainer = InstrukturContainer()
        kursusContainer = KursusContainer()
        pendaftaranContainer = PendaftaranContainer()
        siswaContainer = SiswaContainer()
    }
}