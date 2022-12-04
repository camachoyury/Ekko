package com.camachoyury.core.di

import com.camachoyury.core.data.ItemRepository
import com.camachoyury.core.data.network.Api
import com.camachoyury.core.domain.ItemUseCase

object Injector {
    private val api : Api by lazy { return@lazy Api() }

    private val itemRepository: ItemRepository by lazy { return@lazy  ItemRepository(api) }

    val itemUseCase: ItemUseCase by lazy { return@lazy  ItemUseCase(itemRepository) }

}