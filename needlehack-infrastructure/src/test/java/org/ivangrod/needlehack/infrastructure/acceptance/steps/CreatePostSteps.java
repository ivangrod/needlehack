package org.ivangrod.needlehack.infrastructure.acceptance.steps;

import io.cucumber.java.en.Given;
import java.time.LocalDateTime;
import java.util.Collections;
import org.ivangrod.needlehack.application.post.create.CreatingPostParams;
import org.ivangrod.needlehack.application.post.create.PostCreator;
import org.ivangrod.needlehack.infrastructure.acceptance.helper.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreatePostSteps {

  @Autowired
  PostCreator postCreator;

  @Autowired
  World world;

  @Given("I create a post with title {string} and uri {string} and author {string} from Netflix")
  public void i_create_a_post_with_title_and_uri_and_author_from_netflix(String title, String uri,
      String author) {
    CreatingPostParams params = new CreatingPostParams(title, uri, author,
        "http://www.netflixtechblog.com/", "NETFLIX",
        "", LocalDateTime.now(), Collections.emptySet());
    postCreator.execute(params);
  }
}
