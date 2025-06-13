package com.ivangrod.needlehack.pill.application.port.`in`

import com.ivangrod.needlehack.pill.domain.Recommendation

fun interface RecommendPill {

    fun recommend(query: RecommendPillQuery): List<Recommendation>
}

data class RecommendPillQuery(val value: String)
