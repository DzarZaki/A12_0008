package com.example.finalpam.ui.instruktur.view

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
import com.example.finalpam.ui.instruktur.viewmodel.DetailInstrukturUiState
import com.example.finalpam.ui.instruktur.viewmodel.DetailInstrukturViewModel
import com.example.finalpam.ui.instruktur.viewmodel.InsertInstrukturUiEvent
import com.example.finalpam.ui.kursus.view.OnError
import com.example.finalpam.ui.kursus.view.OnLoading
import com.example.finalpam.ui.navigation.DestinasiNavigasi

object DestinasiDetailInstruktur : DestinasiNavigasi {
    override val route = "instruktur_detail"
    override val titleRes = "Detail Instruktur"
    const val ID_INSTRUKTUR = "id_instruktur"
    val routeWithArgs = "$route/{$ID_INSTRUKTUR}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailInstrukturScreen(
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.detailUiState
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetailInstruktur.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        DetailInstrukturBody(
            uiState = uiState,
            onEditClick = onEditClick,
            onDeleteClick = {
                viewModel.deleteInstruktur()
                onDeleteClick()
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun DetailInstrukturBody(
    uiState: DetailInstrukturUiState,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> OnLoading(modifier = modifier.fillMaxSize())
        uiState.isError -> OnError(retryAction = {}, modifier = modifier.fillMaxSize())
        else -> DetailInstrukturContent(
            detailUiEvent = uiState.detailUiEvent,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick,
            modifier = modifier
        )
    }
}

@Composable
fun DetailInstrukturContent(
    detailUiEvent: InsertInstrukturUiEvent,
    onEditClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Nama: ${detailUiEvent.nama}",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "ID Instruktur: ${detailUiEvent.idInstruktur}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Email: ${detailUiEvent.email}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Nomor Telepon: ${detailUiEvent.nomorTelepon}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Deskripsi: ${detailUiEvent.deskripsi}",
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { onEditClick(detailUiEvent.idInstruktur) },
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
