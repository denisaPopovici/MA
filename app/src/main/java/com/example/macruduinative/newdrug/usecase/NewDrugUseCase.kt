package com.example.macruduinative.newdrug.usecase

import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.repo.DrugsRepository
import javax.inject.Inject

interface NewDrugUseCase {
    suspend operator fun invoke(ID: String,
                                name: String,
                                price: String,
                                producer: String,
                                provider: String,
                                releaseYear: String,
                                quantity: String) : String
}

class NewDrugUseCaseImpl @Inject constructor(
    val repo: DrugsRepository
) : NewDrugUseCase {
    override suspend fun invoke(ID: String,
                                name: String,
                                price: String,
                                producer: String,
                                provider: String,
                                releaseYear: String,
                                quantity: String): String {

        return repo.addDrug(ID, name, price, producer, provider, releaseYear, quantity)

    }
}