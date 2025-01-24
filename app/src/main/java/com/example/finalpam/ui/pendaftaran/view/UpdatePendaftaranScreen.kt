package com.example.finalpam.ui.pendaftaran.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import com.example.finalpam.ui.pendaftaran.viewmodel.InsertPendaftaranUiEvent
import com.example.finalpam.ui.pendaftaran.viewmodel.InsertPendaftaranUiState
import com.example.finalpam.ui.pendaftaran.viewmodel.UpdatePendaftaranViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdatePendaftaran : DestinasiNavigasi {
    override val route = "pendaftaran_update"
    override val titleRes = "Edit Pendaftaran"
    const val ID_PENDAFTARAN = "id_pendaftaran"
    val routeWithArgs = "$route/{$ID_PENDAFTARAN}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePendaftaranScreen(
    navigateBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdatePendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdatePendaftaran.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        }
    ) { padding ->
        EntryPendaftaranBody(
            modifier = Modifier.padding(padding),
            onPendaftaranValueChange = viewModel::updateInsertPendaftaranState,
            insertUiState = viewModel.updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateData()
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
fun EntryPendaftaranBody(
    insertUiState: InsertPendaftaranUiState,
    onPendaftaranValueChange: (InsertPendaftaranUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormPendaftaranInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onPendaftaranValueChange,
            modifier = Modifier.fillMaxWidth()
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

@Composable
fun FormPendaftaranInput(
    insertUiEvent: InsertPendaftaranUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPendaftaranUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.idSiswa,
            onValueChange = { onValueChange(insertUiEvent.copy(idSiswa = it)) },
            label = { Text(text = "ID Siswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idKursus,
            onValueChange = { onValueChange(insertUiEvent.copy(idKursus = it)) },
            label = { Text(text = "ID Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.tanggalPendaftaran,
            onValueChange = { onValueChange(insertUiEvent.copy(tanggalPendaftaran = it)) },
            label = { Text(text = "Tanggal") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.status,
            onValueChange = { onValueChange(insertUiEvent.copy(status = it)) },
            label = { Text(text = "Status") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
