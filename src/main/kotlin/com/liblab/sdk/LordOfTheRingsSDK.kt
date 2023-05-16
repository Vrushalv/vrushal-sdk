package com.liblab.sdk

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.liblab.sdk.Constants.MOVIE_URL
import com.liblab.sdk.Constants.QUOTE_URL
import com.liblab.sdk.exception.LordOfTheRingsSDKException
import com.liblab.sdk.model.Movie
import com.liblab.sdk.model.MoviesResponseWrapper
import com.liblab.sdk.model.Quote
import com.liblab.sdk.model.QuotesResponseWrapper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * LordOfTheRingsSDK is an SDK that provides access to the Lord of the Rings API.
 *
 * @param apiKey The API key used for authentication.
 */
class LordOfTheRingsSDK(private val apiKey: String? = System.getenv(Constants.TOKEN)) {
    var httpClient = OkHttpClient()
    private val objectMapper = jacksonObjectMapper()

    /**
     * Sends an HTTP request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @return The HTTP response.
     */
    private fun sendRequest(url: String): Response {
        val request = Request.Builder()
            .url(url)
            .header("Authorization", "Bearer $apiKey")
            .build()

        return httpClient.newCall(request).execute()
    }

    /**
     * Retrieves a list of movies from the Lord of the Rings API.
     *
     * @return The list of movies.
     * @throws IOException If an I/O error occurs during the request.
     * @throws LordOfTheRingsSDKException If an error occurs while retrieving the movies.
     */
    @Throws(IOException::class, LordOfTheRingsSDKException::class)
    fun getMovies(): List<Movie> {
        val response = sendRequest(MOVIE_URL)
        val json = response.body?.string() ?: ""
        if (!response.isSuccessful) {
            throw LordOfTheRingsSDKException("Error retrieving movies: ${response.code} - ${response.message}")
        }
        val moviesResponse = objectMapper.readValue<MoviesResponseWrapper>(json)

        return moviesResponse.docs
    }

    /**
     * Retrieves a movie by its ID from the Lord of the Rings API.
     *
     * @param id The ID of the movie.
     * @return The movie with the specified ID.
     * @throws IOException If an I/O error occurs during the request.
     * @throws LordOfTheRingsSDKException If an error occurs while retrieving the movie.
     */
    @Throws(IOException::class, LordOfTheRingsSDKException::class)
    fun getMovieById(id: String): Movie? {
        val url = "$MOVIE_URL/$id"
        val response = sendRequest(url)
        val json = response.body?.string() ?: ""
        if (!response.isSuccessful) {
            throw LordOfTheRingsSDKException("Error retrieving movie with ID $id: ${response.code} - ${response.message}")
        }
        return if (response.isSuccessful) {
             val resp = objectMapper.readValue(json, MoviesResponseWrapper::class.java)
            resp.docs[0]
        } else {
            null
        }
    }

    /**
     * Retrieves a list of quotes for a movie from the Lord of the Rings API.
     *
     * @param id The ID of the movie.
     * @return The list of quotes for the movie.
     * @throws IOException If an I/O error occurs during the request.
     * @throws LordOfTheRingsSDKException If an error occurs while retrieving the quotes.
     */
    @Throws(IOException::class, LordOfTheRingsSDKException::class)
    fun getMovieQuotes(id: String): List<Quote>? {
        val url = "$MOVIE_URL/$id/quote"
        val response = sendRequest(url)
        val json = response.body?.string() ?: ""
        if (!response.isSuccessful) {
            throw LordOfTheRingsSDKException("Error retrieving quotes $id: ${response.code} - ${response.message}")
        }
        val quotesResponse = objectMapper.readValue<QuotesResponseWrapper>(json)

        return quotesResponse.docs
    }

    /**
     * Retrieves a list of quotes from the Lord of the Rings API.
     *
     * @return The list of movies.
     * @throws IOException If an I/O error occurs during the request.
     * @throws LordOfTheRingsSDKException If an error occurs while retrieving the movies.
     */
    @Throws(IOException::class, LordOfTheRingsSDKException::class)
    fun getQuotes(): List<Quote>? {
        val response = sendRequest(QUOTE_URL)
        val json = response.body?.string() ?: ""
        if (!response.isSuccessful) {
            throw LordOfTheRingsSDKException("Error retrieving quotes ${response.code} - ${response.message}")
        }
        val quotesResponse = objectMapper.readValue<QuotesResponseWrapper>(json)

        return quotesResponse.docs
    }
}



