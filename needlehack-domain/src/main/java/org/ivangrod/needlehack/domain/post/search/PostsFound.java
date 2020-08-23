package org.ivangrod.needlehack.domain.post.search;

import java.util.List;
import org.ivangrod.needlehack.domain.post.Post;

public class PostsFound {

    private final long numTotal;

    private final List<Post> posts;

    public PostsFound(long numTotal, List<Post> posts) {
        this.numTotal = numTotal;
        this.posts = posts;
    }

    public long getNumTotal() {
        return numTotal;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
