# API Response Mapper

Una librería para mapear fácilmente las respuestas de una API a modelos de datos en Android.

## Instalación

Agrega la dependencia a tu proyecto:

```groovy
dependencies {
    implementation 'com.gscporras:apimapper:1.0.0'
}
```

## Uso
```
val responseMapper = ApiResponseMapper()

// En tu llamada a la API
val response = apiService.getUser() // Supongamos que es una llamada que devuelve
Response<UserResponse>
val result = responseMapper.mapResponse(response, User::class.java)

when (result) {
    is ApiResult.Success -> {
        val user = result.data
        // Usa el objeto de usuario mapeado
    }
    is ApiResult.Error -> {
        val error = result.message
        // Maneja el error
    }
}
```