package com.example.finalpam

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalpam.ui.instruktur.viewmodel.DetailInstrukturViewModel
import com.example.finalpam.ui.instruktur.viewmodel.HomeInstrukturViewModel
import com.example.finalpam.ui.instruktur.viewmodel.InsertInstrukturViewModel
import com.example.finalpam.ui.instruktur.viewmodel.UpdateInstrukturViewModel
import com.example.finalpam.ui.kursus.viewmodel.DetailKursusViewModel
import com.example.finalpam.ui.kursus.viewmodel.HomeKursusViewModel
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusViewModel
import com.example.finalpam.ui.kursus.viewmodel.UpdateKursusViewModel
import com.example.finalpam.ui.pendaftaran.viewmodel.DetailPendaftaranViewModel
import com.example.finalpam.ui.pendaftaran.viewmodel.HomePendaftaranViewModel
import com.example.finalpam.ui.pendaftaran.viewmodel.InsertPendaftaranViewModel
import com.example.finalpam.ui.pendaftaran.viewmodel.UpdatePendaftaranViewModel
import com.example.finalpam.ui.siswa.viewmodel.DetailSiswaViewModel
import com.example.finalpam.ui.siswa.viewmodel.HomeSiswaViewModel
import com.example.finalpam.ui.siswa.viewmodel.InsertSiswaViewModel
import com.example.finalpam.ui.siswa.viewmodel.UpdateSiswaViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        // ViewModel untuk Kursus
        initializer { HomeKursusViewModel(aplikasiFinal().kursusContainer.kursusRepository) }
        initializer { InsertKursusViewModel(aplikasiFinal().kursusContainer.kursusRepository) }
        initializer {
            DetailKursusViewModel(
                createSavedStateHandle(),
                aplikasiFinal().kursusContainer.kursusRepository
            )
        }
        initializer {
            UpdateKursusViewModel(
                createSavedStateHandle(),
                aplikasiFinal().kursusContainer.kursusRepository
            )
        }
        // ViewModel untuk Siswa
        initializer { HomeSiswaViewModel(aplikasiFinal().siswaContainer.siswaRepository) }
        initializer { InsertSiswaViewModel(aplikasiFinal().siswaContainer.siswaRepository) }
        initializer {
            DetailSiswaViewModel(
                createSavedStateHandle(),
                aplikasiFinal().siswaContainer.siswaRepository
            )
        }
        initializer {
            UpdateSiswaViewModel(
                createSavedStateHandle(),
                aplikasiFinal().siswaContainer.siswaRepository
            )
        }
        // ViewModel untuk Instruktur
        initializer { HomeInstrukturViewModel(aplikasiFinal().instrukturContainer.instrukturRepository) }
        initializer { InsertInstrukturViewModel(aplikasiFinal().instrukturContainer.instrukturRepository) }
        initializer {
            DetailInstrukturViewModel(
                createSavedStateHandle(),
                aplikasiFinal().instrukturContainer.instrukturRepository
            )
        }
        initializer {
            UpdateInstrukturViewModel(
                createSavedStateHandle(),
                aplikasiFinal().instrukturContainer.instrukturRepository
            )
        }
        // ViewModel untuk Pendaftaran
        initializer { HomePendaftaranViewModel(aplikasiFinal().pendaftaranContainer.pendaftaranRepository) }
        initializer { InsertPendaftaranViewModel(aplikasiFinal().pendaftaranContainer.pendaftaranRepository) }
        initializer {
            DetailPendaftaranViewModel(
                createSavedStateHandle(),
                aplikasiFinal().pendaftaranContainer.pendaftaranRepository
            )
        }
        initializer {
            UpdatePendaftaranViewModel(
                createSavedStateHandle(),
                aplikasiFinal().pendaftaranContainer.pendaftaranRepository
            )
        }
    }
}

// Ekstensi untuk mendapatkan aplikasi
fun CreationExtras.aplikasiFinal(): FinalApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinalApplications)
