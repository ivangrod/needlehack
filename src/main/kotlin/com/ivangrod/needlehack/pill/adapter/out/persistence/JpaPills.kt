package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.domain.Pill

class JpaPills(private val repository: JpaPillRepository) : Pills {

    override fun save(pill: Pill) {
        repository.save(JpaPill.fromDomain(pill))
    }

    override fun get(id: Long): Pill {
        return repository.getReferenceById(id).toDomain()
    }
}
