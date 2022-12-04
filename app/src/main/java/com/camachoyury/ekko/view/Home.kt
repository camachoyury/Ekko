package com.camachoyury.ekko.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.camachoyury.ekko.view.composables.ProgressBar
import com.camachoyury.ekko.domain.Item
import com.camachoyury.ekko.view.ui.theme.EkkoTheme

@ExperimentalFoundationApi
@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val items = viewModel.itemList.collectAsState()

    val context = LocalContext.current


    when (items.value) {
        is HomeViewModel.ItemListState.Success -> {
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                content = {
                    val i = (items.value as HomeViewModel.ItemListState.Success).items

                    items(i.size) { index ->
                        Item(i[index], selectItem = {})
                    }
                }
            )
        }

        is HomeViewModel.ItemListState.Error -> {
            Text(text = "EROR")
        }

        is HomeViewModel.ItemListState.LoadingState -> {
            ProgressBar()
        }
    }
}

@Composable
fun LikedIndicator() {
    val selected = remember { mutableStateOf(false) }
    IconButton(
        onClick = { selected.value = !selected.value },
    ) {
        val icon = if (selected.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder
        val color = animateColorAsState(
            targetValue = if (selected.value) Color.Red else Color.Black,
            animationSpec = tween(durationMillis = 2500)
        )
        Icon(imageVector = icon, contentDescription = null, tint = color.value)
    }
}

@Composable
fun Item(item: Item, selectItem: () -> Unit) {
    val context = LocalContext.current
    val imageUri = "@drawable/${item.image}"
    val imageResource =
        context.resources.getIdentifier(imageUri, null, context.packageName)
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .background(Color.White)
            .padding(4.dp)
            .clickable { selectItem() },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd,
            ) {
                LikedIndicator()
            }

            Image(
                painterResource(imageResource),
//                painterResource(R.drawable.ts_10_11017a),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 8.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    item.title, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,
                )

                Text(
                    "${item.price} $",
                    style = TextStyle(color = Color.Red, fontFamily = FontFamily.Monospace),
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EkkoTheme() {
        Item(item = Item(
            "Ladies+YouTube+Favorite+Tee",
            "Ladies YouTube Favorite Tee",
            "ladies_tshirts",
            10.60,
            "They say home is where the heart is. This vibrant tee features the Bay Area address of Google's head office. &amp;nbsp;&lt;div&gt;&lt;br&gt;&lt;/div&gt;&lt;div&gt;Features:&lt;/div&gt;&lt;div&gt;&lt;ul&gt;&lt;li&gt;100% combed cotton.&lt;/li&gt;&lt;li&gt;Made in the USA.&amp;nbsp;&lt;/li&gt;&lt;li&gt;Available in gold with a striking design at front and the white Google logo at back yoke.&amp;nbsp;&lt;/li&gt;&lt;/ul&gt;&lt;/div&gt;",
            "ts_10_23069b"
        ), selectItem = {}
        )
    }
}

