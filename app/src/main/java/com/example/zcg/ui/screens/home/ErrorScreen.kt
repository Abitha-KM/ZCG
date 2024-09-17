package com.example.zcg.ui.screens.home

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorScreen(modifier: Modifier, error: String) {
    Text(
        error,
        modifier = modifier.wrapContentSize(),
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
    )
}
