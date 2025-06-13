package com.ivangrod.needlehack.pill.domain.service

import com.ivangrod.needlehack.pill.domain.Embedding

fun interface QueryEmbedding {

    fun getEmbeddingsForQuery(query: String): Embedding?
}