package com.example.finalpam.ui.kursus.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.finalpam.ui.navigation.DestinasiNavigasi

object DestinasiDetail : DestinasiNavigasi {
    override val route = "kursus_detail"
    override val titleRes = "Detail Kursus"
    const val ID_KURSUS = "id_kursus"
    val routeWithArgs = "$route/{$ID_KURSUS}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKursusScreen(
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.detailUiState
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
        DetailKursusBody(
            uiState = uiState,
            onEditClick = { onEditClick(uiState.detailUiEvent.idKursus) },
            onDeleteClick = {
                viewModel.deleteKursus()
                onDeleteClick()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun DetailKursusBody(
    uiState: DetailKursusUiState,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> OnLoading(modifier = modifier.fillMaxSize())
        uiState.isError -> OnError(retryAction = {}, modifier = modifier.fillMaxSize())
        else -> DetailKursusContent(
            detailUiEvent = uiState.detailUiEvent,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick,
            modifier = modifier
        )
    }
}

@Composable
fun DetailKursusContent(
    detailUiEvent: InsertKursusUiEvent,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Nama Kursus: ${detailUiEvent.namaKursus}",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "ID Kursus: ${detailUiEvent.idKursus}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Deskripsi: ${detailUiEvent.deskripsi}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Kategori: ${detailUiEvent.kategori}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Harga: ${detailUiEvent.harga}",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { onEditClick(detailUiEvent.idKursus) },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Edit")
            }
            Button(
                onClick = onDeleteClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text(text = "Hapus")
            }
        }
    }
}
