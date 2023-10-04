package com.example.countries.data.sources.remote.api

import com.example.countries.data.model.CountriesDto
import com.example.countries.data.model.CountryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("/v3.1/all")
    suspend fun getAllCountries(): Response<CountriesDto>

    @GET("/v3.1/name/{countryName}")
    suspend fun getCountryByName(@Path("countryName") countryName: String): Response<List<CountryDto>>
}

