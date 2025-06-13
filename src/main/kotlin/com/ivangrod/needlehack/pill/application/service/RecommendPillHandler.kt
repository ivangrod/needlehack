package com.ivangrod.needlehack.pill.application.service

import com.ivangrod.needlehack.pill.application.port.`in`.RecommendPill
import com.ivangrod.needlehack.pill.application.port.`in`.RecommendPillQuery
import com.ivangrod.needlehack.pill.application.port.out.Recommendations
import com.ivangrod.needlehack.pill.domain.Recommendation
import com.ivangrod.needlehack.pill.domain.service.QueryEmbedding

class RecommendPillHandler(
    private val queryEmbedding: QueryEmbedding,
    private val repository: Recommendations,
) : RecommendPill {

    override fun recommend(query: RecommendPillQuery): List<Recommendation> =
        queryEmbedding.getEmbeddingsForQuery(query.value)
            ?.let { repository.findSimilar(it, limit = 10) }
            ?: emptyList()
}