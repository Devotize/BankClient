package com.sychev.shared.remote.model.user_data


import kotlinx.serialization.SerialName

data class UsersDto(
    @SerialName("users")
    val userDtos: List<UserDto>
)