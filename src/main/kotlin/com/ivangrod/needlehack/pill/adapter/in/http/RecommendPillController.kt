package com.ivangrod.needlehack.pill.adapter.`in`.http

import com.ivangrod.needlehack.pill.application.port.`in`.RecommendPillQuery
import com.ivangrod.needlehack.pill.application.service.RecommendPillHandler
import com.ivangrod.needlehack.pill.domain.Recommendation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recommend")
class RecommendPillController(
    private val handler: RecommendPillHandler
) {

    @PostMapping
    fun recommend(@RequestBody request: RecommendationRequest): RecommendationResult {
        return RecommendationResult(handler.recommend(RecommendPillQuery(request.query)))
    }
}

data class RecommendationRequest(val query: String)
data class RecommendationResult(val results: List<Recommendation>)