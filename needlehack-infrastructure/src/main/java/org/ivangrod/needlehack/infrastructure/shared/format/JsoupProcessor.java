package org.ivangrod.needlehack.infrastructure.shared.format;

import org.apache.commons.text.StringEscapeUtils;
import org.ivangrod.needlehack.domain.post.ContentProcessor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupProcessor implements ContentProcessor {

  @Override
  public String execute(String contentWithoutProcessiing) {
    return StringEscapeUtils.unescapeHtml4(Jsoup
        .clean(contentWithoutProcessiing, "", Whitelist.none(),
            new Document.OutputSettings().prettyPrint(false)));
  }
}
