package com.example.countries.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.view.animation.LinearInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.countries.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToHome: () -> Unit) {
    val alpha = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        delay(300L)
        alpha.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = { LinearInterpolator().getInterpolation(it) }
            )
        )
        delay(2000L)
        navigateToHome()
    }

    Box(
        Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha.value),
            painter = painterResource(id = R.drawable.cc_devnations),
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun LightSplashPreview() {
    SplashScreen {}
}

@Composable
@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun NightSplashPreview() {
    SplashScreen {}
}