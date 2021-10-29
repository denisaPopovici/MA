package com.example.macruduinative.drugs.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macruduinative.details.usecase.GetDrugUseCase
import com.example.macruduinative.details.usecase.RemoveDrugUseCase
import com.example.macruduinative.details.usecase.UpdateDrugUseCase
import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.usecase.GetDrugsUseCase
import com.example.macruduinative.newdrug.usecase.NewDrugUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugsViewModel @Inject constructor(
    useCase: GetDrugsUseCase,
    private val getDrugUseCase: GetDrugUseCase,
    private val newDrugUseCase: NewDrugUseCase,
    private val removeDrugUseCase: RemoveDrugUseCase,
    private val updateDrugUseCase: UpdateDrugUseCase,
) : ViewModel() {

    private val _listOfDrugs: MutableState<List<Drug>> = mutableStateOf(emptyList())
    private val _drug: MutableState<List<Drug>> = mutableStateOf(emptyList())

    val listOfDrugs: State<List<Drug>> = _listOfDrugs
    val drug: State<List<Drug>> = _drug
    var _message : String = ""


    init {
        viewModelScope.launch {
            val drugList = useCase()
            _listOfDrugs.value = drugList
        }

    }

    fun getDrug(name: String) {
        viewModelScope.launch {
            val drug = getDrugUseCase(name)
            _drug.value = listOf(drug)
        }
    }


    fun addDrug(ID: String,
                name: String,
                price: String,
                producer: String,
                provider: String,
                releaseYear: String,
                quantity: String){

            viewModelScope.launch {
                val message =
                newDrugUseCase(
                    ID,
                    name,
                    price,
                    producer,
                    provider,
                    releaseYear,
                    quantity
                )
                _message = message
            }

    }

    fun removeDrug(ID: String) {
        viewModelScope.launch { removeDrugUseCase(ID) }
    }

    fun updateDrug(ID: String,
                name: String,
                price: String,
                producer: String,
                provider: String,
                releaseYear: String,
                quantity: String){
        viewModelScope.launch {
            val message = updateDrugUseCase(ID, name, price, producer, provider, releaseYear, quantity)
            _message = message
        }
    }


}