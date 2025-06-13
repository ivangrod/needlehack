package com.ivangrod.needlehack.pill.adapter.out.http

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class EmbeddingRequest(val prompt: String, val model: String = "nomic-embed-text")

data class EmbeddingResponse @JsonCreator constructor(
    @JsonProperty("embedding")
    val embedding: List<Double> = emptyList(),

    @JsonProperty("tokens")
    val tokens: Int = 0
)