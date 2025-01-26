package com.example.finalpam.ui.instruktur.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.InstrukturRepository
import com.example.finalpam.ui.instruktur.view.DestinasiUpdateInstruktur
import kotlinx.coroutines.launch

class UpdateInstrukturViewModel(
    savedStateHandle: SavedStateHandle,
    private val instrukturRepository: InstrukturRepository
) : ViewModel() {

    var updateUIState by mutableStateOf(InsertInstrukturUiState())
        private set

    private val _idInstruktur: String = checkNotNull(savedStateHandle[DestinasiUpdateInstruktur.ID_INSTRUKTUR])

    init {
        viewModelScope.launch {
            updateUIState = instrukturRepository.getInstrukturById(_idInstruktur)
                .toUiStateInstruktur()
        }
    }

    fun updateInsertInstrukturState(insertInstrukturUiEvent: InsertInstrukturUiEvent) {
        updateUIState = InsertInstrukturUiState(insertUiEvent = insertInstrukturUiEvent)
    }

    fun updateData() {
        viewModelScope.launch {
            try {
                instrukturRepository.updateInstruktur(
                    _idInstruktur,
                    updateUIState.insertUiEvent.toInstruktur()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
