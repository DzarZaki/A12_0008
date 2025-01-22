package com.example.finalpam.ui.instruktur.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.repository.InstrukturRepository
import kotlinx.coroutines.launch

class UpdateInstrukturViewModel(
    savedStateHandle: SavedStateHandle,
    private val instrukturRepository: InstrukturRepository
) : ViewModel() {

    var updateUIState by mutableStateOf(InsertInstrukturUiState())
        private set

    private val idInstruktur: String = checkNotNull(savedStateHandle[DestinasiUpdate.ID_INSTRUKTUR])

    init {
        viewModelScope.launch {
            updateUIState = instrukturRepository.getInstrukturById(idInstruktur).data
                .toUiStateInstruktur()
        }
    }

    fun updateInsertInstrukturState(insertInstrukturUiEvent: InsertInstrukturUiEvent) {
        updateUIState = InsertInstrukturUiState(insertInstrukturUiEvent = insertInstrukturUiEvent)
    }

    suspend fun updateInstruktur() {
        viewModelScope.launch {
            try {
                instrukturRepository.updateInstruktur(
                    idInstruktur,
                    updateUIState.insertInstrukturUiEvent.toInstruktur()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
