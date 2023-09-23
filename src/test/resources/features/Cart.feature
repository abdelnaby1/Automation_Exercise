Business Need: Checkout
Feature: Search and place order for that product

  @checkout
  Scenario Outline: Search Experience for product search in homepage and do checkout
    Given User is on GreenCart Landing page
    When User search with short name <name> and extract the actual name for the product with ok
    And User add <qty> items of product with shortname <name> to the cart and proceeds to checkout
    Then User validate the <name> in the checkout page
    And User able to place the order

Examples:
    |name| qty|
    |Tom | 3  |
    |Beet| 1  |
