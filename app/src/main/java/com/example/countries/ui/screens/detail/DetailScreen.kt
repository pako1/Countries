package com.example.countries.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.countries.R
import com.example.countries.domain.model.Country
import com.example.countries.ui.screens.common.Failure
import com.example.countries.ui.screens.common.Loading
import com.example.countries.ui.screens.preview.SampleCountryDataProvider
import com.example.countries.ui.screens.state.UIState
import com.example.countries.ui.theme.LightGrey40

@Composable
fun DetailScreen(detailUIState: UIState<Country>) = when (detailUIState) {
    is UIState.Loading -> Loading()
    is UIState.Success -> DisplayCountry(country = detailUIState.data)
    is UIState.Failure -> Failure()
}

@Composable
fun DisplayCountry(country: Country) {
    val scrollableState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.verticalScroll(state = scrollableState)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(country.flags.png)
                .transformations(CircleCropTransformation())
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = "Country Flag",
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .alpha(0.8f)
                .padding(bottom = 16.dp, top = 16.dp),
        )
        Text(
            text = country.name.common,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            color = LightGrey40
        )
        Text(
            text = "Overview",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text(
            text = "Capital: ${country.capital.first()}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Belongs to: ${country.region}",
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 16.dp)
                    .weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Currency: ${country.currencies.currency.name} ${country.currencies.currency.symbol}",
                modifier = Modifier
                    .padding(bottom = 4.dp, end = 8.dp)
                    .weight(1f),
                textAlign = TextAlign.End
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            color = LightGrey40
        )
        Text(
            text = "More Info",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Alternative Spelling",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow { items(country.altSpellings) { Chips(text = it) } }
        Text(
            text = "Country Borders",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        LazyRow(modifier = Modifier.padding(bottom = 16.dp)) {
            items(country.borders) { Chips(text = it, textAlignment = TextAlign.Center) }
        }
    }
}

@Composable
fun Chips(text: String, textAlignment: TextAlign = TextAlign.Start) = Card(
    modifier = Modifier.padding(4.dp),
    colors = CardDefaults.cardColors(containerColor = LightGrey40)
) {
    Text(
        text = text, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), textAlign = textAlignment
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsPreview(@PreviewParameter(SampleCountryDataProvider::class) detailUIState: UIState<Country>) =
    DetailScreen(detailUIState = detailUIState)