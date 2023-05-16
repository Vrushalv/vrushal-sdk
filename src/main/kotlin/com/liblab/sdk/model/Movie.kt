package com.liblab.sdk.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Movie(
    @JsonProperty("_id")
    val _id: String,
    val name: String,
    val runtimeInMinutes: Int,
    val budgetInMillions: Double,
    val boxOfficeRevenueInMillions: Double,
    val academyAwardNominations: Int,
    val academyAwardWins: Int,
    val rottenTomatoesScore: Double
)

data class MoviesResponseWrapper(
    val docs: List<Movie>,
    val total: String?,
    val limit: String?,
    val offset: String?,
    val page: String?,
    val pages: String?
)