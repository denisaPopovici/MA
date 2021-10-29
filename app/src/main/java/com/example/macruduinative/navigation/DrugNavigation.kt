package com.example.macruduinative.navigation
import android.widget.Toast
import androidx.compose.runtime.Composable
import org.jetbrains.anko.toast

import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composemovieapp.navigation.Screen.*
import androidx.navigation.compose.NavHost
import com.example.macruduinative.details.DrugDetailsScreen
import com.example.macruduinative.drugs.DrugsScreen
import com.example.macruduinative.newdrug.NewDrugScreen

@Composable
fun DrugNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Drugs.route) {
        composable(
            route = Drugs.route
        ) {
            DrugsScreen(
                onDrugClick = { selectedDrug ->
                    navController.navigate("${DrugDetails.route}/$selectedDrug")
                },
                onAddClick = {
                    navController.navigate(NewDrug.route)
                }
            )
        }
        composable(
            route = NewDrug.route
        ) {
            NewDrugScreen(
                onAddClick = {
                    navController.navigate(Drugs.route)
                }
            )
        }
        composable(
            route = "${DrugDetails.route}/{selectedDrug}",
            arguments = listOf(navArgument("selectedDrug") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("selectedDrug")?.let { drug ->
                DrugDetailsScreen(selectedDrug = drug, onDeleteClick = {navController.navigate(Drugs.route)})
            }
        }

    }
}