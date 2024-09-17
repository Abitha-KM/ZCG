package com.example.zcg.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zcg.model.Item

@Composable
fun Banner(
    items: List<Item>,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(240.dp)
) {
    if (items.isNotEmpty()) {
        Card {
            ItemCard(item = items[0], modifier)
        }
    }
}
