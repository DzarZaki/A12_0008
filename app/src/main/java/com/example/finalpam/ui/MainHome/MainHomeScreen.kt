package com.example.finalpam.ui.MainHome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalpam.R

@Composable
fun MainHomeScreen(
    onKursusClick: () -> Unit,
    onSiswaClick: () -> Unit,
    onInstrukturClick: () -> Unit,
    onPendaftaranClick: () -> Unit,
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFDF7E6))
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Logo Aplikasi",
                    modifier = Modifier
                        .size(450.dp)
                        .padding(bottom = 32.dp)
                )


                Button(
                    onClick = onKursusClick,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Icon Kursus",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Kursus")
                }
                Button(
                    onClick = onSiswaClick,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Icon Siswa",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Siswa")
                }
                Button(
                    onClick = onInstrukturClick,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Icon Instruktur",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Instruktur")
                }
                Button(
                    onClick = onPendaftaranClick,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Icon Pendaftaran",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("Pendaftaran")
                }
            }
        }
    }
}


