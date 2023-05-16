package com.liblab.sdk.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Quote(
    @JsonProperty("_id")
    val _id: String?,
    val dialog: String?,
    val movie: String?,
    val character: String?,
    val id: String?,
)

data class QuotesResponseWrapper(
    @JsonProperty("docs")
    val docs: List<Quote>?,
    val total: String?,
    val limit: String?,
    val offset: String?,
    val page: String?,
    val pages: String?
)