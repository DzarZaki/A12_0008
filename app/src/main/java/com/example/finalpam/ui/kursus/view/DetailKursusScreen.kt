package com.example.finalpam.ui.kursus.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.PenyediaViewModel
import com.example.finalpam.ui.costumwidget.CustomTopAppBar
import com.example.finalpam.ui.kursus.viewmodel.DetailKursusUiState
import com.example.finalpam.ui.kursus.viewmodel.DetailKursusViewModel
import com.example.finalpam.ui.kursus.viewmodel.InsertKursusUiEvent

object DestinasiDetail : DestinasiNavigasi {
    override val route = "kursus_detail"
    override val titleRes = "Detail Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKursusScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        DetailBody(
            detailUiState = viewModel.detailUiState,
            onDeleteClick = {
                viewModel.deleteKursus()
                navigateBack()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun DetailBody(
    detailUiState: DetailKursusUiState,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        if (detailUiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (detailUiState.isError) {
            Text(
                text = "Terjadi kesalahan: ${detailUiState.errorMessage}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            FormDetail(
                detailUiEvent = detailUiState.detailUiEvent,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onDeleteClick,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Hapus Kursus")
            }
        }
    }
}

@Composable
fun FormDetail(
    detailUiEvent: InsertKursusUiEvent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = detailUiEvent.namaKursus,
            onValueChange = {},
            label = { Text(text = "Nama Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = detailUiEvent.deskripsi,
            onValueChange = {},
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = detailUiEvent.kategori,
            onValueChange = {},
            label = { Text(text = "Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = detailUiEvent.harga.toString(),
            onValueChange = {},
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
    }
}