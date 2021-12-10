package com.sychev.bankclient.ui.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen(route = "main_screen")
    object CardsScreen: Screen(route = "cards_screen")
}
