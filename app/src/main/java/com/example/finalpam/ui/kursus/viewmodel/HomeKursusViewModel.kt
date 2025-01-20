package com.example.finalpam.ui.kursus.viewmodel

import coil.network.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Kursus
import com.example.finalpam.repository.KursusRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeKursusUiState {
    data class Success(val kursus: List<Kursus>) : HomeKursusUiState()
    object Error : HomeKursusUiState()
    object Loading : HomeKursusUiState()
}
class HomeKursusViewModel (private val kursusRepository: KursusRepository) : ViewModel() {
    var kursusUiState: HomeKursusUiState by mutableStateOf(HomeKursusUiState.Loading)
        private set

    init {
        getKursus()
    }

    fun getKursus() {
        viewModelScope.launch {
            kursusUiState = HomeKursusUiState.Loading
            kursusUiState = try {
                HomeKursusUiState.Success(kursusRepository.getAllKursus().data)
            } catch (e: IOException) {
                HomeKursusUiState.Error
            } catch (e: HttpException) {
                HomeKursusUiState.Error
            }
        }
    }

    fun deleteKursus(idKursus: String) {
        viewModelScope.launch {
            try {
                kursusRepository.deleteKursus(idKursus)
            } catch (e: IOException) {
                kursusUiState = HomeKursusUiState.Error
            } catch (e: HttpException) {
                kursusUiState = HomeKursusUiState.Error
            }
        }
    }
}