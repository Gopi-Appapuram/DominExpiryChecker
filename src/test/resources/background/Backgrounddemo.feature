Feature: This is background demo

  Background: User is logged in to saucedemo app
    Given User is accessing the saucedemo app login page
    When User enters vald username and password
    Then user should be able to be navigate to home page

  Scenario: Test menu items
    And Click on brudcrumbs icon
    Then User should be able to see the menu itemms 

  Scenario: Verify add to  cart function
    And Click on Add to cart button
    Then the item should be added to cart
