package com.ivangrod.needlehack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories()
class NeedlehackApplication

fun main(args: Array<String>) {
    runApplication<NeedlehackApplication>(*args)
}
