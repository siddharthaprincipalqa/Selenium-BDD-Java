package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.context.TestContext;
import org.example.pages.LoginPage;
import org.example.pages.ProductsPage;
import org.testng.Assert;


public class LogoutSteps {
    private final TestContext context;
    private ProductsPage productsPage;

    public LogoutSteps(TestContext context) {
        this.context = context;
    }

    @When("the shopper signs out")
    public void signOut(){
        productsPage = new ProductsPage(context.getDriver());
        productsPage.logout();
    }
    @Then("the login page is displayed")
    public void verifyLogoutSuccess(){
        LoginPage loginPage =
                new LoginPage(context.getDriver());

        Assert.assertTrue(
                loginPage.isDisplayed(),
                "The login form should be displayed after logout"
        );
        Assert.assertEquals(
                context.getDriver().getCurrentUrl(),
                "https://www.saucedemo.com/",
                "The shopper should remain on the login page"
        );
    }
}
