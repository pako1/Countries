package com.example.countries.data.mappers

import com.example.countries.data.model.CountriesDto
import com.example.countries.data.model.CountryDto
import com.example.countries.data.model.CurrenciesDto
import com.example.countries.data.model.FlagsDto
import com.example.countries.data.model.LanguagesDto
import com.example.countries.data.model.NameDto
import com.example.countries.data.model.NativeNameDto
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.Currencies
import com.example.countries.domain.model.Currency
import com.example.countries.domain.model.Flags
import com.example.countries.domain.model.Languages
import com.example.countries.domain.model.Name
import com.example.countries.domain.model.NativeName
import javax.inject.Inject

class CountryDtoMapper @Inject constructor() {

    fun mapToCountriesFrom(countriesDto: CountriesDto): List<Country> =
        countriesDto.map { countryDto -> mapToCountryFrom(countryDto) }

    fun mapToCountryFrom(countryDto: CountryDto) = with(countryDto) {
        Country(
            altSpellings = altSpellings ?: listOf("Not Available"),
            borders = borders ?: listOf("Not Available"),
            capital = capital ?: listOf("Not Available"),
            currencies = currenciesDto.mapToCurrencies(),
            flag = flag,
            flags = flagsDto.mapToFlags(),
            languages = languagesDto.mapToLanguages(),
            name = nameDto.mapToName(),
            population = population,
            region = region
        )
    }

    private fun CurrenciesDto?.mapToCurrencies(): Currencies {
        val currencyDto = this?.getCurrency()
        return Currencies(
            Currency(
                name = currencyDto?.name ?: "Not available",
                symbol = currencyDto?.symbol ?: "Not available"
            )
        )
    }

    private fun FlagsDto?.mapToFlags() =
        Flags(alt = this?.alt ?: "", png = this?.png ?: "", svg = this?.svg ?: "")

    private fun LanguagesDto?.mapToLanguages() = Languages(this?.language ?: "Not Available")

    private fun NameDto?.mapToName() = Name(
        common = this?.common ?: "Not Available",
        nativeName = this?.nativeNameDto.mapToNativeName(),
        official = this?.official ?: "Not Available"
    )

    private fun NativeNameDto?.mapToNativeName() = NativeName(this?.deuDto?.common ?: "")
}