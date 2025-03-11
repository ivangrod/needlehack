package com.ivangrod.needlehack.pill.adapter.out.persistence

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.core.IndexRequest
import com.ivangrod.needlehack.pill.application.port.out.Pills
import com.ivangrod.needlehack.pill.domain.Pill
import org.slf4j.LoggerFactory
import java.io.IOException

class ElasticSearchPills(private val client: ElasticsearchClient, private val indexPrefix: String) : Pills {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ElasticSearchPills::class.java)
        private const val MODULE_NAME = "pills"
    }

    @Throws(IOException::class)
    private fun persist(identifier: String?, plainBody: HashMap<String?, Any?>?) {
        val request = IndexRequest
            .of { i: IndexRequest.Builder<Map<String?, Any?>?> ->
                i.index(this.indexFor()).id(identifier).document(plainBody)
            }
        client.index(request)
    }

    private fun indexFor(): String {
        return String.format("%s_%s", this.indexPrefix, MODULE_NAME)
    }

    override fun save(pill: Pill) {
        persist(pill.id.value, pill.toPrimitives())
    }

    override fun get(id: Long): Pill {
        throw UnsupportedOperationException()
    }
}
