package com.example.macruduinative.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class DrugRepository(private val drugDao: DrugDao) {

    // Observed LiveData will notify the observer when the data has changed.
    val allDrugs: LiveData<List<Drug>> = drugDao.getDrugs()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(drug: Drug) {
        drugDao.insert(drug)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun remove(id: String) {
        drugDao.delete(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(drug: Drug) {
        drugDao.update(drug)
    }


}
