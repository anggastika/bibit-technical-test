@test
Feature: Login to stockbit

  Background: Go to homepage
    Given user go to homepage stockbit

  Scenario Outline: Failed login to stockbit
    When user click entry point login
    Then user input username "<username>" and password "<password>"
    Then user click button login
    And user check alert message "<alert>"
    Examples:
      | username              | password              | alert                 |  |
      | NOT_REGISTER_USERNAME | NOT_REGISTER_PASSWORD | ALERT_INVALID_ACCOUNT |  |
      | USERNAME              | NOT_VALID_PASSWORD    | ALERT_INVALID_ACCOUNT |  |

  Scenario: Success login to stockbit
    When user click entry point login
    Then user input username "USERNAME" and password "PASSWORD"
    Then user click button login
    Then user skip popup avatar
    Then user click profile
    And user check username "USERNAME"
