package org.ivangrod.needlehack.infrastructure.acceptance.steps;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import java.time.LocalDateTime;
import java.util.Collections;
import org.ivangrod.needlehack.domain.shared.format.DateFormatter;
import org.ivangrod.needlehack.infrastructure.Application;
import org.ivangrod.needlehack.infrastructure.acceptance.helper.World;
import org.ivangrod.needlehack.infrastructure.post.api.rest.inbound.dto.PostDto;
import org.ivangrod.needlehack.infrastructure.shared.format.JsonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostSteps {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    World world;

    @Given("I create a post with title {string} and uri {string} and author {string} from Netflix")
    public void i_create_a_post_with_title_and_uri_and_author_from_netflix(String title, String uri,
                                                                           String author) throws Exception {
        PostDto postDto = new PostDto(title, uri, author,
                "http://www.netflixtechblog.com/", "NETFLIX",
                "", DateFormatter.dateToString(LocalDateTime.now()), Collections.emptySet());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/posts").content(JsonProcessor.encode(postDto)).accept(APPLICATION_JSON).contentType(APPLICATION_JSON));
        world.setActResponse(resultActions);
    }

    @Given("I have an existing post with title {string} and uri {string} and author {string} from Netflix")
    public void i_have_an_existing_post_with_title_and_uri_and_author_from_netflix(String title, String uri,
                                                                                   String author) throws Exception {
        PostDto postDto = new PostDto(title, uri, author,
                "http://www.netflixtechblog.com/", "NETFLIX",
                "", DateFormatter.dateToString(LocalDateTime.now()), Collections.emptySet());
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/posts").content(JsonProcessor.encode(postDto)).accept(APPLICATION_JSON).contentType(APPLICATION_JSON));
        world.setPreconditionResponse(resultActions);
    }

    @When("I search a post containing {string}")
    public void i_search_a_post_containing_value(String contentValue) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/posts").queryParam("term", contentValue).accept(APPLICATION_JSON));
        world.setActResponse(resultActions);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) throws Exception {
        world.getActResponse().andExpect(status().is(statusCode));
    }

    @And("the response body should contain {string}")
    public void the_response_body_should_contain(String contentValue) throws Exception {
        world.getActResponse().andExpect(content().contentType(APPLICATION_JSON)).andExpect(jsonPath("$.content").isArray()).andExpect(jsonPath("$.content[0].title").value(contentValue));
    }
}
