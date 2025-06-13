package com.ivangrod.needlehack.pill.adapter.service

import com.ivangrod.needlehack.pill.adapter.out.http.NomicEmbeddingClient
import com.ivangrod.needlehack.pill.domain.Embedding
import com.ivangrod.needlehack.pill.domain.service.QueryEmbedding

class QueryEmbeddingFromAIModel(val embeddingClient: NomicEmbeddingClient): QueryEmbedding {

    override fun getEmbeddingsForQuery(query: String): Embedding? {
        return embeddingClient.getEmbeddings(query)
    }
}