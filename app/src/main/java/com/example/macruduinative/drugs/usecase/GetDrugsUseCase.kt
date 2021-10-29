package com.example.macruduinative.drugs.usecase

import com.example.macruduinative.drugs.domain.Drug
import com.example.macruduinative.drugs.repo.DrugsRepository
import javax.inject.Inject

interface GetDrugsUseCase {
    suspend operator fun invoke(): List<Drug>
}

class GetDrugsUseCaseImpl @Inject constructor(
    val repo: DrugsRepository
) : GetDrugsUseCase {
    override suspend fun invoke(): List<Drug> {
        return repo.getAllDrugs()
    }
}