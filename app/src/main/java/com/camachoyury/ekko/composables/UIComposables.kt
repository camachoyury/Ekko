package com.camachoyury.ekko.composables

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp



//@Composable
//fun ShirtStoreToolBar(title: @Composable () -> Unit){
//    val context = LocalContext.current
//    TopAppBar(
//        title = {
//            title()
//        },
//        navigationIcon = {
//            IconButton(onClick = { }) {
//                Icon(Icons.Filled.Menu, tint = Color.Black, contentDescription = "")
//            }
//        },
//        actions = {
//            IconButton(onClick = {  context.startActivity(Intent(context, CartActivity::class.java)) }) {
//                Icon(Icons.Filled.ShoppingCart, contentDescription = "", tint = Color.Black)
//            }
//        },
//        backgroundColor = Color.White,
//        contentColor = Color.White,
//        elevation = 12.dp
//    )
//}

@Composable
fun ProgressBar(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp),
            color = Color.Blue,
            strokeWidth = 10.dp
        )
    }

}

