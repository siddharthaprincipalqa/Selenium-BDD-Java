package org.example.pages;

import org.example.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private final WebDriverWait wait;
    private final By productCards = By.cssSelector("[data-test='inventory-item']");
    private final By items = By.cssSelector("[data-test='inventory-item-name']");
    private final By cartPageTitle = By.cssSelector("[data-test='title']");
    private final By removeButton = By.cssSelector("button[data-test^='remove-']");

    public CartPage(WebDriver driver){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(
                ConfigReader.getProperty("timeout.seconds")
        )));
    }
    public String getCartTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageTitle)).getText();
    }
    public Integer verifyItemInCart(String itemName){
        List<WebElement> productName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
        for (WebElement product : productName) {
            String actualName = product.findElement(items).getText();
            if (actualName.equals(itemName)){
                return 1;
            }
        }
        throw new IllegalArgumentException("Product not found: " + itemName);
    }
    public void removeItemFromCart(String itemName){
        List<WebElement> productName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
        for (WebElement product : productName) {
            String actualName = product.findElement(items).getText();
            if (actualName.equals(itemName)){
                wait.until(ExpectedConditions.elementToBeClickable(product.findElement(removeButton))).click();
                return;
            }
        }
        throw new IllegalArgumentException("Product not found: " + itemName);
    }
}
