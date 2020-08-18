package org.ivangrod.needlehack.infrastructure.post.api.rest.inbound;

import java.net.URI;
import org.ivangrod.needlehack.application.post.create.CreatingPostParams;
import org.ivangrod.needlehack.application.post.create.PostCreator;
import org.ivangrod.needlehack.domain.post.PostId;
import org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto.PostDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public final class PostController {

  private PostCreator postCreator;

  public PostController(PostCreator postCreator) {
    this.postCreator = postCreator;
  }

  @PostMapping(path = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addPost(@RequestBody PostDto postDto) {

    CreatingPostParams postParamsToCreate = new CreatingPostParams(postDto.getTitle(),
        postDto.getUri(), postDto.getAuthor(),
        postDto.getFeedUri(), postDto.getFeedSource(), postDto.getContent(),
        postDto.getPublicationDate(), postDto.getTopics());
    postCreator.execute(postParamsToCreate);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
        "/posts/{id}").buildAndExpand(PostId.buildFromUri(postDto.getUri()).value()).toUri();

    return ResponseEntity.created(location).build();
  }
}
