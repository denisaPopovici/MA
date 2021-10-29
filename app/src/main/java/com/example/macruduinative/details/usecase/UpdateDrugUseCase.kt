package com.example.macruduinative.details.usecase

import com.example.macruduinative.drugs.repo.DrugsRepository
import javax.inject.Inject

interface UpdateDrugUseCase {
    suspend operator fun invoke( ID: String,
                                 name: String,
                                 price: String,
                                 producer: String,
                                 provider: String,
                                 releaseYear: String,
                                 quantity: String) : String
}

class UpdateDrugUseCaseImpl @Inject constructor(
    val repo: DrugsRepository
) : UpdateDrugUseCase {
    override suspend fun invoke(ID: String,
                                name: String,
                                price: String,
                                producer: String,
                                provider: String,
                                releaseYear: String,
                                quantity: String) : String {
        return repo.updateDrug(ID, name, price, producer, provider, releaseYear, quantity)
    }
}