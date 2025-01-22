package com.example.finalpam.ui.instruktur.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Instruktur
import com.example.finalpam.repository.InstrukturRepository
import kotlinx.coroutines.launch

class InsertInstrukturViewModel(private val instrukturRepository: InstrukturRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertInstrukturUiState())
        private set

    fun updateInsertInstrukturState(insertUiEvent: InsertInstrukturUiEvent) {
        uiState = InsertInstrukturUiState(insertUiEvent = insertUiEvent)
    }

    fun insertInstruktur() {
        viewModelScope.launch {
            try {
                instrukturRepository.insertInstruktur(uiState.insertUiEvent.toInstruktur())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertInstrukturUiState(
    val insertUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

data class InsertInstrukturUiEvent(
    val idInstruktur: String = "",
    val nama: String = "",
    val email: String = "",
    val nomorTelepon: String = "",
    val deskripsi: String = ""
)

// Konversi InsertInstrukturUiEvent ke Instruktur
fun InsertInstrukturUiEvent.toInstruktur(): Instruktur = Instruktur(
    idInstruktur = idInstruktur,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon,
    deskripsi = deskripsi
)

// Konversi Instruktur ke InsertInstrukturUiState
fun Instruktur.toUiStateInstruktur(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertUiEvent = toInsertInstrukturUiEvent()
)

// Konversi Instruktur ke InsertInstrukturUiEvent
fun Instruktur.toInsertInstrukturUiEvent(): InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    idInstruktur = idInstruktur,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon,
    deskripsi = deskripsi
)
