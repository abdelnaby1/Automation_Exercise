Business Need: Searching
Feature: Search and place order for products

  @search
  Scenario: Search Experience for product search in both homepage and offers page 1
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom"

  @search
  Scenario: Search Experience for product search in both homepage and offers page 2
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" second

  @search
  Scenario: Search Experience for product search in both homepage and offers page 3
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" third

  @search
  Scenario: Search Experience for product search in both homepage and offers page 4
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" fourth

  @search
  Scenario: Search Experience for product search in both homepage and offers page 5
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" fifth

  @search
  Scenario: Search Experience for product search in both homepage and offers page 6
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" sixth

  @search
  Scenario: Search Experience for product search in both homepage and offers page 7
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" seventh

  @search
  Scenario: Search Experience for product search in both homepage and offers page 8
    Given User is on GreenCart Landing page
    When User search with short name "Tom" and extract the actual name for the product
    Then The product should exist on offers page when the user search for "Tom" eighth