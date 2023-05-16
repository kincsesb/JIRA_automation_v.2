Feature: Login feature
  As a registered user,
  I want to be able to log in into my account,
  So I can access my personal data.

  Scenario: Successful log in with valid username and password
    Given the user has valid credentials with username and password
    When the user enters the username and password
    And clicks the log in button
    And clicks the avatar icon
    And chooses the profile option
    Then the displayed username should be the same as the one used for log in