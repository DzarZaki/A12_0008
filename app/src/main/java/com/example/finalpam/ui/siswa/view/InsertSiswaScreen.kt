package com.example.finalpam.ui.siswa.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import com.example.finalpam.ui.siswa.viewmodel.InsertSiswaUiEvent
import com.example.finalpam.ui.siswa.viewmodel.InsertSiswaUiState
import com.example.finalpam.ui.siswa.viewmodel.InsertSiswaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertSiswa : DestinasiNavigasi {
    override val route = "insert_siswa"
    override val titleRes = "Tambah Siswa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertSiswaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertSiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiInsertSiswa.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        InsertSiswaBody(
            insertUiState = viewModel.uiState,
            onSiswaValueChange = viewModel::updateInsertSiswaState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertSiswa()
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
fun InsertSiswaBody(
    insertUiState: InsertSiswaUiState,
    onSiswaValueChange: (InsertSiswaUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormSiswaInput(
            insertUiEvent = insertUiState.insertSiswaUiEvent,
            onValueChange = onSiswaValueChange,
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
fun FormSiswaInput(
    insertUiEvent: InsertSiswaUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertSiswaUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange = { onValueChange(insertUiEvent.copy(nama = it)) },
            label = { Text(text = "Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.idSiswa,
            onValueChange = { onValueChange(insertUiEvent.copy(idSiswa = it)) },
            label = { Text(text = "ID Siswa") },
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
    }
}
