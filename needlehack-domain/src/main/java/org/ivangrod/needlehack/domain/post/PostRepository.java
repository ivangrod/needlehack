package org.ivangrod.needlehack.domain.post;

import org.ivangrod.needlehack.domain.post.search.PostsFound;
import org.ivangrod.needlehack.domain.shared.criteria.Criteria;

public interface PostRepository {

  void save(Post post);

  PostsFound searchAll();

  PostsFound matching(Criteria criteria);
}
