package com.camachoyury.ekko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val itemUseCase: ItemUseCase): ViewModel() {

    private val _itemList: MutableStateFlow<ItemListState> =
        MutableStateFlow(ItemListState.LoadingState)
    val itemList: StateFlow<ItemListState> = _itemList.asStateFlow()

    init {
        viewModelScope.launch {
            _itemList.value = ItemListState.LoadingState
            itemUseCase().collect {
                _itemList.value = ItemListState.Success(it)
            }
        }
    }

    sealed class ItemListState {
        object LoadingState : ItemListState()
        class Success(val items: List<Item>) : ItemListState()
        data class Error(val exception: Throwable) : ItemListState()
    }
}