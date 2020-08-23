package org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto;

import org.ivangrod.needlehack.application.post.search.PostFound;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SearchPostResponse extends ResponseEntity<Page<PostFound>> {

    public SearchPostResponse(Page<PostFound> body, HttpStatus status) {
        super(body, status);
    }
}
