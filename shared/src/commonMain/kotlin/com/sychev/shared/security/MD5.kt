package com.sychev.shared.security

expect class MD5() {

    fun encrypt(data: String): String

}