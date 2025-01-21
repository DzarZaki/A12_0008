package com.example.finalpam.ui.siswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Siswa
import com.example.finalpam.repository.SiswaRepository
import com.example.finalpam.ui.kursus.view.DestinasiDetail
import kotlinx.coroutines.launch

class DetailSiswaViewModel (
    savedStateHandle: SavedStateHandle,
    private val siswaRepository: SiswaRepository
) : ViewModel() {
    private val idSiswa: String = checkNotNull(savedStateHandle[DestinasiDetail.ID_SISWA])

    var detailUiState: DetailSiswaUiState by mutableStateOf(DetailSiswaUiState())
        private set

    init {
        getSiswaById()
    }

    private fun getSiswaById() {
        viewModelScope.launch {
            detailUiState = DetailSiswaUiState(isLoading = true)
            try {
                val result = siswaRepository.getSiswaById(idSiswa).data
                detailUiState = DetailSiswaUiState(
                    detailUiEvent = result.toDetailSiswaUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailSiswaUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }

    fun deleteSiswa() {
        viewModelScope.launch {
            detailUiState = DetailSiswaUiState(isLoading = true)
            try {
                siswaRepository.deleteSiswa(idSiswa)
                detailUiState = DetailSiswaUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailSiswaUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}

// Data class untuk UI State
data class DetailSiswaUiState(
    val detailUiEvent: InsertSiswaUiEvent = InsertSiswaUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertSiswaUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertSiswaUiEvent()
}

// Extension untuk konversi dari entity Siswa ke UI Event
fun Siswa.toDetailSiswaUiEvent(): InsertSiswaUiEvent {
    return InsertSiswaUiEvent(
        idSiswa = idSiswa,
        nama = nama,
        email = email,
        nomorTelepon = nomorTelepon
    )
}