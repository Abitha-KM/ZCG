package com.example.zcg.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.zcg.ui.component.Banner
import com.example.zcg.ui.component.HorizontalFreeScroll
import com.example.zcg.ui.component.SplitBanner
import com.zcg.model.SectionType

@Composable
fun SectionsScreen(
    modifier: Modifier,
    sectionListUiState: SectionListUiState
)
{
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        items(sectionListUiState.item) {
            when(it.sectionType) {
                SectionType.HORIZONTAL_FREE_SCROLL -> {
                    HorizontalFreeScroll(
                        it.items
                    )
                }
                SectionType.SPLIT_BANNER -> {
                    SplitBanner(
                        it.items
                    )
                }
                SectionType.BANNER -> {
                    Banner(
                        it.items
                    )
                }
            }
        }
    }
}
