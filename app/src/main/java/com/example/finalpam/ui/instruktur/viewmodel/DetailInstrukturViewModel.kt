package com.example.finalpam.ui.instruktur.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Instruktur
import com.example.finalpam.repository.InstrukturRepository
import com.example.finalpam.ui.instruktur.view.DestinasiDetailInstruktur
import com.example.finalpam.ui.kursus.view.DestinasiDetail
import kotlinx.coroutines.launch

class DetailInstrukturViewModel(
    savedStateHandle: SavedStateHandle,
    private val instrukturRepository: InstrukturRepository
) : ViewModel() {

    private val idInstruktur: String = checkNotNull(savedStateHandle[DestinasiDetailInstruktur.ID_INSTRUKTUR])

    var detailUiState by mutableStateOf(DetailInstrukturUiState())
        private set

    init {
        getInstrukturById()
    }

    private fun getInstrukturById() {
        viewModelScope.launch {
            detailUiState = DetailInstrukturUiState(isLoading = true)
            try {
                val result = instrukturRepository.getInstrukturById(idInstruktur).data
                detailUiState = DetailInstrukturUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailInstrukturUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun deleteInstruktur() {
        viewModelScope.launch {
            detailUiState = DetailInstrukturUiState(isLoading = true)
            try {
                instrukturRepository.deleteInstruktur(idInstruktur)
                detailUiState = DetailInstrukturUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailInstrukturUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}

data class DetailInstrukturUiState(
    val detailUiEvent: InsertInstrukturUiEvent = InsertInstrukturUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertInstrukturUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertInstrukturUiEvent()
}

fun Instruktur.toDetailUiEvent(): InsertInstrukturUiEvent = InsertInstrukturUiEvent(
    idInstruktur = idInstruktur,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon,
    deskripsi = deskripsi
)
