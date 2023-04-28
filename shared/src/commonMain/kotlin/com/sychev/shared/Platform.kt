package com.sychev.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform