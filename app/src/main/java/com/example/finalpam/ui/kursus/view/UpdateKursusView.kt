package com.example.finalpam.ui.kursus.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.PenyediaViewModel
import com.example.finalpam.ui.costumwidget.CustomTopAppBar
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusUiEvent
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusUiState
import com.example.finalpam.ui.kursus.viewmodel.UpdateKursusViewModel
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "kursus_update"
    override val titleRes = "Edit Kursus"
    const val ID_KURSUS = "id_kursus"
    val routeWithArgs = "$route/{$ID_KURSUS}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKursusView(
    navigateBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        }
    ) { padding ->
        UpdateBody(
            modifier = Modifier.padding(padding),
            onKursusValueChange = viewModel::updateInsertKursusState,
            insertUiState = viewModel.updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKursus()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}

@Composable
fun UpdateBody(
    insertUiState: InsertKursusUiState,
    onKursusValueChange: (InsertKursusUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiState.insertUiEvent.namaKursus,
            onValueChange = { onKursusValueChange(insertUiState.insertUiEvent.copy(namaKursus = it)) },
            label = { Text(text = "Nama Kursus") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiState.insertUiEvent.idKursus,
            onValueChange = { onKursusValueChange(insertUiState.insertUiEvent.copy(idKursus = it)) },
            label = { Text(text = "ID Kursus") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = false // Tidak dapat diubah
        )
        OutlinedTextField(
            value = insertUiState.insertUiEvent.deskripsi,
            onValueChange = { onKursusValueChange(insertUiState.insertUiEvent.copy(deskripsi = it)) },
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiState.insertUiEvent.kategori,
            onValueChange = { onKursusValueChange(insertUiState.insertUiEvent.copy(kategori = it)) },
            label = { Text(text = "Kategori") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiState.insertUiEvent.harga.toString(),
            onValueChange = { onKursusValueChange(insertUiState.insertUiEvent.copy(harga = it.toDoubleOrNull() ?: 0.0)) },
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiState.insertUiEvent.idInstruktur,
            onValueChange = { onKursusValueChange(insertUiState.insertUiEvent.copy(idInstruktur = it)) },
            label = { Text(text = "ID Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}
