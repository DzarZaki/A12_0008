package com.example.finalpam.ui.siswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.finalpam.model.Siswa
import com.example.finalpam.repository.SiswaRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed class HomeSiswaUiState {
    data class Success(val siswa: List<Siswa>) : HomeSiswaUiState()
    object Error : HomeSiswaUiState()
    object Loading : HomeSiswaUiState()
}
class HomeSiswaViewModel (private val siswaRepository: SiswaRepository) : ViewModel() {
    var siswaUiState: HomeSiswaUiState by mutableStateOf(HomeSiswaUiState.Loading)
        private set

    init {
        getSiswa()
    }

    fun getSiswa() {
        viewModelScope.launch {
            siswaUiState = HomeSiswaUiState.Loading
            siswaUiState = try {
                HomeSiswaUiState.Success(siswaRepository.getSiswa())
            } catch (e: IOException) {
                HomeSiswaUiState.Error
            } catch (e: HttpException) {
                HomeSiswaUiState.Error
            }
        }
    }

    fun deleteSiswa(idSiswa: String) {
        viewModelScope.launch {
            try {
                siswaRepository.deleteSiswa(idSiswa)
                getSiswa() // Refresh data setelah delete
            } catch (e: IOException) {
                siswaUiState = HomeSiswaUiState.Error
            } catch (e: HttpException) {
                siswaUiState = HomeSiswaUiState.Error
            }
        }
    }
}