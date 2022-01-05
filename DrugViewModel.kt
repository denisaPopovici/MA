package com.example.macruduinative.database

import android.app.Application
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.macruduinative.server.service.DrugService
import com.example.macruduinative.server.service.ServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject




class DrugViewModel(application: Application) : AndroidViewModel(application) {

    val repository: DrugRepository

    private val service: DrugService = ServiceFactory
        .createRetrofitService(DrugService::class.java, DrugService.SERVICE_ENDPOINT)

    private val mutableDrugs = MutableLiveData<List<Drug>>().apply { value = emptyList() }
    private val mutableLoading = MutableLiveData<Boolean>().apply { value = false }
    private val mutableMessage = MutableLiveData<String>()

    var drugs: LiveData<List<Drug>> = mutableDrugs
    val message: LiveData<String> = mutableMessage

    init {
        val drugDao = DrugRoomDatabase.getDatabase(application).wordDao()
        repository = DrugRepository(drugDao)
        drugs = repository.allDrugs
    }


    fun insert(id: Int, name: String, price: String, producer: String, provider: String, quantity: String, is_offline: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        var idNou = id
        if (id == -1) { //must generate an id
             idNou = repository.getNoOfDrugs() + 1
        }
        val drug = Drug(idNou, name, price.toFloat(), producer, provider, quantity.toInt(), is_offline)
        repository.insert(drug)
    }

    fun delete(id: Int)  = viewModelScope.launch(Dispatchers.IO) {
        repository.remove(id)
    }

    fun update(id: Int, name: String, price: String, producer: String, provider: String, quantity: String, is_offline: Boolean)  = viewModelScope.launch(Dispatchers.IO) {
        val drug = Drug(id, name, price.toFloat(), producer, provider, quantity.toInt(), is_offline)
        repository.update(drug)
    }

    fun fetchDrugsFromNetwork() {
        viewModelScope.launch {
            try {
                mutableDrugs.value = service.getAllDrugs()

                launch(Dispatchers.IO) {
                    repository.deleteDrugs()
                    repository.addDrugs(mutableDrugs.value)
                }
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while retrieving the data: ${e.message}"
            }
        }
    }

    fun addDrug(drug: Drug) {
        var added = drug
        viewModelScope.launch {
            try {
                added = service.addNewDrug(drug)
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while adding the data: ${e.message}"
            } finally {
                //add the drug anyway
                launch(Dispatchers.IO) {
                    //maintain the same id
                    drug.id = added.id
                    repository.insert(drug)
                }
            }
        }
    }

    fun updateDrug(drug: Drug) {
        viewModelScope.launch {
            try {
                service.updateDrug(drug)
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while updating the data: ${e.message}"
            } finally {
                //update db takes place anyway
                launch(Dispatchers.IO) {
                    repository.update(drug)
                }
            }
        }
    }

    fun removeDrug(drugID: Int) {
        viewModelScope.launch {
            try {
                service.removeDrug(drugID)
                launch(Dispatchers.IO) {
                    repository.remove(drugID)
                }
            } catch (e: Exception) {
                mutableMessage.value = "Received an error while removing the data: ${e.message}"
            } finally {
                //remove from local db anyway
                launch(Dispatchers.IO) {
                    repository.remove(drugID)
                }
            }
        }
    }


    fun syncOfflineData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("TAG", "HERE")
        for(drug in repository.getOfflineData()) {
            Log.d("TAG", drug.name)
            viewModelScope.launch {
                try {
                    drug.is_offline = false
                    Log.d("TAG", "INAINTE SA ADAUG")
                    service.addNewDrug(drug)
                    Log.d("TAG", "DUPA CE ADAUG")

                    launch(Dispatchers.IO) {
                        update(drug.id, drug.name, drug.price.toString(), drug.producer, drug.provider, drug.quantity.toString(), drug.is_offline)
                    }
                } catch (e: Exception) {
                    mutableMessage.value = "Received an error while syncing the data: ${e.message}"
                }
            }
        }
    }
}