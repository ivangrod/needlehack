package org.ivangrod.needlehack.infrastructure.shared.format;

import com.google.common.base.CaseFormat;

public class StringFormatter {

  public static String toSnake(String text) {
    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, text);
  }

  public static String toCamel(String text) {
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, text);
  }

  public static String toCamelFirstLower(String text) {
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, text);
  }
}
