package com.example.countries.data.di

import com.example.countries.data.mappers.CountryDtoMapper
import com.example.countries.data.repositories.CountriesRepositoryImpl
import com.example.countries.data.sources.remote.source.RemoteCountriesDataSource
import com.example.countries.data.sources.remote.api.CountriesService
import com.example.countries.data.sources.remote.api.OkhttpClient
import com.example.countries.data.sources.remote.api.RetrofitClient
import com.example.countries.domain.repositories.CountriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkhttpClient.getClient()

    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit = RetrofitClient.getClient(okHttpClient)

    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): CountriesService {
        return retrofit.create(CountriesService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteCountriesDataSource(countriesService: CountriesService): RemoteCountriesDataSource {
        return RemoteCountriesDataSource(countriesService)
    }

    @Provides
    @Singleton
    fun provideCountryDtoMapper(): CountryDtoMapper {
        return CountryDtoMapper()
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(
        remoteCountriesDataSource: RemoteCountriesDataSource,
        countryDtoMapper: CountryDtoMapper
    ): CountriesRepository {
        return CountriesRepositoryImpl(remoteCountriesDataSource, countryDtoMapper)
    }

}