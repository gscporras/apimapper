package com.gscporras.apimapper

import com.gscporras.apimapper.models.User
import junit.framework.TestCase.assertEquals
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Response

class ApiResponseMapperTest {

    private val mapper = ApiResponseMapper()

    @Test
    fun `mapResponse should return Success for successful response`() {
        val responseBody = User(1, "John Doe", "john@example.com")
        val response = Response.success(responseBody)

        val result = mapper.mapResponse(response, User::class.java)

        assert(result is ApiResult.Success)
        assertEquals((result as ApiResult.Success).data, responseBody)
    }

    @Test
    fun `mapResponse should return Error for failed response`() {
        val response = Response.error<User>(404, ResponseBody.create(null, "Not Found"))

        val result = mapper.mapResponse(response, User::class.java)

        assert(result is ApiResult.Error)
        assertEquals((result as ApiResult.Error).message, "Request failed with status: 404")
    }
}