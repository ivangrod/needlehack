package com.ivangrod.needlehack.pill.domain

data class Embedding(val value: DoubleArray) {

    fun valueAsString(): String {
        return value.joinToString(prefix = "[", postfix = "]")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Embedding
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }
}
