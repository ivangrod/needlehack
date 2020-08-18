package org.ivangrod.needlehack.infrastructure.acceptance.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.ivangrod.needlehack.infrastructure.Application;
import org.ivangrod.needlehack.infrastructure.acceptance.helper.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AbstractStepsConfiguration {

  @Autowired
  protected TestRestTemplate template;

  @Autowired
  World world;
}
