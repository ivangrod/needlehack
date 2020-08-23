package org.ivangrod.needlehack.infrastructure.post.api.rest.inbound;

import java.net.URI;
import org.ivangrod.needlehack.application.post.create.CreatingPostParams;
import org.ivangrod.needlehack.application.post.create.PostCreator;
import org.ivangrod.needlehack.application.post.search.PostFound;
import org.ivangrod.needlehack.application.post.search.PostSearcher;
import org.ivangrod.needlehack.application.post.search.SearchingPostParams;
import org.ivangrod.needlehack.application.post.search.SearchingPostReturn;
import org.ivangrod.needlehack.domain.post.PostId;
import org.ivangrod.needlehack.domain.shared.format.DateFormatter;
import org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto.PostDto;
import org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto.SearchPostResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public final class PostController {

    private PostCreator postCreator;

    private PostSearcher postSearcher;

    public PostController(PostCreator postCreator, PostSearcher postSearcher) {
        this.postCreator = postCreator;
        this.postSearcher = postSearcher;
    }

    @PostMapping(path = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPost(@RequestBody PostDto postDto) {

        CreatingPostParams postParamsToCreate = new CreatingPostParams(postDto.getTitle(),
                postDto.getUri(), postDto.getAuthor(),
                postDto.getFeedUri(), postDto.getFeedSource(), postDto.getContent(),
                DateFormatter.stringToDate(postDto.getPublicationDate()), postDto.getTopics());
        postCreator.execute(postParamsToCreate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/posts/{id}").buildAndExpand(PostId.buildFromUri(postDto.getUri()).value()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchPostResponse searchPost(@RequestParam(value = "term", defaultValue = "*") String term,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit) {

        SearchingPostReturn result = postSearcher.execute(new SearchingPostParams(term, limit, page));
        PageRequest pageRequest = PageRequest.of(page, limit);
        return new SearchPostResponse(new PageImpl<PostFound>(result.getPosts(), pageRequest, result.getNumTotal()), HttpStatus.OK);
    }
}
