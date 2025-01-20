package com.example.finalpam.ui.kursus.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.PenyediaViewModel
import com.example.finalpam.ui.costumwidget.CustomTopAppBar
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusUiEvent
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusUiState
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusViewModel
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import kotlinx.coroutines.launch

object DestinasiEntry : DestinasiNavigasi {
    override val route = "kursus_entry"
    override val titleRes = "Tambah Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKursusScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onKursusValueChange = viewModel::updateInsertKursusState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKursus()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertKursusUiState,
    onKursusValueChange: (InsertKursusUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onKursusValueChange,
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
fun FormInput(
    insertUiEvent: InsertKursusUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertKursusUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.namaKursus,
            onValueChange = { onValueChange(insertUiEvent.copy(namaKursus = it)) },
            label = { Text(text = "Nama Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.deskripsi,
            onValueChange = { onValueChange(insertUiEvent.copy(deskripsi = it)) },
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = insertUiEvent.kategori,
            onValueChange = { onValueChange(insertUiEvent.copy(kategori = it)) },
            label = { Text(text = "Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.harga.toString(),
            onValueChange = { onValueChange(insertUiEvent.copy(harga = it.toDoubleOrNull() ?: 0.0)) },
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}