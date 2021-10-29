package com.example.macruduinative.drugs

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.viewmodel.DrugsViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemovieapp.navigation.Screen
import com.example.macruduinative.newdrug.NewDrugScreen


@Composable
fun DrugsScreen(
    viewModel: DrugsViewModel = hiltViewModel(),
    onDrugClick: (String) -> Unit,
    onAddClick: () -> Unit,
) {
    val listOfDrugs by remember { viewModel.listOfDrugs }

    Column(modifier = Modifier
        .background(color = androidx.compose.ui.graphics.Color.Cyan).fillMaxHeight()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Drug list:",
                fontSize = 33.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn {
            items(listOfDrugs) { item ->
                SingleDrugItem(
                    drug = item,
                    onDrugClick = onDrugClick
                )
            }
        }
        Button(onClick = { onAddClick() }, modifier = Modifier
            .padding(15.dp).fillMaxWidth().height(100.dp))
            {
            Text(
                textAlign = TextAlign.Center,
                text = "ADD"
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SingleDrugItem(
    drug: Drug,
    onDrugClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable
            { // handling onDrugClick
                onDrugClick(drug.name)
            }
            .border(3.dp, androidx.compose.ui.graphics.Color.Blue),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = drug.name,
                fontSize = 24.sp
            )
            Text(
                text = drug.quantity.toString(),
                fontSize = 24.sp,
            )
        }
    }
}