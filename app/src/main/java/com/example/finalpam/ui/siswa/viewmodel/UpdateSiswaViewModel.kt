package com.example.finalpam.ui.siswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.SiswaRepository
import com.example.finalpam.ui.kursus.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateSiswaViewModel (
    savedStateHandle: SavedStateHandle,
    private val siswaRepository: SiswaRepository
) : ViewModel() {

    var updateUiState by mutableStateOf(InsertSiswaUiState())
        private set

    private val idSiswa: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_SISWA])

    init {
        viewModelScope.launch {
            updateUiState = siswaRepository.getSiswaById(idSiswa).data
                .toUiStateSiswa()
        }
    }

    fun updateInsertSiswaState(insertSiswaUiEvent: InsertSiswaUiEvent) {
        updateUiState = InsertSiswaUiState(insertSiswaUiEvent = insertSiswaUiEvent)
    }

    fun updateSiswa() {
        viewModelScope.launch {
            try {
                siswaRepository.updateSiswa(
                    idSiswa,
                    updateUiState.insertSiswaUiEvent.toSiswa()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}