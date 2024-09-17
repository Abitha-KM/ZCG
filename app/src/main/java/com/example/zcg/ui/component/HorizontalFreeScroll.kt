package com.example.zcg.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zcg.model.Item

@Composable
fun HorizontalFreeScroll(
    items: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(items) {
            Card {
                ItemCard(
                    item = it,
                    modifier
                        .widthIn(max = 124.dp)
                        .heightIn(max = 124.dp)
                )
            }
        }
    }
}
