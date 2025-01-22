package com.example.finalpam.ui.instruktur.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Instruktur
import com.example.finalpam.repository.InstrukturRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeInstrukturUiState {
    data class Success(val instruktur: List<Instruktur>) : HomeInstrukturUiState()
    object Error : HomeInstrukturUiState()
    object Loading : HomeInstrukturUiState()
}

class HomeInstrukturViewModel(private val instrukturRepository: InstrukturRepository) : ViewModel() {
    var instrukturUiState: HomeInstrukturUiState by mutableStateOf(HomeInstrukturUiState.Loading)
        private set

    init {
        getInstruktur()
    }

    fun getInstruktur() {
        viewModelScope.launch {
            instrukturUiState = HomeInstrukturUiState.Loading
            instrukturUiState = try {
                val result = instrukturRepository.getAllInstruktur().data
                HomeInstrukturUiState.Success(result)
            } catch (e: IOException) {
                HomeInstrukturUiState.Error
            } catch (e: HttpException) {
                HomeInstrukturUiState.Error
            }
        }
    }

    fun deleteInstruktur(idInstruktur: String) {
        viewModelScope.launch {
            try {
                instrukturRepository.deleteInstruktur(idInstruktur)
            } catch (e: IOException) {
                instrukturUiState = HomeInstrukturUiState.Error
            } catch (e: HttpException) {
                instrukturUiState = HomeInstrukturUiState.Error
            }
        }
    }
}
