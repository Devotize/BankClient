package com.sychev.bankclient.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    val cardNumber: String,
    val balance: Double,
    val cardholderName: String,
    val transactionHistory: String,
    val type: String,
    val valid: String
) {
    companion object {
        const val TABLE_NAME = "user_table"
    }
}