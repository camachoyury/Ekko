package com.camachoyury.ekko.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camachoyury.core.di.Injector
import com.camachoyury.core.domain.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _itemList: MutableStateFlow<ItemListState> =
        MutableStateFlow(ItemListState.LoadingState)
    val itemList: StateFlow<ItemListState> = _itemList.asStateFlow()

    private val kmmUseCase = Injector.itemUseCase

    init {
        viewModelScope.launch {
            _itemList.value = ItemListState.LoadingState
//            itemUseCase().collect {
//                _itemList.value = ItemListState.Success(it)
//            }
            try {
                kmmUseCase.getItems(
                    success = {
                        _itemList.value = ItemListState.Success(it)
                        print("YURY" + it[1])
                    },
                    failure = {
                        _itemList.value = ItemListState.Error(it!!)
                    })

            } catch (e: Throwable) {
                print(e.stackTrace)
            }

        }
    }

    sealed class ItemListState {
        object LoadingState : ItemListState()
        class Success(val items: List<Item>) : ItemListState()
        data class Error(val exception: Throwable) : ItemListState()
    }
}