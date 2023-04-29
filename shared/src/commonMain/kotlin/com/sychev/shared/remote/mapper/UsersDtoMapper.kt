package com.sychev.shared.remote.mapper

import com.sychev.shared.domain.mapper.DomainMapper
import com.sychev.shared.domain.model.user_data.TransactionHistory
import com.sychev.shared.domain.model.user_data.User
import com.sychev.shared.domain.model.user_data.Users
import com.sychev.shared.remote.model.user_data.UsersDto

class UsersDtoMapper: DomainMapper<Users, UsersDto> {
    override fun toDomainModel(model: UsersDto): Users {
        val users = model.userDtos.map { userDto ->
            val transactionHistory = userDto.transactionHistoryDto.map { transactionHistoryDto ->
                TransactionHistory(
                    amount = transactionHistoryDto.amount,
                    date = transactionHistoryDto.date,
                    iconUrl = transactionHistoryDto.iconUrl,
                    title = transactionHistoryDto.title
                )
            }
            User(
                balance = userDto.balance,
                cardNumber = userDto.cardNumber,
                cardholderName = userDto.cardholderName,
                transactionHistory = transactionHistory,
                type = userDto.type,
                valid = userDto.valid
            )
        }
        return Users(
            users = users
        )
    }
}