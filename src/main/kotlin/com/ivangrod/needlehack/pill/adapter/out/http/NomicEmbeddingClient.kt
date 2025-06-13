package com.ivangrod.needlehack.pill.adapter.out.http

import com.ivangrod.needlehack.pill.domain.Embedding
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

class NomicEmbeddingClient (private val restTemplate: RestTemplate) {

    fun getEmbeddings(textForEmbedding: String): Embedding? {
        val url = "http://localhost:11434/api/embeddings"
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val request = HttpEntity(EmbeddingRequest(prompt = textForEmbedding), headers)

        return restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            EmbeddingResponse::class.java
        ).body?.let { Embedding(it.embedding.toDoubleArray()) }
    }
}