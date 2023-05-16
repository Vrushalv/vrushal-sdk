package com.liblab.sdk

import com.liblab.sdk.exception.LordOfTheRingsSDKException

class Example {
    private val apiKey = System.getenv(Constants.TOKEN) //your-token
    private val lordOfTheRingsSDK = LordOfTheRingsSDK(apiKey)

    fun runExample() {
        try {
            val movies = lordOfTheRingsSDK.getMovies()
            println("Movies:")
            for (movie in movies) {
                println("ID: ${movie._id}")
                println("Name: ${movie.name}")
                println()
            }

            val movieId = "5cd95395de30eff6ebccde56" //your-movie-id
            val movie = lordOfTheRingsSDK.getMovieById(movieId)
            if (movie != null) {
                println("Movie:")
                println("ID: ${movie._id}")
                println("Name: ${movie.name}")
                println()
            } else {
                println("Movie with ID $movieId not found.")
                println()
            }

            val quotes = lordOfTheRingsSDK.getMovieQuotes(movieId)
            if (quotes != null) {
                println("Quotes:")
                for (quote in quotes) {
                    println("ID: ${quote._id}")
                    println("Dialog: ${quote.dialog}")
                    println()
                }
            } else {
                println("Quotes not found for movie with ID $movieId.")
            }

            val allQuotes = lordOfTheRingsSDK.getQuotes()
            if (allQuotes != null) {
                println("All Quotes:")
                for (quote in allQuotes) {
                    println("ID: ${quote._id}")
                    println("Dialog: ${quote.dialog}")
                    println()
                }
            } else {
                println("All Quotes not found.")
            }
        } catch (e: LordOfTheRingsSDKException) {
            println("Error: ${e.message}")
        }
    }
}

fun main() {
    val example = Example()
    example.runExample()
}