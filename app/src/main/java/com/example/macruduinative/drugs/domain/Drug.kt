package com.example.macruduinative.drugs.domain

data class Drug(
    val ID: String,
    var name: String,
    var price: Float,
    var provider: String,
    var producer: String,
    var releaseYear: Int,
    var quantity: Int
)
