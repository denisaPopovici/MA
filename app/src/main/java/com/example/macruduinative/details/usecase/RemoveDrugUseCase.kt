package com.example.macruduinative.details.usecase

import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.repo.DrugsRepository
import javax.inject.Inject

interface RemoveDrugUseCase {
    suspend operator fun invoke( ID: String)
}

class RemoveDrugUseCaseImpl @Inject constructor(
    val repo: DrugsRepository
) : RemoveDrugUseCase {
    override suspend fun invoke(ID: String) {
        return repo.deleteDrug(ID)
    }
}