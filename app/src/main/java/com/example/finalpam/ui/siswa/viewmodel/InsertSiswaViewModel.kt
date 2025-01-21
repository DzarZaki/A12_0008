package com.example.finalpam.ui.siswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalpam.model.Siswa
import com.example.finalpam.repository.SiswaRepository
import kotlinx.coroutines.launch

class InsertSiswaViewModel (private val siswaRepository: SiswaRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertSiswaUiState())
        private set

    fun updateInsertSiswaState(insertSiswaUiEvent: InsertSiswaUiEvent) {
        uiState = InsertSiswaUiState(insertSiswaUiEvent = insertSiswaUiEvent)
    }

    fun insertSiswa() {
        viewModelScope.launch {
            try {
                siswaRepository.insertSiswa(uiState.insertSiswaUiEvent.toSiswa())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// Data class untuk UI State
data class InsertSiswaUiState(
    val insertSiswaUiEvent: InsertSiswaUiEvent = InsertSiswaUiEvent()
)

// Data class untuk event input pengguna
data class InsertSiswaUiEvent(
    val idSiswa: String = "",
    val nama: String = "",
    val email: String = "",
    val nomorTelepon: String = ""
)

// Extension untuk konversi dari UI Event ke Entity Siswa
fun InsertSiswaUiEvent.toSiswa(): Siswa = Siswa(
    idSiswa = idSiswa,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon
)

// Extension untuk konversi dari Entity Siswa ke UI State
fun Siswa.toUiStateSiswa(): InsertSiswaUiState = InsertSiswaUiState(
    insertSiswaUiEvent = toInsertSiswaUiEvent()
)

// Extension untuk konversi dari Entity Siswa ke UI Event
fun Siswa.toInsertSiswaUiEvent(): InsertSiswaUiEvent = InsertSiswaUiEvent(
    idSiswa = idSiswa,
    nama = nama,
    email = email,
    nomorTelepon = nomorTelepon
)