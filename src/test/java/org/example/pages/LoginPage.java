package org.example.pages;

import org.example.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(
                ConfigReader.getProperty("timeout.seconds")
        )));
    }

    public void open(){
        driver.get(ConfigReader.getProperty("base.url"));
    }

    public void login(String username, String password){
        wait.until(ExpectedConditions.visibilityOf
                (driver.findElement(usernameInput))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf
                (driver.findElement(passwordInput))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginError)
        ).getText();
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.titleIs("Swag Labs"));
    }
}
