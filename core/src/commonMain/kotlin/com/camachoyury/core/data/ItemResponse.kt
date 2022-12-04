package com.camachoyury.core.data

import com.camachoyury.core.domain.Item
import kotlinx.serialization.SerialName


data class ItemResponse(
    @SerialName("shirts")
    val items: List<Item>
)