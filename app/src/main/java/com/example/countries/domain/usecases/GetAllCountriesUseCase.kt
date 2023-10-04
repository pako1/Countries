package com.example.countries.domain.usecases

import com.example.countries.data.sources.remote.wrapper.Result
import com.example.countries.domain.model.Countries
import com.example.countries.domain.repositories.CountriesRepository
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(private val countriesRepository: CountriesRepository) {
    suspend operator fun invoke(): Result<Countries> {
        return countriesRepository.getAllCountries()
    }
}