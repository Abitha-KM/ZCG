package com.example.zcg.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.zcg.R
import com.zcg.model.Item

@Composable
fun ItemCard(
    item: Item,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = modifier
        )
        Text(
            text = item.title,
            modifier = Modifier.padding(10.dp)
        )
    }
}
