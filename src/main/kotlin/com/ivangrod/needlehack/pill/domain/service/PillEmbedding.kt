package com.ivangrod.needlehack.pill.domain.service

import com.ivangrod.needlehack.pill.domain.Content
import com.ivangrod.needlehack.pill.domain.Embedding
import com.ivangrod.needlehack.pill.domain.Title

fun interface PillEmbedding {

    fun getEmbeddingsForPill(title: Title, content: Content): Embedding?
}