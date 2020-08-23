package org.ivangrod.needlehack.application.post.search;

public class SearchingPostParams {

    private String searchingTerm;

    private int limit;

    private int offset;

    public SearchingPostParams(String searchingTerm, int limit, int offset) {
        this.searchingTerm = searchingTerm;
        this.limit = limit;
        this.offset = offset;
    }

    public String getSearchingTerm() {
        return this.searchingTerm;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
