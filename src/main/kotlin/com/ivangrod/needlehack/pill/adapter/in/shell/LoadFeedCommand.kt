package com.ivangrod.needlehack.pill.adapter.`in`.shell

import com.ivangrod.needlehack.pill.application.port.`in`.CollectPill
import com.ivangrod.needlehack.pill.application.port.out.Feeds
import com.ivangrod.needlehack.pill.domain.ChannelName
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class LoadFeedCommand(val feeds: Feeds, val port: CollectPill) {

    @ShellMethod("Collect pills for the feed provided by the argument")
    fun loadPill(@ShellOption("-F", "--feed") name: String): String {
        feeds.byChannelName(ChannelName(name))?.let { port.collect(CollectPill.CollectPillCommand(it)) }
        return "All pills for [${name.uppercase()}] feed have been collected"
    }

    @ShellMethod("Collect pills for all feeds")
    fun loadPills(): String {
        feeds.all().forEach { port.collect(CollectPill.CollectPillCommand(it)) }
        return "All pills have been collected"
    }
}
