package com.sychev.shared.data.remote.model.user_data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersDto(
    @SerialName("users")
    val userDtos: List<UserDto>
)