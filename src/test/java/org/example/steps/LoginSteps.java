package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.config.ConfigReader;
import org.example.context.TestContext;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {
    private final TestContext context;
    private WebDriver driver;
    private LoginPage loginPage;
    public LoginSteps(TestContext context) {
        this.context = context;
    }
    @Given("the shopper is on the login page")
    public void openLoginPage(){
        driver = context.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.open();
    }
    @When("the shopper signs in with username {string} and password {string}")
    public void signIn(String username, String password){
        loginPage.login(username, password);
    }
    @Then("the product catalog should be displayed")
    public void verifyProductCatalog(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title"))).getText();
        Assert.assertEquals(actualTitle, "Products",
                "The product catalog should be displayed after login");
    }
    @Then("the login error should contain {string}")
    public void loginErrorShouldContain(String expectedMessage){
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Expected error containing: " + expectedMessage + ", but found: " + actualMessage);
    }
    @Then("the shopper should remain on the login page")
    public void verifyShopperRemainsLoginPage(){
        Assert.assertEquals(
                driver.getCurrentUrl(),
                ConfigReader.getProperty("base.url"),
                "The shopper should remain on the login page"
        );
    }
}
