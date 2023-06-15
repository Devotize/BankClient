package com.sychev.shared.utils

actual fun getCurrentSystemTimeMillis(): Long =
    System.currentTimeMillis()

actual fun getCurrentSystemTimeSeconds(): Long =
    System.currentTimeMillis() / 1000