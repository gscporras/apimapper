package com.gscporras.apimapper

import com.google.gson.Gson
import retrofit2.Response

class ApiResponseMapper {

    private val gson = Gson()

    // Método genérico para mapear la respuesta
    fun <T> mapResponse(response: Response<*>, modelClass: Class<T>): ApiResult<T> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val json = gson.toJson(body)
                val model = gson.fromJson(json, modelClass)
                ApiResult.Success(model)
            } else {
                ApiResult.Error("Response body is null")
            }
        } else {
            ApiResult.Error("Request failed with status: ${response.code()}")
        }
    }
}

// Clase sellada para representar el resultado
sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
}