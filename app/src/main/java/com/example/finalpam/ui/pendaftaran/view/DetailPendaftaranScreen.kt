package com.example.finalpam.ui.pendaftaran.view

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
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import com.example.finalpam.ui.pendaftaran.viewmodel.DetailPendaftaranUiState
import com.example.finalpam.ui.pendaftaran.viewmodel.DetailPendaftaranViewModel
import com.example.finalpam.ui.pendaftaran.viewmodel.InsertPendaftaranUiEvent

object DestinasiDetailPendaftaran : DestinasiNavigasi {
    override val route = "pendaftaran_detail"
    override val titleRes = "Detail Pendaftaran"
    const val ID_PENDAFTARAN = "id_pendaftaran"
    val routeWithArgs = "$route/{$ID_PENDAFTARAN}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPendaftaranScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit = { },
    viewModel: DetailPendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetailPendaftaran.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        DetailPendaftaranBody(
            detailUiState = viewModel.detailUiState,
            onDeleteClick = {
                viewModel.deletePendaftaran()
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
fun DetailPendaftaranBody(
    detailUiState: DetailPendaftaranUiState,
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
            FormDetailPendaftaran(
                detailUiEvent = detailUiState.detailUiEvent,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onDeleteClick,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Hapus Pendaftaran")
            }
        }
    }
}

@Composable
fun FormDetailPendaftaran(
    detailUiEvent: InsertPendaftaranUiEvent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = detailUiEvent.idSiswa,
            onValueChange = {},
            label = { Text(text = "ID Siswa") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = detailUiEvent.idKursus,
            onValueChange = {},
            label = { Text(text = "ID Kursus") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = detailUiEvent.tanggalPendaftaran,
            onValueChange = {},
            label = { Text(text = "Tanggal") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
        OutlinedTextField(
            value = detailUiEvent.status,
            onValueChange = {},
            label = { Text(text = "Status") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false
        )
    }
}
