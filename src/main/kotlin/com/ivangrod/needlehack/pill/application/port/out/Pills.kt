package com.ivangrod.needlehack.pill.application.port.out

import com.ivangrod.needlehack.pill.domain.Pill

interface Pills {
    fun save(pill: Pill)

    fun get(id: Long): Pill
}
