package com.example.finalpam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalpam.ui.MainHome.MainHomeScreen
import com.example.finalpam.ui.kursus.view.DestinasiDetail
import com.example.finalpam.ui.kursus.view.DestinasiEntry
import com.example.finalpam.ui.kursus.view.DestinasiHome
import com.example.finalpam.ui.kursus.view.DestinasiUpdate
import com.example.finalpam.ui.kursus.view.DetailKursusScreen
import com.example.finalpam.ui.kursus.view.EntryKursusScreen
import com.example.finalpam.ui.kursus.view.HomeKursusScreen
import com.example.finalpam.ui.kursus.view.UpdateKursusView
import com.example.finalpam.ui.siswa.view.DestinasiDetailSiswa
import com.example.finalpam.ui.siswa.view.DestinasiHomeSiswa
import com.example.finalpam.ui.siswa.view.DestinasiInsertSiswa
import com.example.finalpam.ui.siswa.view.DestinasiUpdateSiswa
import com.example.finalpam.ui.siswa.view.DetailSiswaScreen
import com.example.finalpam.ui.siswa.view.HomeSiswaScreen
import com.example.finalpam.ui.siswa.view.InsertSiswaScreen
import com.example.finalpam.ui.siswa.view.UpdateSiswaScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "main_home", // Halaman utama
    ) {
        // Halaman Utama (Main Home)
        composable("main_home") {
            MainHomeScreen(
                onKursusClick = { navController.navigate(DestinasiHome.route) },
                onSiswaClick = { /* Navigasi ke HomeSiswa */ },
                onInstrukturClick = { /* Navigasi ke HomeInstruktur */ },
                onPendaftaranClick = { /* Navigasi ke HomePendaftaran */ },
            )
        }

        // Halaman Home Kursus
        composable(DestinasiHome.route) {
            HomeKursusScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { idKursus ->
                    navController.navigate("${DestinasiDetail.route}/$idKursus")
                }
            )
        }

        // Halaman Tambah Kursus
        composable(DestinasiEntry.route) {
            EntryKursusScreen(navigateBack = {
                navController.navigate(DestinasiHome.route) {
                    popUpTo(DestinasiHome.route) { inclusive = true }
                }
            })
        }

        // Halaman Detail Kursus
        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.ID_KURSUS) {
                    type = NavType.StringType
                }
            )
        ) {
            val idKursus = it.arguments?.getString(DestinasiDetail.ID_KURSUS)
            idKursus?.let {
                DetailKursusScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { navController.navigate("${DestinasiUpdate.route}/$it") },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Halaman Edit Kursus
        composable(
            DestinasiUpdate.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdate.ID_KURSUS) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateKursusView(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
            )
        }

        // Halaman Home Siswa
        composable(DestinasiHomeSiswa.route) {
            HomeSiswaScreen(
                navigateToItemEntry = { navController.navigate(DestinasiInsertSiswa.route) },
                onDetailClick = { idSiswa ->
                    navController.navigate("${DestinasiDetailSiswa.route}/$idSiswa")
                }
            )
        }

        // Halaman Tambah Siswa
        composable(DestinasiInsertSiswa.route) {
            InsertSiswaScreen(navigateBack = {
                navController.navigate(DestinasiHomeSiswa.route) {
                    popUpTo(DestinasiHomeSiswa.route) { inclusive = true }
                }
            })
        }

        // Halaman Detail Siswa
        composable(
            DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSiswa.ID_SISWA) {
                    type = NavType.StringType
                }
            )
        ) {
            val idSiswa = it.arguments?.getString(DestinasiDetailSiswa.ID_SISWA)
            idSiswa?.let {
                DetailSiswaScreen(
                    navigateBack = {
                        navController.navigate(DestinasiHomeSiswa.route) {
                            popUpTo(DestinasiHomeSiswa.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = { navController.navigate("${DestinasiUpdateSiswa.route}/$it") },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        // Halaman Edit Siswa
        composable(
            DestinasiUpdateSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdateSiswa.ID_SISWA) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateSiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
            )
        }
    }
}


