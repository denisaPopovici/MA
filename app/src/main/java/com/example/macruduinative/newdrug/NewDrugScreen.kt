package com.example.macruduinative.newdrug

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.macruduinative.details.alertDialogDelete
import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.viewmodel.DrugsViewModel

@Composable
fun NewDrugScreen(
    viewModel: DrugsViewModel = hiltViewModel() ,
    onAddClick: () -> Unit,

) {

    Column(modifier = Modifier.background(color = Color.Cyan).fillMaxHeight()) {
        val ID = remember { mutableStateOf(TextFieldValue()) }
        val name = remember { mutableStateOf(TextFieldValue()) }
        val price = remember { mutableStateOf(TextFieldValue()) }
        val producer = remember { mutableStateOf(TextFieldValue()) }
        val provider = remember { mutableStateOf(TextFieldValue()) }
        val year = remember { mutableStateOf(TextFieldValue()) }
        val quantity = remember { mutableStateOf(TextFieldValue()) }

        val showDialog = remember { mutableStateOf(false) }
        var errorMsg = ""


        if (showDialog.value) {
            alertDialog(
                showDialog = showDialog.value,
                onDismiss = {showDialog.value = false},
                viewModel
            )
        }

        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(3.dp, androidx.compose.ui.graphics.Color.Blue)
            ,
            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "ID: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = ID.value,
                    onValueChange = { ID.value = it }
                )

            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(3.dp, androidx.compose.ui.graphics.Color.Blue)
            ,
            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Name: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = name.value,
                    onValueChange = { name.value = it }
                )

            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
            .border(3.dp, androidx.compose.ui.graphics.Color.Blue),

            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Price: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = price.value,
                    onValueChange = { price.value = it }
                )

            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth().border(3.dp, androidx.compose.ui.graphics.Color.Blue),

            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Producer: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = producer.value,
                    onValueChange = { producer.value = it }
                )

            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth().border(3.dp, androidx.compose.ui.graphics.Color.Blue),

            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Provider: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = provider.value,
                    onValueChange = { provider.value = it }
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth().border(3.dp, androidx.compose.ui.graphics.Color.Blue),

            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Release year: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = year.value,
                    onValueChange = { year.value = it }
                )

            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth().border(3.dp, androidx.compose.ui.graphics.Color.Blue),

            elevation = 8.dp,
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Quantity: ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold

                )
                TextField(
                    value = quantity.value,
                    onValueChange = { quantity.value = it }
                )

            }
        }
        Button(
            modifier = Modifier.fillMaxWidth().height(55.dp),
            onClick = {
                viewModel.addDrug(
                    ID.value.text.toString(),
                    name.value.text.toString(),
                    price.value.text.toString(),
                    producer.value.text.toString(),
                    provider.value.text.toString(),
                    year.value.text.toString(),
                    quantity.value.text.toString()
                );
                val message = viewModel._message
                if(message != "") { //avem erori de validare
                    showDialog.value = true
                }
                else //s-a adaugat
                    onAddClick()

        }) {
            Text(text = "ADD")
        }

    }
}

@Composable
fun alertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    viewModel: DrugsViewModel)
{

    if (showDialog) {
        AlertDialog(
            onDismissRequest =
            onDismiss
            ,
            title = {
                Text(text = viewModel._message)
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("OK")
                    }
                }
            }
        )
    }
}