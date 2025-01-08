package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.domain.Pill
import com.ivangrod.needlehack.pill.adapter.out.persistence.dto.Pill as PillDTO

class MongoDBPills(private val repository: MongoDBPillRepository) : Pills {

    override fun save(pill: Pill) {
        repository.save(PillDTO.fromDomain(pill))
    }

    override fun get(id: Long): Pill {
        TODO("Not yet implemented")
    }
}
