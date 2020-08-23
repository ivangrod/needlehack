package org.ivangrod.needlehack.domain.shared.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

  private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm");

  public static String dateToString(LocalDateTime dateTime) {
    return dateTime.format(TIME_FORMATTER);
  }

  public static LocalDateTime stringToDate(String dateTimeInString) {
    return LocalDateTime.parse(dateTimeInString, TIME_FORMATTER);
  }
}
