package com.example.countries.domain.repositories

import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.domain.model.Countries
import com.example.countries.domain.model.Country

interface CountriesRepository {
    suspend fun getAllCountries(): Result<Countries>
    suspend fun getCountryByName(countryName: String): Result<Country>
}