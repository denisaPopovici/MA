package com.example.macruduinative.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Drug::class], version = 5, exportSchema = false)
abstract class DrugRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): DrugDao

    companion object {
        @Volatile
        private var INSTANCE: DrugRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): DrugRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrugRoomDatabase::class.java,
                    "drug_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}
