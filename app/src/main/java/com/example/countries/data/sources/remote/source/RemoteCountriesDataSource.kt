package com.example.countries.data.sources.remote.source

import com.example.countries.data.model.CountriesDto
import com.example.countries.data.model.CountryDto
import com.example.countries.data.sources.remote.api.CountriesService
import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.data.sources.remote.wrapper.Result.Error
import com.example.countries.data.sources.remote.wrapper.Result.Failure
import com.example.countries.data.sources.remote.wrapper.Result.Success
import javax.inject.Inject

class RemoteCountriesDataSource @Inject constructor(private val countriesService: CountriesService) {

    suspend fun fetchAllCountries(): Result<CountriesDto> {
        return try {
            val response = countriesService.getAllCountries()
            val body = response.body()
            when {
                response.isSuccessful && body != null -> Success(body)
                else -> Error(code = response.code(), message = response.message())
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }

    suspend fun fetchCountryBy(countryName: String): Result<CountryDto> {
        return try {
            val response = countriesService.getCountryByName(countryName)
            val body = response.body()
            when {
                response.isSuccessful && !body.isNullOrEmpty() -> Success(body.first())
                else -> Error(code = response.code(), message = response.message())
            }
        } catch (e: Exception) {
            Failure(e)
        }
    }
}