package com.example.macruduinative.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.viewmodel.DrugsViewModel

@Composable
fun DrugDetailsScreen(
    selectedDrug: String,
    viewModel: DrugsViewModel = hiltViewModel(),
    onDeleteClick: () -> Unit,

    ) {
    viewModel.getDrug(selectedDrug)
    val drug by remember { viewModel.drug}

    Column(modifier = Modifier.background(color = Color.Cyan).fillMaxHeight()) {
        val name = remember { mutableStateOf(TextFieldValue(drug[0].name)) }
        val price = remember { mutableStateOf(TextFieldValue(drug[0].price.toString())) }
        val producer = remember { mutableStateOf(TextFieldValue(drug[0].producer)) }
        val provider = remember { mutableStateOf(TextFieldValue(drug[0].provider)) }
        val year = remember { mutableStateOf(TextFieldValue(drug[0].releaseYear.toString())) }
        val quantity = remember { mutableStateOf(TextFieldValue(drug[0].quantity.toString())) }

        val showDeleteDialog = remember { mutableStateOf(false) }

        val showUpdateDialog = remember { mutableStateOf(false) }


        if (showDeleteDialog.value) {
            alertDialogDelete(viewModel = viewModel,
                onDeleteClick = onDeleteClick,
                ID = drug[0].ID,
                showDialog = showDeleteDialog.value,
                onDismiss = {showDeleteDialog.value = false},
            )
        }



        if (showUpdateDialog.value) {
            alertUpdateDelete(viewModel = viewModel,
                onDeleteClick = onDeleteClick,
                ID = drug[0].ID,
                name.value.text.toString(),
                price.value.text.toString(),
                producer.value.text.toString(),
                provider.value.text.toString(),
                year.value.text.toString(),
                quantity.value.text.toString(),
                showDialog = showUpdateDialog.value,
                onDismiss = {showUpdateDialog.value = false},
            )
        }


        Row(
            modifier =
                Modifier.padding(8.dp)
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Text(
                text = drug[0].name,
                fontSize = 33.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center
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
                .fillMaxWidth()
                .border(3.dp, androidx.compose.ui.graphics.Color.Blue),

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
                .fillMaxWidth()
                .border(3.dp, androidx.compose.ui.graphics.Color.Blue),

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
                    onValueChange = { quantity.value = it },
                )

            }
        }
        Row(
            modifier = Modifier.padding(30.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly){
            Button(
                onClick = {

                    showDeleteDialog.value = true
                }

            ) {
                Text(text = "DELETE")
            }
            Button(
                onClick = {
                    showUpdateDialog.value = true
                }) {
                Text(text = "UPDATE")
            }
        }

    }

}

@Composable
fun alertDialogDelete(
    viewModel: DrugsViewModel = hiltViewModel(),
    onDeleteClick: () -> Unit,
    ID: String,
    showDialog: Boolean,
    onDismiss: () -> Unit)
{

    if (showDialog) {
        AlertDialog(
            onDismissRequest =
                onDismiss
            ,
            title = {
                Text(
                    text = "Are you sure you want to delete this drug?", textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                            viewModel.removeDrug(ID);
                            onDeleteClick()
                        }
                    ) {
                        Text("YES")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("NO")
                    }
                }
            }
        )
    }
}

@Composable
fun alertUpdateDelete(
    viewModel: DrugsViewModel = hiltViewModel(),
    onDeleteClick: () -> Unit,
    ID: String,
    name: String,
    price: String,
    producer: String,
    provider: String,
    releaseYear: String,
    quantity: String,
    showDialog: Boolean,
    onDismiss: () -> Unit)
{

    if (showDialog) {
        val showErrorDialog = remember { mutableStateOf(false) }

        if (showErrorDialog.value) {
            alertDialog(viewModel = viewModel,
                showDialog = showErrorDialog.value,
                onDismiss = {showErrorDialog.value = false
                            onDismiss()},
            )
        }

        AlertDialog(
            onDismissRequest =
            onDismiss
            ,
            title = {
                Text(text = "Are you sure you want to update the information for this drug?", textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp)
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            viewModel.updateDrug(
                                ID,
                                name, price, producer, provider, releaseYear, quantity
                            );
                            if(viewModel._message==""){ //nu avem erori
                                onDismiss()
                                onDeleteClick()
                            }
                            else { //avem erori de validare
                                showErrorDialog.value = true
//                                onDismiss()
                            }
                        }
                    ) {
                        Text("YES")
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text("NO")
                    }
                }
            }
        )
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
                Text(text = viewModel._message, textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold, fontSize = 20.sp)
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