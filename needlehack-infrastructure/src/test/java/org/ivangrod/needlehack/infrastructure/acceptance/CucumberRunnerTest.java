package org.ivangrod.needlehack.infrastructure.acceptance;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/features"},
        features = "src/test/resources/features")
public class CucumberRunnerTest {


}
