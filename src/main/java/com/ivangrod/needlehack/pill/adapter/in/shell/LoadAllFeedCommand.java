package com.ivangrod.needlehack.pill.adapter.in.shell;

import com.ivangrod.needlehack.pill.application.port.in.CollectPill;
import com.ivangrod.needlehack.pill.application.port.out.Feeds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ShellComponent
public class LoadAllFeedCommand {

	private final Logger log = LoggerFactory.getLogger(LoadAllFeedCommand.class);

	private final Feeds feeds;
	private final CollectPill port;

	public LoadAllFeedCommand(Feeds feeds, CollectPill port) {
		this.feeds = feeds;
		this.port = port;
	}

	@ShellMethod(key = "load-pills", value = "Collect pills for all feeds")
	public String loadPills() {
		this.log.info("Start collecting all pills at {}",
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));

		final List<CompletableFuture<Void>> collectProcess = this.feeds.all().stream().map(
				feed -> CompletableFuture.runAsync(() -> this.port.collect(new CollectPill.CollectPillCommand(feed))))
				.toList();
		CompletableFuture.allOf(collectProcess.toArray(CompletableFuture[]::new)).join();

		this.log.info("End collecting all pills at {}",
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
		return "All pills have been collected";
	}
}
