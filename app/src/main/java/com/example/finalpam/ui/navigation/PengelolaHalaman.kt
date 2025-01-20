package com.example.finalpam.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalpam.ui.kursus.view.DestinasiDetail
import com.example.finalpam.ui.kursus.view.DestinasiEntry
import com.example.finalpam.ui.kursus.view.DestinasiHome
import com.example.finalpam.ui.kursus.view.DestinasiUpdate
import com.example.finalpam.ui.kursus.view.DetailKursusScreen
import com.example.finalpam.ui.kursus.view.EntryKursusScreen
import com.example.finalpam.ui.kursus.view.HomeKursusScreen
import com.example.finalpam.ui.kursus.view.UpdateKursusView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        // Halaman Home
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
    }
}

