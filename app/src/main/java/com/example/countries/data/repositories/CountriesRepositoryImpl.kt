package com.example.countries.data.repositories

import com.example.countries.data.mappers.CountryDtoMapper
import com.example.countries.data.sources.remote.source.RemoteCountriesDataSource
import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.domain.model.Countries
import com.example.countries.domain.model.Country
import com.example.countries.domain.repositories.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val remoteCountriesDataSource: RemoteCountriesDataSource,
    private val countryDtoMapper: CountryDtoMapper
) : CountriesRepository {
    override suspend fun getAllCountries(): Result<Countries> {
        return when (val networkResult = remoteCountriesDataSource.fetchAllCountries()) {
            is Result.Success -> {
                val countriesDto = networkResult.data
                val countries = countryDtoMapper.mapToCountriesFrom(countriesDto)
                Result.Success(Countries().apply { addAll(countries) })
            }

            is Result.Error -> Result.Error(
                code = networkResult.code,
                message = networkResult.message
            )

            is Result.Failure -> Result.Failure(networkResult.exception)
        }
    }

    override suspend fun getCountryByName(countryName: String): Result<Country> {
        return when (
            val networkResult = remoteCountriesDataSource
                .fetchCountryBy(countryName = countryName)
        ) {
            is Result.Success -> {
                val countryDto = networkResult.data
                val country = countryDtoMapper.mapToCountryFrom(countryDto)
                Result.Success(country)
            }

            is Result.Error -> Result.Error(
                code = networkResult.code,
                message = networkResult.message
            )

            is Result.Failure -> Result.Failure(networkResult.exception)
        }
    }
}