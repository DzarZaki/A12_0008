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

    fun updateInsertInstrukturState(insertInstrukturUiEvent: InsertInstrukturUiEvent) {
        uiState = InsertInstrukturUiState(insertInstrukturUiEvent = insertInstrukturUiEvent)
    }

    suspend fun insertInstruktur() {
        viewModelScope.launch {
            try {
                instrukturRepository.insertInstruktur(uiState.insertInstrukturUiEvent.toInstruktur())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertInstrukturUiState(
    val insertInstrukturUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent()
)

data class InsertInstrukturUiEvent(
    val idInstruktur: String = "",
    val nama: String = "",
    val email: String = "",
    val nomorTelepon: String = "",
    val deskripsi: String = ""
)

fun InsertInstrukturUiEvent.toInstruktur(): Instruktur = Instruktur(
    idInstruktur = idInstruktur,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon,
    deskripsi = deskripsi
)

fun Instruktur.toUiStateInstruktur(): InsertInstrukturUiState = InsertInstrukturUiState(
    insertInstrukturUiEvent = toInsertInstrukturUiEvent()
)

fun Instruktur.toInsertInstrukturUiEvent(): InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    idInstruktur = idInstruktur,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon,
    deskripsi = deskripsi
)
