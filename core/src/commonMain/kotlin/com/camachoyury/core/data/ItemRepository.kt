package com.camachoyury.core.data

import com.camachoyury.core.domain.Item
import com.camachoyury.core.common.ApplicationDispatcher
import com.camachoyury.core.data.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ItemRepository (private val api: Api) {
    fun getItems(): Flow<List<Item>> {
        return flow {

            emit(api.getItems().items)
        }.flowOn(ApplicationDispatcher)
    }


}