package com.sychev.bankclient.data.local.mapper

import com.sychev.bankclient.data.local.entity.UserEntity
import com.sychev.shared.domain.mapper.DomainMapper
import com.sychev.shared.domain.model.user_data.User

class UserEntityMapper: DomainMapper<User, UserEntity> {
    override fun toDomainModel(model: UserEntity): User {
        return User(
            balance = model.balance,
            cardholderName = model.cardholderName,
            cardNumber = model.cardNumber,
            transactionHistory = Converters.jsonToList(model.transactionHistory).toList(),
            type = model.type,
            valid = model.valid,
        )
    }

    fun fromDomainModel(model: User): UserEntity {
        return UserEntity(
            balance = model.balance,
            cardholderName = model.cardholderName,
            cardNumber = model.cardNumber,
            transactionHistory = Converters.listToJson(model.transactionHistory),
            type = model.type,
            valid = model.valid,
        )
    }
}