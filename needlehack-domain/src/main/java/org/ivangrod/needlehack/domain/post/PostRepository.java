package org.ivangrod.needlehack.domain.post;

import java.util.List;
import org.ivangrod.needlehack.domain.shared.criteria.Criteria;

public interface PostRepository {

  void save(Post post);

  List<Post> searchAll();

  List<Post> matching(Criteria criteria);
}
