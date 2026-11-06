package com.example.praktikum6.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praktikum6.Routes
import com.example.praktikum6.data.repository.StudentLocalDataSource
import com.example.praktikum6.data.source.DefaultStudentRepository
import com.example.praktikum6.ui.detail.DetailViewModel
import com.example.praktikum6.ui.home.HomeViewModel
import com.example.praktikum6.ui.screen.AboutScreen
import com.example.praktikum6.ui.screen.DetailScreen
import com.example.praktikum6.ui.screen.HomeScreen
import com.example.praktikum6.ui.screen.ProfileScreen

@Composable
fun NavigationLabApp() {
    val navController = rememberNavController()
    val dataSource = remember { StudentLocalDataSource() }
    val repository = remember { DefaultStudentRepository(dataSource) }

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
//            Text("hello")
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.factory(repository)
            )
            viewModel.loadStudents()
            LaunchedEffect(2026) {
                viewModel.loadStudents()
            }
            HomeScreen(
                viewModel.uiState,
                onOpenDetail = { id ->
                    navController.navigate(Routes.detail(id))
                },
                onOpenProfile = { id ->
                    navController.navigate(Routes.profile(id))
                },
                onOpenAbout = { id ->
                    navController.navigate(Routes.about(id))
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("studentId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
            val viewModel: DetailViewModel = viewModel(
                factory = DetailViewModel.factory(repository)
            )
            LaunchedEffect(studentId) {
                viewModel.loadStudent(studentId)
            }
            DetailScreen(
                uiState = viewModel.uiState,
                onBack = { navController.popBackStack() }
            )
        }
//        composable(
//            route = Routes.DETAIL,
//            arguments = listOf(
//                navArgument("studentId") {
//                    type = NavType.IntType
//                }
//            )
//        ) { backStackEntry ->
//            val studentId =
//                backStackEntry.arguments?.getInt("studentId") ?: 0
//            DetailScreen(
//                studentId = studentId,
//                onBack = { navController.popBackStack() }
//
//            )
//        }
        composable(
            route = Routes.ABOUT,
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val studentId =
                backStackEntry.arguments?.getInt("studentId") ?: 0
            AboutScreen(
                studentId = studentId,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.PROFILE,
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            )
            ) {backStackEntry ->
            val studentId =
                backStackEntry.arguments?.getInt("studentId") ?: 0
            ProfileScreen(
                studentId = studentId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}