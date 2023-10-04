package com.example.countries.domain.model

data class Country(
    val altSpellings: List<String>,
    val borders: List<String>,
    val capital: List<String>,
    val currencies: Currencies,
    val flag: String,
    val flags: Flags,
    val languages: Languages,
    val name: Name,
    val population: Int,
    val region: String
)