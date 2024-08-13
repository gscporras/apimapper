package com.gscporras.apimapper.utils

import retrofit2.Response

fun parseError(response: Response<*>): String {
    return try {
        val errorBody = response.errorBody()?.string()
        // Podrías usar Gson para parsear el cuerpo del error a un objeto si está en JSON
        errorBody ?: "Unknown error"
    } catch (e: Exception) {
        e.message ?: "An error occurred"
    }
}