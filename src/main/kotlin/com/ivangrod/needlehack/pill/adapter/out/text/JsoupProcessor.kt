package com.ivangrod.needlehack.pill.adapter.out.text

import com.ivangrod.needlehack.pill.application.port.out.ContentProcessor
import com.ivangrod.needlehack.pill.domain.Content
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.safety.Safelist

class JsoupProcessor : ContentProcessor {

    override fun execute(rawContent: String?): Content =

        Jsoup.clean(
            rawContent ?: "", "", Safelist.none(),
            Document.OutputSettings().prettyPrint(false)
        )
            .let { StringEscapeUtils.unescapeHtml4(it) }
            .let { pillContent -> Content(pillContent) }


}
