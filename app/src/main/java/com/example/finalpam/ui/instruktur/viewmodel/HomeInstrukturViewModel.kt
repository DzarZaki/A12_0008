package com.example.finalpam.ui.instruktur.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.AllInstrukturResponse
import com.example.finalpam.model.Instruktur
import com.example.finalpam.repository.InstrukturRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeInstrukturUiState {
    data class Success(val instrukturList: List<Instruktur>) : HomeInstrukturUiState()
    object Error : HomeInstrukturUiState()
    object Loading : HomeInstrukturUiState()
}

class HomeInstrukturViewModel(private val instrukturRepository: InstrukturRepository) : ViewModel() {
    var uiState: HomeInstrukturUiState by mutableStateOf(HomeInstrukturUiState.Loading)
        private set

    init {
        getAllInstruktur()
    }

    fun getAllInstruktur() {
        viewModelScope.launch {
            uiState = HomeInstrukturUiState.Loading
            uiState = try {
                val response: AllInstrukturResponse = instrukturRepository.getAllInstruktur()
                HomeInstrukturUiState.Success(response.data)
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
                getAllInstruktur() // Refresh data setelah delete
            } catch (e: IOException) {
                uiState = HomeInstrukturUiState.Error
            } catch (e: HttpException) {
                uiState = HomeInstrukturUiState.Error
            }
        }
    }
}