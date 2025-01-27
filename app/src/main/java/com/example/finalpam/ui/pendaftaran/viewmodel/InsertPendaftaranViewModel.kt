package com.example.finalpam.ui.pendaftaran.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.repository.PendaftaranRepository
import kotlinx.coroutines.launch

class InsertPendaftaranViewModel(private val pendaftaranRepository: PendaftaranRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertPendaftaranUiState())
        private set

    fun updateInsertPendaftaranState(insertUiEvent: InsertPendaftaranUiEvent) {
        uiState = InsertPendaftaranUiState(insertUiEvent = insertUiEvent)
    }

    fun insertPendaftaran() {
        viewModelScope.launch {
            try {
                pendaftaranRepository.insertPendaftaran(uiState.insertUiEvent.toPendaftaran())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertPendaftaranUiState(
    val insertUiEvent: InsertPendaftaranUiEvent = InsertPendaftaranUiEvent()
)

data class InsertPendaftaranUiEvent(
    val idPendaftaran: String = "",
    val idSiswa: String = "",
    val idKursus: String = "",
    val tanggalPendaftaran: String = "",
    val status: String = ""
)

// Konversi InsertPendaftaranUiEvent ke Pendaftaran
fun InsertPendaftaranUiEvent.toPendaftaran(): Pendaftaran = Pendaftaran(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggalpendaftaran = tanggalPendaftaran,

)

// Konversi Pendaftaran ke InsertPendaftaranUiState
fun Pendaftaran.toUiStatePendaftaran(): InsertPendaftaranUiState = InsertPendaftaranUiState(
    insertUiEvent = toInsertPendaftaranUiEvent()
)

// Konversi Pendaftaran ke InsertPendaftaranUiEvent
fun Pendaftaran.toInsertPendaftaranUiEvent(): InsertPendaftaranUiEvent = InsertPendaftaranUiEvent(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggalPendaftaran = tanggalpendaftaran,

)
