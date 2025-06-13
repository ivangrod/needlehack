package com.ivangrod.needlehack.pill.domain

@JvmInline
value class Uri private constructor(val value: String) {
    companion object {

        private const val SOURCE_PARAM_LINK = "?source"

        fun of(raw: String): Uri {
            val processed = raw.substringBefore(SOURCE_PARAM_LINK)
            return Uri(processed)
        }
    }
}