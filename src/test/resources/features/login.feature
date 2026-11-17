Feature: Shopper login
  As a registered shopper
  I want to sign in to the store
  So that I can access the product catalog

  @smoke @LOGIN-001
  Scenario: Valid shopper signs in successfully
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "secret_sauce"
    Then the product catalog should be displayed

  @regression @LOGIN-002
  Scenario: Shopper enters an incorrect password
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password "wrong_password"
    Then the login error should contain "Username and password do not match"
    And the shopper should remain on the login page

  @regression @LOGIN-003
  Scenario: Locked shopper attempts to sign in
    Given the shopper is on the login page
    When the shopper signs in with username "locked_out_user" and password "secret_sauce"
    Then the login error should contain "this user has been locked out"
    And the shopper should remain on the login page

  @regression @LOGIN-004
  Scenario: Shopper leaves the username empty
    Given the shopper is on the login page
    When the shopper signs in with username "" and password "secret_sauce"
    Then the login error should contain "Username is required"
    And the shopper should remain on the login page

  @regression @LOGIN-005
  Scenario: Shopper leaves the password empty
    Given the shopper is on the login page
    When the shopper signs in with username "standard_user" and password ""
    Then the login error should contain "Password is required"
    And the shopper should remain on the login page

  @regression @LOGIN-006
  Scenario: shopper is on the login page
    Given the shopper is on the login page
    When the shopper signs in with username "" and password ""
    Then the login error should contain "Username is required"
    And the shopper should remain on the login page

  @regression @LOGIN-007
  Scenario: shopper is on the login page
    Given the shopper is on the login page
    When the shopper signs in with username "unknown_shopper" and password "secret_sauce"
    Then the login error should contain "Username and password do not match"
    And the shopper should remain on the login page