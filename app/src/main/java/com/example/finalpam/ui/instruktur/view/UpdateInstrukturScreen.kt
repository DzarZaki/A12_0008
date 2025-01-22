package com.example.finalpam.ui.instruktur.view

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
import com.example.finalpam.ui.instruktur.viewmodel.InsertInstrukturUiEvent
import com.example.finalpam.ui.instruktur.viewmodel.InsertInstrukturUiState
import com.example.finalpam.ui.instruktur.viewmodel.UpdateInstrukturViewModel
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateInstruktur : DestinasiNavigasi {
    override val route = "instruktur_update"
    override val titleRes = "Edit Instruktur"
    const val ID_INSTRUKTUR = "id_instruktur"
    val routeWithArgs = "$route/{$ID_INSTRUKTUR}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateInstrukturScreen(
    navigateBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                title = DestinasiUpdateInstruktur.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        }
    ) { padding ->
        EntryInstrukturBody(
            modifier = Modifier.padding(padding),
            onInstrukturValueChange = viewModel::updateInsertInstrukturState,
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
fun EntryInstrukturBody(
    insertUiState: InsertInstrukturUiState,
    onInstrukturValueChange: (InsertInstrukturUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInstrukturInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onInstrukturValueChange,
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
fun FormInstrukturInput(
    insertUiEvent: InsertInstrukturUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertInstrukturUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange = { onValueChange(insertUiEvent.copy(nama = it)) },
            label = { Text(text = "Nama Instruktur") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.email,
            onValueChange = { onValueChange(insertUiEvent.copy(email = it)) },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.nomorTelepon,
            onValueChange = { onValueChange(insertUiEvent.copy(nomorTelepon = it)) },
            label = { Text(text = "Nomor Telepon") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsi,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsi = it)) },
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = false
        )
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
