package com.example.finalpam.ui.kursus.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalpam.PenyediaViewModel
import com.example.finalpam.R
import com.example.finalpam.model.Kursus
import com.example.finalpam.ui.costumwidget.CustomTopAppBar
import com.example.finalpam.ui.kursus.viewmodel.HomeKursusUiState
import com.example.finalpam.ui.kursus.viewmodel.HomeKursusViewModel
import com.example.finalpam.ui.navigation.DestinasiNavigasi

object DestinasiHome : DestinasiNavigasi {
    override val route = "home_kursus"
    override val titleRes = "Home Kursus"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeKursusScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeKursusViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getKursus()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Kursus")
            }
        },
    ) { innerPadding ->
        HomeKursusStatus(
            homeUiState = viewModel.kursusUiState,
            retryAction = { viewModel.getKursus() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteKursus(it.idKursus)
                viewModel.getKursus()
            }
        )
    }
}

@Composable
fun HomeKursusStatus(
    homeUiState: HomeKursusUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kursus) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeKursusUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is HomeKursusUiState.Success ->
            if (homeUiState.kursus.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Kursus")
                }
            } else {
                KursusLayout(
                    kursus = homeUiState.kursus,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.idKursus)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeKursusUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(100.dp)
            .padding(40.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.error), contentDescription = ""
        )
        Text(text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}
@Composable
fun KursusLayout(
    kursus: List<Kursus>,
    modifier: Modifier = Modifier,
    onDetailClick: (Kursus) -> Unit,
    onDeleteClick: (Kursus) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(kursus) { item ->
            KursusCard(
                kursus = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(item) },
                onDeleteClick = {
                    onDeleteClick(item)
                }
            )
        }
    }
}

@Composable
fun KursusCard(
    kursus: Kursus,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kursus) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    kursus.namaKursus,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(kursus) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Kursus"
                    )
                }
            }
            Text(
                text = kursus.deskripsi,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Kategori: ${kursus.kategori}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Harga: Rp${kursus.harga}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}