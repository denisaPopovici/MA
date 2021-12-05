package com.example.macruduinative.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drug_table")
data class Drug(@PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "provider") val provider: String, @ColumnInfo(name = "producer") val producer: String, @ColumnInfo(name = "quantity") val quantity: Int)






