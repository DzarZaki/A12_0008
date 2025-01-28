package com.example.finalpam.ui.kursus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Kursus
import com.example.finalpam.repository.KursusRepository
import kotlinx.coroutines.launch

class InsertKursusViewModel(private val kursusRepository: KursusRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertKursusUiState())
        private set

    fun updateInsertKursusState(insertUiEvent: InsertKursusUiEvent) {
        uiState = InsertKursusUiState(insertUiEvent = insertUiEvent)
    }

    fun insertKursus() {
        viewModelScope.launch {
            try {
                kursusRepository.insertKursus(uiState.insertUiEvent.toKursus())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertKursusUiState(
    val insertUiEvent: InsertKursusUiEvent = InsertKursusUiEvent()
)

data class InsertKursusUiEvent(
    val idKursus: String = "",
    val namaKursus: String = "",
    val deskripsi: String = "",
    val kategori: String = "",
    val harga: Double = 0.0,
    val idInstruktur: String = ""
)


fun InsertKursusUiEvent.toKursus(): Kursus = Kursus(
    idKursus = idKursus,
    namaKursus = namaKursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    idInstruktur = idInstruktur
)


fun Kursus.toUiStateKursus(): InsertKursusUiState = InsertKursusUiState(
    insertUiEvent = toInsertKursusUiEvent()
)


fun Kursus.toInsertKursusUiEvent(): InsertKursusUiEvent = InsertKursusUiEvent(
    idKursus = idKursus,
    namaKursus = namaKursus,
    deskripsi = deskripsi,
    kategori = kategori,
    harga = harga,
    idInstruktur = idInstruktur
)