package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.example.steps", "org.example.hooks"},
        tags = "@smoke or @regression",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        }
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {


}
