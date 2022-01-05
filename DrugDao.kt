package com.example.macruduinative.database

import androidx.lifecycle.LiveData;
import androidx.room.*


@Dao
interface DrugDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from drug_table")
    fun getDrugs(): LiveData<List<Drug>>

    @Query("SELECT * from drug_table WHERE is_offline=1")
    fun getOfflineData(): List<Drug>

    @get:Query("select count(*) from drug_table")
    val numberOfDrugs: Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(drug: Drug)

    @Query("DELETE FROM drug_table")
    fun deleteAll()

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(drug: Drug)

    @Query("DELETE FROM drug_table WHERE id=:given_id")
    fun delete(given_id:Int)

}
