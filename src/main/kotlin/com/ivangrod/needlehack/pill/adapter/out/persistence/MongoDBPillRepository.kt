package com.ivangrod.needlehack.pill.adapter.out.persistence

import com.ivangrod.needlehack.pill.adapter.out.persistence.dto.Pill
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoDBPillRepository : MongoRepository<Pill, String>
