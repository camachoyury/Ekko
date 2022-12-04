package com.camachoyury.ekko

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

    fun getTShirtById(id: String): Flow<Item> {
        return flow {
            val item = itemService.getItems().items.find { s -> s.image == id }
            emit(item!!)
        }.flowOn(Dispatchers.IO)
    }
}