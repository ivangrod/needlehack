package org.ivangrod.needlehack.domain.post;

import java.util.List;

public interface FeedListener {

  List<Post> extract(Feed feed);
}