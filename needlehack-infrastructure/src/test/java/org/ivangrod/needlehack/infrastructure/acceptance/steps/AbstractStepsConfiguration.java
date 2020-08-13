package org.ivangrod.needlehack.infrastructure.acceptance.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.ivangrod.needlehack.infrastructure.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class AbstractStepsConfiguration {


}
