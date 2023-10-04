package com.example.countries.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countries.domain.model.Countries
import com.example.countries.domain.model.Currencies
import com.example.countries.ui.screens.common.Failure
import com.example.countries.ui.screens.common.Loading
import com.example.countries.ui.screens.preview.SampleCountriesDataProvider
import com.example.countries.ui.screens.state.UIState

@Composable
fun HomeScreen(homeUiState: UIState<Countries>, clickEvent: (String) -> Unit) = when (homeUiState) {
    is UIState.Loading -> Loading()
    is UIState.Success -> DisplayCountries(homeUiState.data, clickEvent)
    is UIState.Failure -> Failure()
}

@Composable
fun DisplayCountries(countries: Countries, clickEvent: (String) -> Unit) {
    LazyColumn {
        items(countries) { country ->
            CountryItem(
                name = country.name.common,
                currencies = country.currencies,
                capital = country.capital,
                region = country.region,
                flag = country.flag,
                clickEvent = clickEvent
            )
        }
    }
}

@Composable
fun CountryItem(
    name: String,
    currencies: Currencies,
    capital: List<String>,
    region: String,
    flag: String,
    clickEvent: (String) -> Unit
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { clickEvent.invoke(name) }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, bottom = 4.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = flag,
            fontSize = 24.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp)
        )
    }
    Text(
        text = "Capital: ${capital.first()}",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Belongs to: $region",
            modifier = Modifier
                .padding(start = 8.dp, bottom = 16.dp)
                .weight(1f),
            textAlign = TextAlign.Start
        )
        Text(
            text = "Currency: ${currencies.currency.name} ${currencies.currency.symbol}",
            modifier = Modifier
                .padding(bottom = 4.dp, end = 16.dp)
                .weight(1f),
            textAlign = TextAlign.End
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "See more",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(end = 2.dp)
        )
        Icon(
            Icons.Default.ArrowForward,
            contentDescription = "More",
            modifier = Modifier
                .size(16.dp)
                .padding(top = 4.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview(@PreviewParameter(SampleCountriesDataProvider::class) homeUiState: UIState<Countries>) {
    HomeScreen(homeUiState = homeUiState, clickEvent = {})
}