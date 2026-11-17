@cart
Feature: Shopping Cart
  As a shopper
  I want to add products to my cart
  so that i can purchase them
  @regression @CART-001
    Scenario: shopper adds a backpack to the cart
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "secret_sauce"
    Then the product catalog should be displayed
    When the shopper adds item "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1 item
    When the shopper opens the cart
    Then the cart should contain "Sauce Labs Backpack"
    When the shopper signs out
    Then the login page is displayed

  @regression @CART-002
  Scenario: Shopper adds two different products to the cart
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "secret_sauce"
    Then the product catalog should be displayed
    When the shopper adds item "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1 item
    When the shopper adds item "Sauce Labs Bike Light" to the cart
    Then the cart badge should show 2 item
    When the shopper opens the cart
    Then the cart should contain "Sauce Labs Backpack"
    And the cart should contain "Sauce Labs Bike Light"
    When the shopper signs out
    Then the login page is displayed

  @regression @CART-003
  Scenario: Shopper adds two different products to the cart and remove an item from the cart
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "secret_sauce"
    Then the product catalog should be displayed
    When the shopper adds item "Sauce Labs Backpack" to the cart
    Then the cart badge should show 1 item
    When the shopper adds item "Sauce Labs Bike Light" to the cart
    Then the cart badge should show 2 item
    When the shopper opens the cart
    Then the cart should contain "Sauce Labs Backpack"
    And the cart should contain "Sauce Labs Bike Light"
    When the shopper remove an item "Sauce Labs Backpack"
    Then the cart should contain "Sauce Labs Bike Light"
    When the shopper signs out
    Then the login page is displayed

  @regression @CART-004
  Scenario Outline: Shopper adds a product to the cart
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "secret_sauce"
    Then the product catalog should be displayed
    When the shopper adds item "<product>" to the cart
    Then the cart badge should show 1 item
    When the shopper opens the cart
    Then the cart should contain "<product>"
    Examples:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |