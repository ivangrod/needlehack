package org.ivangrod.needlehack.application.post.search;

public class SearchingPostParams {

    private String searchingTerm;

    public SearchingPostParams(String searchingTerm) {
        this.searchingTerm = searchingTerm;
    }

    public String getSearchingTerm() {
        return this.searchingTerm;
    }
}
