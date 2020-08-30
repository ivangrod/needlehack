package org.ivangrod.needlehack.domain.post;

import org.ivangrod.needlehack.domain.shared.StringValueObject;

public class PostContent extends StringValueObject {

  private PostContent(String value) {
    super(value);
  }

  public static PostContent buildWithContentProcessed(String contentWithoutProcessiing,
      ContentProcessor processor) {
    return new PostContent(processor.execute(contentWithoutProcessiing));
  }

  public static PostContent buildWithContentPlain(String contentWithoutProcessiing) {
    return new PostContent(contentWithoutProcessiing);
  }
}
