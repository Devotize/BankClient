package com.sychev.shared.backend.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenDataModel(
    @SerialName("properties")
    val properties: List<Pair<String, Long>>,
) {
    companion object {
        val emptyModel = TokenDataModel(emptyList())
    }
}