package com.example.texnostrelka_2025_otbor.data.remote.model

data class ComicsNetworkModel(
    val id: String? = null,
    val text: String? = null,
    val description: String? = null,
    val image: String? = null // Храним как Base64 строку
)