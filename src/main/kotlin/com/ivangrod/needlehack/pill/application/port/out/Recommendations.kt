package com.ivangrod.needlehack.pill.application.port.out

import com.ivangrod.needlehack.pill.domain.Embedding
import com.ivangrod.needlehack.pill.domain.Recommendation

fun interface Recommendations {

    fun findSimilar(queryEmbedding: Embedding, limit: Int): List<Recommendation>
}
