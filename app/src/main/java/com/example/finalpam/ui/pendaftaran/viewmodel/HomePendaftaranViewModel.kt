package com.example.finalpam.ui.pendaftaran.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.repository.PendaftaranRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomePendaftaranUiState {
    data class Success(val pendaftaran: List<Pendaftaran>) : HomePendaftaranUiState()
    object Error : HomePendaftaranUiState()
    object Loading : HomePendaftaranUiState()
}

class HomePendaftaranViewModel(private val pendaftaranRepository: PendaftaranRepository) : ViewModel() {
    var pendaftaranUiState: HomePendaftaranUiState by mutableStateOf(HomePendaftaranUiState.Loading)
        private set

    init {
        getPendaftaran()
    }

    fun getPendaftaran() {
        viewModelScope.launch {
            pendaftaranUiState = HomePendaftaranUiState.Loading
            pendaftaranUiState = try {
                val response = pendaftaranRepository.getAllPendaftaran()
                HomePendaftaranUiState.Success(response.data)
            } catch (e: IOException) {
                HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                HomePendaftaranUiState.Error
            }
        }
    }

    fun deletePendaftaran(idPendaftaran: String) {
        viewModelScope.launch {
            try {
                pendaftaranRepository.deletePendaftaran(idPendaftaran)
                // Refresh data setelah delete
                getPendaftaran()
            } catch (e: IOException) {
                pendaftaranUiState = HomePendaftaranUiState.Error
            } catch (e: HttpException) {
                pendaftaranUiState = HomePendaftaranUiState.Error
            }
        }
    }
}
