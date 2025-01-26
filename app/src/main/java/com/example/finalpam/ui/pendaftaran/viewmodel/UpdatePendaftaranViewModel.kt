package com.example.finalpam.ui.pendaftaran.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.PendaftaranRepository
import com.example.finalpam.ui.pendaftaran.view.DestinasiUpdatePendaftaran
import kotlinx.coroutines.launch

class UpdatePendaftaranViewModel(
    savedStateHandle: SavedStateHandle,
    private val pendaftaranRepository: PendaftaranRepository
) : ViewModel() {

    var updateUIState by mutableStateOf(InsertPendaftaranUiState())
        private set

    private val _idPendaftaran: String = checkNotNull(savedStateHandle[DestinasiUpdatePendaftaran.ID_PENDAFTARAN])

    init {
        viewModelScope.launch {
            updateUIState = pendaftaranRepository.getPendaftaranById(_idPendaftaran)
                .toUiStatePendaftaran()
        }
    }

    fun updateInsertPendaftaranState(insertPendaftaranUiEvent: InsertPendaftaranUiEvent) {
        updateUIState = InsertPendaftaranUiState(insertUiEvent = insertPendaftaranUiEvent)
    }

    fun updateData() {
        viewModelScope.launch {
            try {
                pendaftaranRepository.updatePendaftaran(
                    _idPendaftaran,
                    updateUIState.insertUiEvent.toPendaftaran()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
