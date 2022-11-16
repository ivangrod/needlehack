package com.ivangrod.needlehack.pill.adapter.`in`.shell

import com.ivangrod.needlehack.pill.application.port.`in`.CollectPill
import com.ivangrod.needlehack.pill.application.port.out.Feeds
import com.ivangrod.needlehack.pill.domain.ChannelName
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellOption
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//@ShellComponent
class LoadFeedCommand(val feeds: Feeds, val port: CollectPill) {

    //@ShellMethod("Collect pills for the feed provided by the argument")
    fun loadPill(@ShellOption("-F", "--feed") name: String): String {
        log.info(
            "Start collecting pills for [${name.uppercase()}] at ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
            }"
        )
        feeds.byChannelName(ChannelName(name))?.let { port.collect(CollectPill.CollectPillCommand(it)) }

        log.info(
            "End collecting pills for [${name.uppercase()}] at ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
            }"
        )

        return "All pills for [${name.uppercase()}] feed have been collected"
    }

    //@ShellMethod("Collect pills for all feeds")
    fun loadPills(): String {
        log.info(
            "Start collecting all pills at ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
            }"
        )
        feeds.all().parallelStream().forEach { port.collect(CollectPill.CollectPillCommand(it)) }
        log.info(
            "End collecting all pills at ${
                LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
            }"
        )
        return "All pills have been collected"
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(LoadFeedCommand::class.java)
    }
}
