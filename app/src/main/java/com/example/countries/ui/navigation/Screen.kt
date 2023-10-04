package com.example.countries.ui.navigation

const val DETAIL_ARGUMENT = "countryName"

sealed class Screen(val route: String) {

    object Splash : Screen(route = "splash_screen")

    object Home : Screen(route = "home_screen")

    object Detail : Screen(route = "detail_screen/{$DETAIL_ARGUMENT}") {
        fun passCountryName(countryName: String): String {
            return "detail_screen/$countryName"
        }
    }
}