package com.example.finalpam.ui.kursus.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.KursusRepository
import com.example.finalpam.ui.kursus.view.DestinasiUpdate
import kotlinx.coroutines.launch

class UpdateKursusViewModel (
    savedStateHandle: SavedStateHandle,
    private val kursusRepository: KursusRepository
) : ViewModel() {

    var updateUIState by mutableStateOf(InsertKursusUiState())
        private set

    private val _idKursus: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_KURSUS])

    init {
        viewModelScope.launch {
            updateUIState = kursusRepository.getKursusById(_idKursus).data
                .toUiStateKursus()
        }
    }

    fun updateInsertKursusState(insertUiEvent: InsertKursusUiEvent) {
        updateUIState = InsertKursusUiState(insertUiEvent = insertUiEvent)
    }

    fun updateData() {
        viewModelScope.launch {
            try {
                kursusRepository.updateKursus(_idKursus, updateUIState.insertUiEvent.toKursus())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}