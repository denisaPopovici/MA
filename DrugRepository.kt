package com.example.macruduinative.database

import android.util.Log
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
    suspend fun remove(id: Int) {
        drugDao.delete(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(drug: Drug) {
        drugDao.update(drug)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteDrugs() {
        drugDao.deleteAll()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getOfflineData() : List<Drug> {
        return drugDao.getOfflineData()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getNoOfDrugs() : Int {
        return drugDao.numberOfDrugs
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addDrugs(drugs: List<Drug>?) {
        if (drugs != null) {
            for(drug in drugs){
                drugDao.insert(drug)
            }
        }
    }


}
