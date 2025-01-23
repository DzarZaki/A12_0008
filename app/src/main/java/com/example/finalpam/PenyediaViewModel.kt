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
        // Inisialisasi ViewModel untuk HomeKursus
        initializer { HomeKursusViewModel(aplikasiFinal().container.kursusRepository) }
        // Inisialisasi ViewModel untuk InsertKursus
        initializer { InsertKursusViewModel(aplikasiFinal().container.kursusRepository) }
        // Inisialisasi ViewModel untuk DetailKursus
        initializer {
            DetailKursusViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.kursusRepository
            )
        }
        // Inisialisasi ViewModel untuk UpdateKursus
        initializer {
            UpdateKursusViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.kursusRepository
            )
        }
        // Inisialisasi ViewModel untuk HomeSiswa
        initializer { HomeSiswaViewModel(aplikasiFinal().container.siswaRepository) }
        // Inisialisasi ViewModel untuk InsertSiswa
        initializer { InsertSiswaViewModel(aplikasiFinal().container.siswaRepository) }
        // Inisialisasi ViewModel untuk DetailSiswa
        initializer {
            DetailSiswaViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.siswaRepository
            )
        }
        // Inisialisasi ViewModel untuk UpdateSiswa
        initializer {
            UpdateSiswaViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.siswaRepository
            )
        }
        // Inisialisasi ViewModel untuk HomeInstruktur
        initializer { HomeInstrukturViewModel(aplikasiFinal().container.instrukturRepository) }
        // Inisialisasi ViewModel untuk InsertInstruktur
        initializer { InsertInstrukturViewModel(aplikasiFinal().container.instrukturRepository) }
        // Inisialisasi ViewModel untuk DetailInstruktur
        initializer {
            DetailInstrukturViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.instrukturRepository
            )
        }
        // Inisialisasi ViewModel untuk UpdateInstruktur
        initializer {
            UpdateInstrukturViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.instrukturRepository
            )
        }
        // Inisialisasi ViewModel untuk HomePendaftaran
        initializer { HomePendaftaranViewModel(aplikasiFinal().container.pendaftaranRepository) }
        // Inisialisasi ViewModel untuk InsertPendaftaran
        initializer { InsertPendaftaranViewModel(aplikasiFinal().container.pendaftaranRepository) }
        // Inisialisasi ViewModel untuk DetailPendaftaran
        initializer {
            DetailPendaftaranViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.pendaftaranRepository
            )
        }
        // Inisialisasi ViewModel untuk UpdatePendaftaran
        initializer {
            UpdatePendaftaranViewModel(
                createSavedStateHandle(),
                aplikasiFinal().container.pendaftaranRepository
            )
        }
    }
}

// Ekstensi untuk mendapatkan aplikasi
fun CreationExtras.aplikasiFinal(): FinalApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinalApplications)
