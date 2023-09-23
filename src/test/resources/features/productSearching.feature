Business Need: Searching
Feature: Search and place order for products

  @search
  Scenario Outline: Search Experience for product search in both homepage and offers page 1
    Given User is on GreenCart Landing page
    When User search with short name <name> and extract the actual name for the product with ok
    Then The product should exist on offers page when the user search for <name> with ok


    Examples:
    |name|
    |Tom |
    |Bean|


