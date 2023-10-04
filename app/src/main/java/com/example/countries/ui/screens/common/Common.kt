package com.example.countries.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countries.R

@Composable
fun Loading() = CircularProgressIndicator(
    modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        .size(50.dp),
    strokeWidth = 6.dp,
)


@Composable
fun Failure() =
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_connection),
            contentDescription = "No connection"
        )
        Text(text = "Something went wrong, please try again later!")
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingPreview() {
    Loading()
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FailurePreview() {
    Failure()
}
