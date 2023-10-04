package com.example.countries.domain.model

data class Currencies(val currency: Currency)


data class Currency(val name: String? = "", val symbol: String? = "")