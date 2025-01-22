package com.example.finalpam.ui.instruktur.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.PenyediaViewModel
import com.example.finalpam.model.Instruktur
import com.example.finalpam.ui.costumwidget.CustomTopAppBar
import com.example.finalpam.ui.instruktur.viewmodel.HomeInstrukturUiState
import com.example.finalpam.ui.instruktur.viewmodel.HomeInstrukturViewModel
import com.example.finalpam.ui.kursus.view.OnError
import com.example.finalpam.ui.kursus.view.OnLoading
import com.example.finalpam.ui.navigation.DestinasiNavigasi

object DestinasiHomeInstruktur : DestinasiNavigasi {
    override val route = "home_instruktur"
    override val titleRes = "Home Instruktur"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeInstrukturScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeInstrukturViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeInstruktur.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getInstruktur()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Instruktur")
            }
        },
    ) { innerPadding ->
        HomeInstrukturStatus(
            homeUiState = viewModel.instrukturUiState,
            retryAction = { viewModel.getInstruktur() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { instruktur ->
                viewModel.deleteInstruktur(instruktur.idInstruktur)
                viewModel.getInstruktur() // Refresh data setelah delete
            }
        )
    }
}

@Composable
fun HomeInstrukturStatus(
    homeUiState: HomeInstrukturUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Instruktur) -> Unit
) {
    when (homeUiState) {
        is HomeInstrukturUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeInstrukturUiState.Success -> {
            if (homeUiState.instruktur.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Instruktur")
                }
            } else {
                InstrukturLayout(
                    instruktur = homeUiState.instruktur,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idInstruktur)
                    },
                    onDeleteClick = { instruktur ->
                        onDeleteClick(instruktur)
                    }
                )
            }
        }

        is HomeInstrukturUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun InstrukturLayout(
    instruktur: List<Instruktur>,
    modifier: Modifier = Modifier,
    onDetailClick: (Instruktur) -> Unit,
    onDeleteClick: (Instruktur) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(instruktur) { item ->
            InstrukturCard(
                instruktur = item,
                onDetailClick = onDetailClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun InstrukturCard(
    instruktur: Instruktur,
    onDetailClick: (Instruktur) -> Unit,
    onDeleteClick: (Instruktur) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetailClick(instruktur) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = instruktur.nama,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = { onDeleteClick(instruktur) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
            Text(
                text = instruktur.email,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Nomor Telepon: ${instruktur.nomorTelepon}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Deskripsi: ${instruktur.deskripsi}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
