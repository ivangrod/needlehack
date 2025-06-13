package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.adapter.out.persistence.dto.RecommendationDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface JpaRecommendationRepository : JpaRepository<JpaPill, Long>, JpaSpecificationExecutor<JpaPill> {
    @Query(
        value = """
            SELECT p.uri, p.title, p.origin_uri, p.channel            
            FROM pill p
            ORDER BY p.embedding <=> cast(:embedding AS vector)
            LIMIT :limit
        """, nativeQuery = true
    )
    fun findSimilar(@Param("embedding") embedding: String, @Param("limit") limit: Int): List<RecommendationDTO>
}
