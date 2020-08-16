package org.ivangrod.needlehack.infrastructure.post.persistence.elastic.config;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.ivangrod.needlehack.infrastructure.shared.persistence.elastic.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class ElasticsearchConfiguration {

  @Value("${elasticsearch.host}")
  private String elasticsearchHost;

  @Value("${elasticsearch.port}")
  private int elasticsearchPort;

  @Value("${elasticsearch.schema}")
  private String elasticsearchSchema;

  private final ResourcePatternResolver resourceResolver;

  public ElasticsearchConfiguration(ResourcePatternResolver resourceResolver) {
    this.resourceResolver = resourceResolver;
  }

  @Bean
  public ElasticsearchClient elasticsearchClient() throws IOException {

    Header[] headers = {new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};
    RestClientBuilder restClientBuilder = RestClient
        .builder(new HttpHost(elasticsearchHost, elasticsearchPort, elasticsearchSchema));
    restClientBuilder.setDefaultHeaders(headers);
    RestHighLevelClient highLevelClient = new RestHighLevelClient(restClientBuilder);

    ElasticsearchClient client = new ElasticsearchClient(highLevelClient, "needlehack");
    return client;
  }

  private boolean indexExists(String indexName, ElasticsearchClient client) throws IOException {
    return client.highLevelClient().indices()
        .exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
  }
}
