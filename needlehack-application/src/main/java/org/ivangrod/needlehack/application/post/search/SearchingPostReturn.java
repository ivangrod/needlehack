package org.ivangrod.needlehack.application.post.search;

import java.util.List;

public class SearchingPostReturn {

    private final long numTotal;

    private final List<PostFound> posts;

    public SearchingPostReturn(long numTotal, List<PostFound> posts) {
        this.numTotal = numTotal;
        this.posts = posts;
    }

    public long getNumTotal() {
        return numTotal;
    }

    public List<PostFound> getPosts() {
        return posts;
    }
}
