package org.ivangrod.needlehack.infrastructure.acceptance.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.time.LocalDateTime;
import java.util.Collections;
import org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreatePostSteps extends AbstractStepsConfiguration {

  @Given("I create a post with title {string} and uri {string} and author {string} from Netflix")
  public void i_create_a_post_with_title_and_uri_and_author_from_netflix(String title, String uri,
      String author) {

    PostDto postDto = new PostDto(title, uri, author,
        "http://www.netflixtechblog.com/", "NETFLIX",
        "", LocalDateTime.now(), Collections.emptySet());
    ResponseEntity<Void> response = template.postForEntity("/posts", postDto, Void.class);
    world.setResponse(response);
  }

  @Then("the response status code should be {int}")
  public void the_response_status_code_should_be(int statusCode) {
    assertThat(world.getResponse()).isNotNull()
        .extracting(ResponseEntity::getStatusCode).isEqualTo(HttpStatus.CREATED);
  }
}
