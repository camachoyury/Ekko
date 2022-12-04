package com.camachoyury.ekko.data

import retrofit2.http.GET

interface ItemService {
    @GET("./")
    suspend fun getItems(): ItemResponse
}