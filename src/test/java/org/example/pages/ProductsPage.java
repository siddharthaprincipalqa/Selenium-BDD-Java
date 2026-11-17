package org.example.pages;

import org.example.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final WebDriverWait wait;
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By productCards = By.cssSelector("[data-test='inventory-item']");
    private final By items = By.cssSelector("[data-test='inventory-item-name']");
    private final By cartButton = By.cssSelector("button[data-test^='add-to-cart-']");
    private final By shoppingCart = By.cssSelector("[data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver){
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(
                ConfigReader.getProperty("timeout.seconds"))));
    }

    public void logout(){
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public boolean addProductToCart(String itemName){
        List<WebElement> productName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
        for (WebElement product : productName) {
            String actualName = product.findElement(items).getText();
            if (actualName.equals(itemName)){
                wait.until(ExpectedConditions.elementToBeClickable(product.findElement(cartButton))).click();
                return true;
            }
        }
        return false;
    }

    public Boolean getCartCount(int count){
        return wait.until(ExpectedConditions.attributeContains(shoppingCart, "aria-label", "Cart, "+count+" items"));
    }

    public void openCart(){
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart)).click();
    }
}
