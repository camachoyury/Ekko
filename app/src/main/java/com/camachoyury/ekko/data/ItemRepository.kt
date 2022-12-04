package com.camachoyury.ekko.data

import com.camachoyury.ekko.domain.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemService: ItemService) {

    fun getItems(): Flow<List<Item>> {
        return flow {
            emit(itemService.getItems().items)
        }.flowOn(Dispatchers.IO)
    }

}