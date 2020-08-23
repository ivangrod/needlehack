package org.ivangrod.needlehack.infrastructure.post.persistence.elastic;

import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.post.PostRepository;
import org.ivangrod.needlehack.domain.post.search.PostsFound;
import org.ivangrod.needlehack.domain.shared.criteria.Criteria;
import org.ivangrod.needlehack.infrastructure.shared.persistence.elastic.ElasticsearchClient;
import org.ivangrod.needlehack.infrastructure.shared.persistence.elastic.ElasticsearchRepository;
import org.springframework.stereotype.Service;

@Service
public final class ElasticsearchPostRepository extends ElasticsearchRepository<Post> implements
        PostRepository {

    public ElasticsearchPostRepository(ElasticsearchClient client) {
        super(client);
    }

    @Override
    public void save(Post post) {
        persist(post.getId().value(), post.toPrimitives());
    }

    @Override
    public PostsFound searchAll() {
        SearchResult searchResult = searchAllInElastic(Post::fromPrimitives);
        return new PostsFound(searchResult.getTotal(), searchResult.getResults());
    }

    @Override
    public PostsFound matching(Criteria criteria) {
        SearchResult searchResult = searchByCriteria(criteria, Post::fromPrimitives);
        return new PostsFound(searchResult.getTotal(), searchResult.getResults());
    }

    @Override
    protected String moduleName() {
        return "posts";
    }
}
