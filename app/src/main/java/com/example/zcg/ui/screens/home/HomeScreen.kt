package com.example.zcg.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val sectionListUiState by viewModel.sectionListUiState.collectAsStateWithLifecycle()
    if (sectionListUiState.isLoading) {
        LoadingScreen(modifier)
    }
    sectionListUiState.userMessage?.let {
        val context = LocalContext.current
        ErrorScreen(modifier = modifier, error = context.getString(it))
    } ?: SectionsScreen(modifier, sectionListUiState)
}
