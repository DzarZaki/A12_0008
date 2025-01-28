package com.example.finalpam.ui.siswa.view

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
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import com.example.finalpam.ui.siswa.viewmodel.InsertSiswaUiEvent
import com.example.finalpam.ui.siswa.viewmodel.InsertSiswaUiState
import com.example.finalpam.ui.siswa.viewmodel.UpdateSiswaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateSiswa : DestinasiNavigasi {
    override val route = "siswa_update"
    override val titleRes = "Edit Siswa"
    const val ID_SISWA = "id_siswa"
    val routeWithArgs = "$route/{$ID_SISWA}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateSiswaScreen(
    navigateBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateSiswa.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        }
    ) { padding ->
        EntryBody(
            modifier = Modifier.padding(padding),
            onSiswaValueChange = viewModel::updateInsertSiswaState,
            insertUiState = viewModel.updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateSiswa()
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
fun EntryBody(
    insertUiState: InsertSiswaUiState,
    onSiswaValueChange: (InsertSiswaUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiState.insertSiswaUiEvent.nama,
            onValueChange = { onSiswaValueChange(insertUiState.insertSiswaUiEvent.copy(nama = it)) },
            label = { Text(text = "Nama") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiState.insertSiswaUiEvent.idSiswa,
            onValueChange = { onSiswaValueChange(insertUiState.insertSiswaUiEvent.copy(idSiswa = it)) },
            label = { Text(text = "ID Siswa") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = false // Tidak dapat diubah
        )
        OutlinedTextField(
            value = insertUiState.insertSiswaUiEvent.email,
            onValueChange = { onSiswaValueChange(insertUiState.insertSiswaUiEvent.copy(email = it)) },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiState.insertSiswaUiEvent.nomorTelepon,
            onValueChange = { onSiswaValueChange(insertUiState.insertSiswaUiEvent.copy(nomorTelepon = it)) },
            label = { Text(text = "Nomor Telepon") },
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