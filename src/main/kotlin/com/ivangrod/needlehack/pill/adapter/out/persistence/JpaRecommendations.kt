package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.application.port.out.Recommendations
import com.ivangrod.needlehack.pill.domain.Embedding
import com.ivangrod.needlehack.pill.domain.Recommendation

class JpaRecommendations(private val repository: JpaRecommendationRepository) : Recommendations {

    override fun findSimilar(queryEmbedding: Embedding, limit: Int): List<Recommendation> {
        return repository.findSimilar(queryEmbedding.valueAsString(), limit)
            .map { Recommendation.fromPrimitives(it.uri, it.title, it.originUri, it.channel) }
    }
}
