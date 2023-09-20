Feature: User want to login to the application
  @netBanking
  Scenario: The user can login with valid credentials - net
    When the user open login page
    Given User login into the application
    Then Homepage is displayed
    And Cards are displayed
  @vodBanking
  Scenario: The user can login with valid credentials - vod
    When the user open login page
    Given User login into the application
    Then Homepage is displayed
    And Cards are displayed

  Scenario: The user can login with valid credentials
    When the user open login page
    Given User login into the application
    Then Homepage is displayed
    And Cards are displayed