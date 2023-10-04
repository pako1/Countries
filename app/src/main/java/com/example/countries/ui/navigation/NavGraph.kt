package com.example.countries.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.countries.domain.model.Countries
import com.example.countries.domain.model.Country
import com.example.countries.ui.screens.SplashScreen
import com.example.countries.ui.screens.detail.DetailScreen
import com.example.countries.ui.screens.detail.DetailViewModel
import com.example.countries.ui.screens.home.HomeScreen
import com.example.countries.ui.screens.home.HomeViewModel
import com.example.countries.ui.screens.state.UIState


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                navigateToHome = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(route = Screen.Home.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val homeUIState: State<UIState<Countries>> = homeViewModel.homeUIState.collectAsState()
            HomeScreen(
                homeUiState = homeUIState.value,
                clickEvent = { countryName ->
                    navController.navigate(route = Screen.Detail.passCountryName(countryName))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAIL_ARGUMENT) { type = NavType.StringType })
        ) { backStackEntry ->
            val detailViewModel = hiltViewModel<DetailViewModel>()
            backStackEntry.arguments?.getString(DETAIL_ARGUMENT)?.let { countryName ->
                detailViewModel.loadCountryData(countryName)
            } ?: navController.popBackStack()
            val detailUIState: State<UIState<Country>> = detailViewModel.uiState.collectAsState()
            DetailScreen(detailUIState = detailUIState.value)
        }
    }
}