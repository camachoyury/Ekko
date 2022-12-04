package com.camachoyury.ekko

import retrofit2.http.GET

interface ItemService {

    @GET("./")
    suspend fun getItems(): ItemResponse
}