Feature: Shopper logout
  As a signed-in shopper
  I want to sign out
  So that I can end my session

@regression @LOGOUT-001
Scenario: Valid shopper signs in successfully
  Given the shopper is on the login page
  When the shopper signs in with username "standard_user" and password "secret_sauce"
  Then the product catalog should be displayed
  When the shopper signs out
  Then the login page is displayed