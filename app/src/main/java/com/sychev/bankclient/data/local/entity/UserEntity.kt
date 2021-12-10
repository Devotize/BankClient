package com.sychev.bankclient.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sychev.bankclient.domain.model.user_data.TransactionHistory

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