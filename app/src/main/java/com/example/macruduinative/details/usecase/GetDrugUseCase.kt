package com.example.macruduinative.details.usecase

import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.repo.DrugsRepository
import javax.inject.Inject

interface GetDrugUseCase {
    suspend operator fun invoke( name: String): Drug
}

class GetDrugUseCaseImpl @Inject constructor(
    val repo: DrugsRepository
) : GetDrugUseCase {
    override suspend fun invoke(name: String): Drug {
        return repo.getDrugByName(name)
    }
}