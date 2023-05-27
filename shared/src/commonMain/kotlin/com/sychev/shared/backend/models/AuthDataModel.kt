package com.sychev.shared.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AuthDataModel(
    @SerialName("properties")
    val properties: List<UserPropertiesDataModel>,
) {
    companion object {
        val emptyModel = AuthDataModel(emptyList())
    }
}

@Serializable
internal data class UserPropertiesDataModel(
    val userEmail: String,
    val userPass: String,
)