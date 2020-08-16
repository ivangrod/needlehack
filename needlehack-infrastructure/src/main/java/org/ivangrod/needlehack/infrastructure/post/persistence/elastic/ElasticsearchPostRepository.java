package org.ivangrod.needlehack.infrastructure.post.persistence.elastic;

import java.util.List;
import org.ivangrod.needlehack.domain.post.Post;
import org.ivangrod.needlehack.domain.post.PostRepository;
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
    persist(post.toPrimitives());
  }

  @Override
  public List<Post> searchAll() {
    return searchAllInElastic(Post::fromPrimitives);
  }

  @Override
  public List<Post> matching(Criteria criteria) {
    return searchByCriteria(criteria, Post::fromPrimitives);
  }

  @Override
  protected String moduleName() {
    return "posts";
  }
}
