package com.example.countries.domain.usecases

import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.domain.model.Country
import com.example.countries.domain.repositories.CountriesRepository
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(private val countriesRepository: CountriesRepository) {
    suspend operator fun invoke(countryName: String): Result<Country> {
        return countriesRepository.getCountryByName(countryName = countryName)
    }
}