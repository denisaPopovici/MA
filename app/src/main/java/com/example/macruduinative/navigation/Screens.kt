package com.example.composemovieapp.navigation

sealed class Screen(val route: String) {
    object Drugs : Screen(route = "drugs")
    object DrugDetails : Screen(route = "details")
    object NewDrug : Screen(route = "newdrug")

}