package com.example.macruduinative.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrugViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DrugRepository

    // Using LiveData and caching what getDrugs returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allDrugs: LiveData<List<Drug>>

    init {
        val drugDao = DrugRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = DrugRepository(drugDao)
        allDrugs = repository.allDrugs
    }


    fun insert(id: String, name: String, price: String, producer: String, provider: String, quantity: String) = viewModelScope.launch(Dispatchers.IO) {
        val drug = Drug(id, name, price.toFloat(), producer, provider, quantity.toInt())
        repository.insert(drug)
    }

    fun delete(id: String)  = viewModelScope.launch(Dispatchers.IO) {
        repository.remove(id)
    }

    fun update(id: String, name: String, price: String, producer: String, provider: String, quantity: String)  = viewModelScope.launch(Dispatchers.IO) {
        val drug = Drug(id, name, price.toFloat(), producer, provider, quantity.toInt())
        repository.update(drug)
    }
}