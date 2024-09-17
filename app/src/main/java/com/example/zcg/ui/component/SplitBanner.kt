package com.example.zcg.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zcg.model.Item

@Composable
fun SplitBanner(
    items: List<Item>
) {
    if (items.size == 2) { // show only item size is 2
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
        ) {
            Card(
                Modifier
                    .height(240.dp)
                    .aspectRatio(1f)
                    .weight(1f)
            ) {
                ItemCard(
                    item = items[0],
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Card(
                Modifier
                    .height(240.dp)
                    .aspectRatio(1f)
                    .weight(1f)
            ) {
                ItemCard(
                    item = items[1],
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }
    }
}
