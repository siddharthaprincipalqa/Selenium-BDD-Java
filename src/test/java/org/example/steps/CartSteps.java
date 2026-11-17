package org.example.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.context.TestContext;
import org.example.pages.CartPage;
import org.example.pages.ProductsPage;
import org.testng.Assert;

public class CartSteps {
    private ProductsPage productsPage;
    private final TestContext context;

    public CartSteps(TestContext context){
        this.context = context;
    }

    @When("the shopper adds item {string} to the cart")
    public void the_shopper_adds_item_to_the_cart(String item){
        productsPage = new ProductsPage(context.getDriver());
        boolean verifyCart  = productsPage.addProductToCart(item);
        Assert.assertTrue(verifyCart, "The cart is invalid");
    }
    @Then("the cart badge should show {int} item")
    public void the_cart_badge_should_show_item(int count){
        productsPage = new ProductsPage(context.getDriver());
        Boolean cartCount = productsPage.getCartCount(count);
        Assert.assertTrue(cartCount, "The cart count is invalid");
    }

    @When("the shopper opens the cart")
    public void the_shopper_opens_the_cart(){
        productsPage = new ProductsPage(context.getDriver());
        productsPage.openCart();
        CartPage cartPage = new CartPage(context.getDriver());
        String getTitle = cartPage.getCartTitle();
        Assert.assertEquals(getTitle, "Your Cart", "The cart Title is invalid");
    }

    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String text){
        CartPage cartPage = new CartPage(context.getDriver());
        int count = cartPage.verifyItemInCart(text);
        Assert.assertEquals(count, 1, "Product not found in the cart");
    }

    @When("the shopper remove an item {string}")
    public void the_shopper_remove_an_item(String item){
        CartPage cartPage = new CartPage(context.getDriver());
        cartPage.removeItemFromCart(item);
    }
}
