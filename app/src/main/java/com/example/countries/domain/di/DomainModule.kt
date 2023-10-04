package com.example.countries.domain.di

import com.example.countries.domain.repositories.CountriesRepository
import com.example.countries.domain.usecases.GetAllCountriesUseCase
import com.example.countries.domain.usecases.GetCountryByNameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllCountriesUseCase(countriesRepository: CountriesRepository): GetAllCountriesUseCase {
        return GetAllCountriesUseCase(countriesRepository)
    }

    @Provides
    fun provideGetCountryByNameUseCase(countriesRepository: CountriesRepository): GetCountryByNameUseCase {
        return GetCountryByNameUseCase(countriesRepository)
    }
}