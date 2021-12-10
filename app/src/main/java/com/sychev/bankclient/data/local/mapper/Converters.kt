package com.sychev.bankclient.data.local.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sychev.bankclient.domain.model.user_data.TransactionHistory

object Converters {

    fun listToJson(value: List<TransactionHistory>) = Gson().toJson(value)

    fun jsonToList(value: String) = Gson().fromJson(value, Array<TransactionHistory>::class.java)
}