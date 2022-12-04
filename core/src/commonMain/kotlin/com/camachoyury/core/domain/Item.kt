package com.camachoyury.core.domain

import kotlinx.serialization.SerialName

@SerialName("shirt")
data class Item( val name : String,
                 val title : String,
                 val category : String,
                 val price : Double,
                 val description : String,
                 val image : String )