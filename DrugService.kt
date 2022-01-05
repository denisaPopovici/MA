package com.example.macruduinative.server.service

import com.example.macruduinative.database.Drug
import retrofit2.http.*

interface DrugService {

    @GET("/api/all-drugs")
    suspend fun getAllDrugs(): List<Drug>

    @POST("/api/new-drug")
    suspend fun addNewDrug(@Body e: Drug): Drug

    @PUT("/api/update-drug")
    suspend fun updateDrug(@Body e: Drug): Drug

    @DELETE("/api/remove-drug/{drugID}")
    suspend fun removeDrug(@Path("drugID") drugId: Int): Drug

    companion object {
        const val SERVICE_ENDPOINT = "http://10.0.2.2:8080"
    }
}