package org.example.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

        @Override
        @DataProvider(name = "scenarios", parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

        @Override
        @Test(
                groups = "cucumber",
                dataProvider = "scenarios",
                retryAnalyzer = RetryAnalyzer.class
        )
        public void runScenario(
                PickleWrapper pickle,
                FeatureWrapper feature) {
                super.runScenario(pickle, feature);
        }

}
