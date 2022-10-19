package com.ivangrod.needlehack

import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle
import org.springframework.shell.jline.PromptProvider
import org.springframework.stereotype.Component

@Component
class NeedlehackPromptProvider : PromptProvider {

    override fun getPrompt(): AttributedString =
        AttributedString("needlehack:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE))
}
