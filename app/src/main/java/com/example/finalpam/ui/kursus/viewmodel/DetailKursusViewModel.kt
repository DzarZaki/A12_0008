package com.example.finalpam.ui.kursus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Kursus
import com.example.finalpam.repository.KursusRepository
import kotlinx.coroutines.launch

class DetailKursusViewModel (
    savedStateHandle: SavedStateHandle,
    private val kursusRepository: KursusRepository
) : ViewModel() {
    private val idKursus: String = checkNotNull(savedStateHandle[DestinasiDetail.ID_KURSUS])

    var detailUiState: DetailKursusUiState by mutableStateOf(DetailKursusUiState())
        private set

    init {
        getKursusById()
    }

    private fun getKursusById() {
        viewModelScope.launch {
            detailUiState = DetailKursusUiState(isLoading = true)
            try {
                val result = kursusRepository.getKursusById(idKursus).data
                detailUiState = DetailKursusUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailKursusUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun deleteKursus() {
        viewModelScope.launch {
            detailUiState = DetailKursusUiState(isLoading = true)
            try {
                kursusRepository.deleteKursus(idKursus)
                detailUiState = DetailKursusUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailKursusUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}

data class DetailKursusUiState(
    val detailUiEvent: InsertKursusUiEvent = InsertKursusUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertKursusUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertKursusUiEvent()
}

fun Kursus.toDetailUiEvent(): InsertKursusUiEvent {
    return InsertKursusUiEvent(
        idKursus = idKursus,
        namaKursus = namaKursus,
        deskripsi = deskripsi,
        kategori = kategori,
        harga = harga
    )
}