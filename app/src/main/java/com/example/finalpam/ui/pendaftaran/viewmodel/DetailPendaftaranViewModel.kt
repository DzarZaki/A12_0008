package com.example.finalpam.ui.pendaftaran.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.repository.PendaftaranRepository
import com.example.finalpam.ui.kursus.view.DestinasiDetail
import com.example.finalpam.ui.pendaftaran.view.DestinasiDetailPendaftaran
import kotlinx.coroutines.launch

class DetailPendaftaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pendaftaranRepository: PendaftaranRepository
) : ViewModel() {

    private val idPendaftaran: String = checkNotNull(savedStateHandle[DestinasiDetailPendaftaran.ID_PENDAFTARAN])

    var detailUiState: DetailPendaftaranUiState by mutableStateOf(DetailPendaftaranUiState())
        private set

    init {
        getPendaftaranById()
    }

    private fun getPendaftaranById() {
        viewModelScope.launch {
            detailUiState = DetailPendaftaranUiState(isLoading = true)
            try {
                val result = pendaftaranRepository.getPendaftaranById(idPendaftaran)
                detailUiState = DetailPendaftaranUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailPendaftaranUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun deletePendaftaran() {
        viewModelScope.launch {
            detailUiState = DetailPendaftaranUiState(isLoading = true)
            try {
                pendaftaranRepository.deletePendaftaran(idPendaftaran)
                detailUiState = DetailPendaftaranUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailPendaftaranUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}

data class DetailPendaftaranUiState(
    val detailUiEvent: InsertPendaftaranUiEvent = InsertPendaftaranUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertPendaftaranUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertPendaftaranUiEvent()
}

// Konversi Pendaftaran ke Detail UI Event
fun Pendaftaran.toDetailUiEvent(): InsertPendaftaranUiEvent = InsertPendaftaranUiEvent(
    idPendaftaran = idPendaftaran,
    idSiswa = idSiswa,
    idKursus = idKursus,
    tanggalPendaftaran = tanggalpendaftaran,

)
