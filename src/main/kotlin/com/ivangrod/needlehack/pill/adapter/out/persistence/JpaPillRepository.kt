package com.ivangrod.needlehack.pill.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface JpaPillRepository : JpaRepository<JpaPill, Long> {
}
