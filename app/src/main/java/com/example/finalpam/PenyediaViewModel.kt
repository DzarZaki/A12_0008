package com.example.finalpam

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalpam.ui.kursus.viewmodel.HomeKursusViewModel

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
    }
}

// Ekstensi untuk mendapatkan aplikasi
fun CreationExtras.aplikasiFinal(): FinalApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FinalApplications)