package com.camachoyury.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform