package com.example.finalpam.ui.pendaftaran.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.finalpam.model.Pendaftaran
import com.example.finalpam.ui.costumwidget.CustomTopAppBar
import com.example.finalpam.ui.kursus.view.OnError
import com.example.finalpam.ui.kursus.view.OnLoading
import com.example.finalpam.ui.navigation.DestinasiNavigasi
import com.example.finalpam.ui.pendaftaran.viewmodel.HomePendaftaranUiState
import com.example.finalpam.ui.pendaftaran.viewmodel.HomePendaftaranViewModel

object DestinasiHomePendaftaran : DestinasiNavigasi {
    override val route = "home_pendaftaran"
    override val titleRes = "Home Pendaftaran"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePendaftaranScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomePendaftaranViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomePendaftaran.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPendaftaran()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Pendaftaran")
            }
        },
    ) { innerPadding ->
        HomePendaftaranStatus(
            homeUiState = viewModel.pendaftaranUiState,
            retryAction = { viewModel.getPendaftaran() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = { pendaftaran ->
                viewModel.deletePendaftaran(pendaftaran.idPendaftaran)
                viewModel.getPendaftaran() // Refresh data setelah delete
            }
        )
    }
}

@Composable
fun HomePendaftaranStatus(
    homeUiState: HomePendaftaranUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteClick: (Pendaftaran) -> Unit
) {
    when (homeUiState) {
        is HomePendaftaranUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomePendaftaranUiState.Success -> {
            if (homeUiState.pendaftaran.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Pendaftaran")
                }
            } else {
                PendaftaranLayout(
                    pendaftaran = homeUiState.pendaftaran,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idPendaftaran)
                    },
                    onDeleteClick = { pendaftaran ->
                        onDeleteClick(pendaftaran)
                    }
                )
            }
        }

        is HomePendaftaranUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun PendaftaranLayout(
    pendaftaran: List<Pendaftaran>,
    modifier: Modifier = Modifier,
    onDetailClick: (Pendaftaran) -> Unit,
    onDeleteClick: (Pendaftaran) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pendaftaran) { item ->
            PendaftaranCard(
                pendaftaran = item,
                onDetailClick = onDetailClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun PendaftaranCard(
    pendaftaran: Pendaftaran,
    onDetailClick: (Pendaftaran) -> Unit,
    onDeleteClick: (Pendaftaran) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onDetailClick(pendaftaran) },
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
                    text = "ID: ${pendaftaran.idPendaftaran}",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = { onDeleteClick(pendaftaran) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
            Text(
                text = "Siswa ID: ${pendaftaran.idSiswa}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Kursus ID: ${pendaftaran.idKursus}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Tanggal: ${pendaftaran.tanggalpendaftaran}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Status: ${pendaftaran.status}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
