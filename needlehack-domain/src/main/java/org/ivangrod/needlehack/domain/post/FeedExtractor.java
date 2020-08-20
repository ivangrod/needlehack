package org.ivangrod.needlehack.domain.post;

import java.util.List;

public interface FeedExtractor {

  List<Post> extract(Feed feed);
}