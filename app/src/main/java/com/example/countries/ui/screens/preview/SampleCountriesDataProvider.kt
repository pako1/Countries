package com.example.countries.ui.screens.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.countries.domain.model.Countries
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.Currencies
import com.example.countries.domain.model.Currency
import com.example.countries.domain.model.Flags
import com.example.countries.domain.model.Languages
import com.example.countries.domain.model.Name
import com.example.countries.domain.model.NativeName
import com.example.countries.ui.screens.state.UIState

class SampleCountriesDataProvider : PreviewParameterProvider<UIState<Countries>> {
    override val values: Sequence<UIState<Countries>> = sequenceOf(
        UIState.Success(data = provideData()),
        UIState.Failure("Because I can"),
        UIState.Loading
    )

    private fun provideData(): Countries = Countries().apply {
        add(
            Country(
                altSpellings = emptyList(),
                borders = emptyList(),
                capital = listOf("Berlin"),
                currencies = Currencies(currency = Currency("Euro", "€")),
                flag = "\uD83C\uDDE9\uD83C\uDDEA",
                flags = Flags("", "", ""),
                languages = Languages(""),
                name = Name(
                    "Germany",
                    NativeName("Deutschland"),
                    "Federal Republic of Germany"
                ),
                population = 10715549,
                region = "Europe",
            )
        )
        add(
            Country(
                altSpellings = emptyList(),
                borders = emptyList(),
                capital = listOf("Athens"),
                currencies = Currencies(currency = Currency("Euro", "€")),
                flag = "🇬🇷",
                flags = Flags("", "", ""),
                languages = Languages(""),
                name = Name(
                    "Greece",
                    NativeName("Ελλάδα"),
                    "Hellenic Republic"
                ),
                population = 32,
                region = "Europe"
            )
        )
    }
}
