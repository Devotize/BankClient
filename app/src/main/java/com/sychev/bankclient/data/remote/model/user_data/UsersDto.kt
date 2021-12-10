package com.sychev.bankclient.data.remote.model.user_data


import com.google.gson.annotations.SerializedName

data class UsersDto(
    @SerializedName("users")
    val userDtos: List<UserDto>
)