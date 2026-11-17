package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.context.TestContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    private final TestContext context;
    public Hooks(TestContext context) {
        this.context = context;
    }
    @Before
    public void setup() {
        context.startBrowser();
    }
    @AfterStep
    public void captureStepScreenshot(Scenario scenario) {
        if (context.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step screenshot");
        }
    }
    @After
    public void teardown(Scenario scenario) {
        try{
            if (scenario.isFailed() && context.getDriver() != null) {
                byte[] screenshot = ((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        }finally{
            context.closeBrowser();
        }
    }
}
