package com.camachoyury.ekko

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("shirts")
    val items: List<Item>
)