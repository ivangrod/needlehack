package com.ivangrod.needlehack.pill.domain

import java.math.BigInteger
import java.security.MessageDigest

class PillId(val value: String) {

    companion object {

        fun fromUri(uri: Uri): PillId {
            val md: MessageDigest = MessageDigest.getInstance("MD5")
            md.update(uri.value.toByteArray(charset("UTF-8")), 0, uri.value.length)
            return PillId(BigInteger(1, md.digest()).toString(16))
        }
    }
}
