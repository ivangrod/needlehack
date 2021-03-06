package org.ivangrod.needlehack.infrastructure.shared.persistence.elastic;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.lucene.search.TotalHits;
import org.apache.lucene.search.TotalHits.Relation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.ivangrod.needlehack.domain.shared.criteria.Criteria;

public abstract class ElasticsearchRepository<T> {

    private final ElasticsearchClient client;
    private final ElasticsearchCriteriaConverter criteriaConverter;

    public ElasticsearchRepository(ElasticsearchClient client) {
        this.client = client;
        this.criteriaConverter = new ElasticsearchCriteriaConverter();
    }

    abstract protected String moduleName();

    protected SearchResult searchAllInElastic(Function<Map<String, Object>, T> unserializer) {
        return searchAllInElastic(unserializer, new SearchSourceBuilder());
    }

    protected SearchResult searchAllInElastic(
            Function<Map<String, Object>, T> unserializer,
            SearchSourceBuilder sourceBuilder
    ) {
        SearchRequest request = new SearchRequest(client.indexFor(moduleName())).source(sourceBuilder);
        try {
            SearchResponse response = client.highLevelClient().search(request, RequestOptions.DEFAULT);

            return new SearchResult(response.getHits().getTotalHits(), Arrays.stream(response.getHits().getHits())
                    .map(hit -> unserializer.apply(hit.getSourceAsMap()))
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new SearchResult(new TotalHits(0, Relation.EQUAL_TO), Collections.emptyList());
    }

    protected SearchResult searchByCriteria(Criteria criteria,
                                            Function<Map<String, Object>, T> unserializer) {
        return searchAllInElastic(unserializer, criteriaConverter.convert(criteria));
    }

    protected void persist(String identifier, HashMap<String, Serializable> plainBody) {
        try {
            client.persist(moduleName(), identifier, plainBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class SearchResult<T> {

        private long total;

        private List<T> results;

        public SearchResult(TotalHits totalHits, List<T> results) {
            this.total = totalHits.value;
            this.results = results;
        }

        public long getTotal() {
            return total;
        }

        public List<T> getResults() {
            return results;
        }
    }
}
