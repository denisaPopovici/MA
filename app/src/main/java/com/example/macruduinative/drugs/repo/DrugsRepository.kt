package com.example.macruduinative.drugs.repo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.macruduinative.drugs.domain.Drug
import javax.inject.Inject

interface DrugsRepository {
    suspend fun getAllDrugs(): List<Drug>
    suspend fun getDrugByName(name: String): Drug
    suspend fun getDrugByID(ID: String): Drug
    suspend fun deleteDrug(ID : String)
    suspend fun updateDrug(ID: String, name:String, price: String, producer: String, provider: String, releaseYear: String, quantity: String) : String
    suspend fun addDrug(ID: String, name:String, price: String, producer: String, provider: String, releaseYear: String, quantity: String) : String
}

class DrugsRepositoryImpl @Inject constructor(
) : DrugsRepository {

    val listOfDrugs: ArrayList<Drug> = arrayListOf(Drug( "unu", "Algocalmin", 28F, "MockProvider", "MockProducer", 2008, 210), Drug("doi", "Ibuprofen", 16F, "MockProvider", "MockProducer", 2000, 0), Drug("trei","Nurofen", 16F, "MockProvider", "MockProducer", 2001, 35), Drug("patru", "Paracetamol", 13F, "MockProvider", "MockProducer", 2007, 43),
    Drug("cinci", "Strepsils", 33F, "MockProvider", "MockProducer", 2007, 21) , Drug("sase", "Bepanthen", 30F, "MockProvider", "MockProducer", 2017, 1),
        Drug("sapte", "Omeran", 22F, "MockProvider", "MockProducer", 2017, 18)


    )


    override suspend fun getAllDrugs(): List<Drug> {
        return listOfDrugs;
    }

    override suspend fun getDrugByName(name: String): Drug {
        for (drug in getAllDrugs())
            if(drug.name == name)
                return drug;

        return Drug("", "", 0F, "", "", 0, 0)
    }

    override suspend fun getDrugByID(ID: String): Drug {
        for (drug in getAllDrugs())
            if(drug.ID == ID)
                return drug;

        return Drug("","", 0F, "", "", 0, 0)
    }


    override suspend fun addDrug(
        ID: String,
        name: String,
        price: String,
        producer: String,
        provider: String,
        releaseYear: String,
        quantity: String
    ) : String {
        var message =""
        if(ID == "")
            message += "The ID cannot be null!\n"
        if(name == "")
            message += "The name cannot be null!\n"
        if(price == "")
            message += "The price cannot be null!\n"
        if(price != "" && price.toFloat() < 0)
            message += "The price cannot be negative!\n"
        if(producer == "")
            message += "The producer cannot be null!\n"
        if(provider == "")
            message += "The provider cannot be null!\n"
        if(releaseYear.toString() == "")
            message += "The release year cannot be null!\n"
        if(releaseYear != "" && (releaseYear.toInt() < 1850 || releaseYear.toInt() > 2021))
            message += "The release year is a number between 1850 and 2021!\n"
        if(quantity.toString() == "")
            message += "The quantity cannot be null!\n"
        if(quantity != "" && quantity.toInt() < 0)
            message += "The quantity cannot be negative!\n"
        if(message == "") {
            val drug: Drug = Drug(ID, name, price.toFloat(), provider, producer, releaseYear.toInt(), quantity.toInt())
            listOfDrugs.add(drug)
        }
        return message
    }

    override suspend fun deleteDrug(ID: String) {
        val drug = getDrugByID(ID)
        listOfDrugs.remove(drug)
    }

    override suspend fun updateDrug(
        ID: String,
        name: String,
        price: String,
        producer: String,
        provider: String,
        releaseYear: String,
        quantity: String
    ) : String {
        var message =""
        if(ID == "")
            message += "The ID cannot be null!\n"
        if(name == "")
            message += "The name cannot be null!\n"
        if(price == "")
            message += "The price cannot be null!\n"
        if(price != "" && price.toFloat() < 0)
            message += "The price cannot be negative!\n"
        if(producer == "")
            message += "The producer cannot be null!\n"
        if(provider == "")
            message += "The provider cannot be null!\n"
        if(releaseYear.toString() == "")
            message += "The release year cannot be null!\n"
        if(releaseYear != "" && (releaseYear.toInt() < 1850 || releaseYear.toInt() > 2021))
            message += "The release year is a number between 1850 and 2021!\n"
        if(quantity.toString() == "")
            message += "The quantity cannot be null!\n"
        if(quantity != "" && quantity.toInt() < 0)
            message += "The quantity cannot be negative!\n"

        if(message == "")
            for (drug in getAllDrugs())
                if(drug.ID == ID) {
                    drug.name = name;
                    drug.price = price.toFloat();
                    drug.producer = producer;
                    drug.provider = provider;
                    drug.releaseYear = releaseYear.toInt();
                    drug.quantity = quantity.toInt();
                }

        return message

    }
}