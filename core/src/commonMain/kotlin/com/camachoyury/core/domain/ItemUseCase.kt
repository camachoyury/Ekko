package com.camachoyury.core.domain

import com.camachoyury.core.data.ItemRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ItemUseCase(private val itemRepository: ItemRepository) {

    private val coroutineScope: CoroutineScope = MainScope()

    @OptIn(InternalCoroutinesApi::class)
    fun getItems(success: (List<Item>) -> Unit, failure: (Throwable?) -> Unit) {
        coroutineScope.launch {
            try {
                itemRepository.getItems().collect {
                    success(it)
                }
            } catch (e: Throwable) {
                failure(e)
            }
        }
        coroutineScope.cancel()
    }
}