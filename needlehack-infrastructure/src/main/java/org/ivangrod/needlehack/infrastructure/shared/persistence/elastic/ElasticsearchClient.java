package org.ivangrod.needlehack.infrastructure.shared.persistence.elastic;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public final class ElasticsearchClient {

  private final RestHighLevelClient highLevelClient;
  private final String indexPrefix;

  public ElasticsearchClient(RestHighLevelClient highLevelClient, String indexPrefix) {
    this.highLevelClient = highLevelClient;
    this.indexPrefix = indexPrefix;
  }

  public RestHighLevelClient highLevelClient() {
    return highLevelClient;
  }

  public String indexPrefix() {
    return indexPrefix;
  }

  public void persist(String moduleName, HashMap<String, Serializable> plainBody)
      throws IOException {
    IndexRequest request = new IndexRequest(indexFor(moduleName)).source(plainBody);
    highLevelClient().index(request, RequestOptions.DEFAULT);
  }

  public String indexFor(String moduleName) {
    return String.format("%s_%s", indexPrefix(), moduleName);
  }

  public void delete(String index) throws IOException {
    highLevelClient.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
  }
}
