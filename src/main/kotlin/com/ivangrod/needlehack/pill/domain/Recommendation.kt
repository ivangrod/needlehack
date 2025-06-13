package com.ivangrod.needlehack.pill.domain

data class Recommendation(
    val uri: Uri,
    val id: PillId = PillId.fromUri(uri),
    val title: Title,
    val origin: Feed,
) {

    companion object {

        fun fromPrimitives(uri: String, title: String, origin: String, channel: String): Recommendation =
            Recommendation(uri = Uri.of(uri), title = Title(title), origin = Feed(Uri.of(origin), ChannelName(channel)))
    }
}