package org.ivangrod.needlehack.infrastructure.shared.persistence.elastic;

import java.util.HashMap;
import java.util.function.Function;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.ivangrod.needlehack.domain.shared.criteria.Criteria;
import org.ivangrod.needlehack.domain.shared.criteria.Filter;
import org.ivangrod.needlehack.domain.shared.criteria.FilterOperator;
import org.ivangrod.needlehack.domain.shared.criteria.Filters;

public final class ElasticsearchCriteriaConverter {

  private final HashMap<FilterOperator, Function<Filter, QueryBuilder>> queryTransformers =
      new HashMap<FilterOperator, Function<Filter, QueryBuilder>>() {{
        put(FilterOperator.EQUAL, ElasticsearchCriteriaConverter.this::termQuery);
        put(FilterOperator.NOT_EQUAL, ElasticsearchCriteriaConverter.this::termQuery);
        put(FilterOperator.GT, ElasticsearchCriteriaConverter.this::greaterThanQueryTransformer);
        put(FilterOperator.LT, ElasticsearchCriteriaConverter.this::lowerThanQueryTransformer);
        put(FilterOperator.CONTAINS, ElasticsearchCriteriaConverter.this::wildcardTransformer);
        put(FilterOperator.NOT_CONTAINS, ElasticsearchCriteriaConverter.this::wildcardTransformer);
      }};

  public SearchSourceBuilder convert(Criteria criteria) {
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    sourceBuilder.from(criteria.offset().orElse(0));
    sourceBuilder.size(criteria.limit().orElse(100));

    if (criteria.order().hasOrder()) {
      sourceBuilder.sort(
          criteria.order().orderBy().value(),
          SortOrder.fromString(criteria.order().orderType().value())
      );
    }

    if (criteria.hasFilters()) {
      QueryBuilder queryBuilder = generateQueryBuilder(criteria.filters());

      sourceBuilder.query(queryBuilder);
    }

    return sourceBuilder;
  }

  private QueryBuilder generateQueryBuilder(Filters filters) {
    BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

    for (Filter filter : filters.filters()) {
      QueryBuilder query = queryForFilter(filter);

      if (isPositiveOperator(filter.operator())) {
        // TODO: Test 'should' to combine multiple filters instead of 'must'
        boolQueryBuilder.should(query);
      } else {
        boolQueryBuilder.mustNot(query);
      }
    }

    return boolQueryBuilder;
  }

  private boolean isPositiveOperator(FilterOperator operator) {
    return operator.isPositive();
  }

  private QueryBuilder queryForFilter(Filter filter) {
    Function<Filter, QueryBuilder> transformer = queryTransformers.get(filter.operator());

    return transformer.apply(filter);
  }

  private QueryBuilder termQuery(Filter filter) {
    return QueryBuilders.termQuery(filter.field().value(), filter.value().value().toLowerCase());
  }

  private QueryBuilder greaterThanQueryTransformer(Filter filter) {
    return QueryBuilders.rangeQuery(filter.field().value())
        .gt(filter.value().value().toLowerCase());
  }

  private QueryBuilder lowerThanQueryTransformer(Filter filter) {
    return QueryBuilders.rangeQuery(filter.field().value())
        .lt(filter.value().value().toLowerCase());
  }

  private QueryBuilder wildcardTransformer(Filter filter) {
    return QueryBuilders.wildcardQuery(
        filter.field().value(),
        String.format("*%s*", filter.value().value().toLowerCase())
    );
  }
}
