Feature: User want to login to the application
  Scenario: The user can login with valid credentials
    When the user open login page
    Given User login into the application
    Then Homepage is displayed
    And Cards are displayed