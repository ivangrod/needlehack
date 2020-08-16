package org.ivangrod.needlehack.domain.post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PostDate {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm");

  private final LocalDateTime collectAt;

  private final LocalDateTime publicationAt;

  public PostDate(LocalDateTime publicationAt) {
    this.collectAt = LocalDateTime.now();
    this.publicationAt = publicationAt;
  }

  public PostDate(LocalDateTime collectAt, LocalDateTime publicationAt) {
    this.collectAt = collectAt;
    this.publicationAt = publicationAt;
  }

  public static PostDate buildStringToPublicationDate(String dateTime) {
    return new PostDate(LocalDateTime.parse(dateTime, TIME_FORMATTER));
  }

  public LocalDateTime getCollectAt() {
    return collectAt;
  }

  public LocalDateTime getPublicationAt() {
    return publicationAt;
  }

  public String publicationDateToStringFormat() {
    return publicationAt.format(TIME_FORMATTER);
  }
}
