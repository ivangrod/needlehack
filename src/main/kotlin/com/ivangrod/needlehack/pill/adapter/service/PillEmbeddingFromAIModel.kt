package com.ivangrod.needlehack.pill.adapter.service

import com.ivangrod.needlehack.pill.adapter.out.http.NomicEmbeddingClient
import com.ivangrod.needlehack.pill.domain.Content
import com.ivangrod.needlehack.pill.domain.Embedding
import com.ivangrod.needlehack.pill.domain.Title
import com.ivangrod.needlehack.pill.domain.service.PillEmbedding

class PillEmbeddingFromAIModel(val embeddingClient: NomicEmbeddingClient): PillEmbedding {

    override fun getEmbeddingsForPill(title: Title, content: Content): Embedding? {
        return embeddingClient.getEmbeddings(title.value + " " + content.value)
    }
}