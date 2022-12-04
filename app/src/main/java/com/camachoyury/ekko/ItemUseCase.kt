package com.camachoyury.ekko

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemUseCase @Inject constructor (private val itemRepository: ItemRepository){

    operator fun invoke(): Flow<List<Item>> {
        return itemRepository.getItems()
    }
}