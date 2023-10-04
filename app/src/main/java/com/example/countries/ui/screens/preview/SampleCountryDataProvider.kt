package com.example.countries.ui.screens.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.Currencies
import com.example.countries.domain.model.Currency
import com.example.countries.domain.model.Flags
import com.example.countries.domain.model.Languages
import com.example.countries.domain.model.Name
import com.example.countries.domain.model.NativeName
import com.example.countries.ui.screens.state.UIState

class SampleCountryDataProvider : PreviewParameterProvider<UIState<Country>> {
    override val values: Sequence<UIState<Country>> = sequenceOf(
        UIState.Loading,
        UIState.Success(data = provideCountry(0)),
        UIState.Failure("Yeah"),
        UIState.Success(data = provideCountry(1))
    )

    private fun provideCountry(index: Int): Country {
        val dummyCountries = listOf(
            Country(
                altSpellings = listOf(
                    "DE",
                    "Federal Republic of Germany",
                    "Bundesrepublik Deutschland"
                ),
                borders = listOf("AUT", "BEL", "CZE", "DNK", "FRA", "LUX", "NLD", "POL", "CHE"),
                capital = listOf("Berlin"),
                currencies = Currencies(currency = Currency("CFP france Euro", "€")),
                flag = "\uD83C\uDDE9\uD83C\uDDEA",
                flags = Flags(
                    "The flag of Germany is composed of three equal horizontal bands of black, red and gold.",
                    "https://flagcdn.com/w320/de.png",
                    "https://flagcdn.com/de.svg"
                ),
                languages = Languages("German"),
                name = Name(
                    "Germany",
                    NativeName("Deutschland"),
                    "Federal Republic of Germany"
                ),
                population = 83240525,
                region = "Europe"
            ),
            Country(
                altSpellings = listOf("GR", "Elláda", "Hellenic Republic", "Ελληνική Δημοκρατία"),
                borders = listOf("ALB", "BGR", "TUR", "MKD"),
                capital = listOf("Athens"),
                currencies = Currencies(currency = Currency("Euro", "€")),
                flag = "🇬🇷",
                flags = Flags(
                    "The flag of Greece is composed of nine equal horizontal bands of blue alternating with white. A blue square bearing a white cross is superimposed in the canton.",
                    "https://flagcdn.com/w320/gr.png",
                    "https://flagcdn.com/gr.svg"
                ),
                languages = Languages("Greek"),
                name = Name(
                    "Greece",
                    NativeName("Ελληνική Δημοκρατία"),
                    "Hellenic Republic"
                ),
                population = 10715549,
                region = "Europe"
            )
        )
        return dummyCountries[index]
    }
}