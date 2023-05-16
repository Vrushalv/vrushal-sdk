package com.liblab.sdk

import com.liblab.sdk.exception.LordOfTheRingsSDKException
import okhttp3.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class LordOfTheRingsSDKTest {
	private lateinit var httpClient: OkHttpClient
	private lateinit var call: Call
	private lateinit var response: Response
	private lateinit var responseBody: ResponseBody
	private lateinit var sdk: LordOfTheRingsSDK

	@BeforeEach
	fun setUp() {
		httpClient = mock(OkHttpClient::class.java)
		call = mock(Call::class.java)
		response = mock(Response::class.java)
		responseBody = mock(ResponseBody::class.java)
		sdk = LordOfTheRingsSDK("test-api-key")
		sdk.httpClient = httpClient
	}

	@Test
	@Throws(Exception::class)
	fun testGetMovies_SuccessfulResponse() {
		val json = """
            {
                "docs": [
                    {
                        "_id": "1",
                        "name": "The Lord of the Rings"
                    },
                    {
                        "_id": "2",
                        "name": "The Hobbit"
                    }
                ]
            }
        """.trimIndent()

		whenever(response.isSuccessful).thenReturn(true)
		whenever(response.body).thenReturn(responseBody)
		whenever(responseBody.string()).thenReturn(json)
		whenever(httpClient.newCall(any<Request>())).thenReturn(call)
		whenever(call.execute()).thenReturn(response)

		val movies = sdk.getMovies()

		assertEquals(2, movies.size)
		assertEquals("1", movies[0]._id)
		assertEquals("The Lord of the Rings", movies[0].name)
		assertEquals("2", movies[1]._id)
		assertEquals("The Hobbit", movies[1].name)
	}

	@Test
	@Throws(Exception::class)
	fun testGetMovies_FailedResponse() {
		whenever(response.isSuccessful).thenReturn(false)
		whenever(response.code).thenReturn(500)
		whenever(response.message).thenReturn("Internal Server Error")
		whenever(httpClient.newCall(any<Request>())).thenReturn(call)
		whenever(call.execute()).thenReturn(response)

		val exception = org.junit.jupiter.api.assertThrows<LordOfTheRingsSDKException> {
			sdk.getMovies()
		}

		assertEquals("Error retrieving movies: 500 - Internal Server Error", exception.message)
	}

	@Test
	@Throws(Exception::class)
	fun testGetMovieById_SuccessfulResponse() {
		val json = """
            {
                "docs": [
                    {
                        "_id": "1",
                        "name": "The Lord of the Rings"
                    }
                ]
            }
        """.trimIndent()


		whenever(response.isSuccessful).thenReturn(true)
		whenever(response.body).thenReturn(responseBody)
		whenever(responseBody.string()).thenReturn(json)
		whenever(httpClient.newCall(any<Request>())).thenReturn(call)
		whenever(call.execute()).thenReturn(response)

		val movie = sdk.getMovieById("1")

		assertEquals("1", movie?._id)
		assertEquals("The Lord of the Rings", movie?.name)
	}

	@Test
	@Throws(Exception::class)
	fun testGetMovieById_NotFoundResponse() {
		whenever(response.isSuccessful).thenReturn(false)
		whenever(response.code).thenReturn(404)
		whenever(response.message).thenReturn("Not Found")
		whenever(httpClient.newCall(any<Request>())).thenReturn(call)
		whenever(call.execute()).thenReturn(response)

		//val movie = sdk.getMovieById("1")

		assertThrows<LordOfTheRingsSDKException> { sdk.getMovieById("1") }
	}
}