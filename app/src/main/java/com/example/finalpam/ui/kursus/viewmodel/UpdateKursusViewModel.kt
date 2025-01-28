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

    var updateUiState by mutableStateOf(InsertKursusUiState())
        private set

    private val idKursus: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_KURSUS])

    init {
        viewModelScope.launch {
            updateUiState = kursusRepository.getKursusById(idKursus)
                .toUiStateKursus()
        }
    }

    fun updateInsertKursusState(insertUiEvent: InsertKursusUiEvent) {
        updateUiState = InsertKursusUiState(insertUiEvent = insertUiEvent)
    }

    fun updateKursus() {
        viewModelScope.launch {
            try {
                kursusRepository.updateKursus(
                    idKursus,
                    updateUiState.insertUiEvent.toKursus()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
