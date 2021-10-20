@test
Feature: Edit Profile Account

  Background: Go to profile page
    Given user go to homepage stockbit
    When user click entry point login
    Then user input username "USERNAME" and password "PASSWORD"
    Then user click button login
    Then user skip popup avatar
    Then user click profile
    And user check username "USERNAME"

  Scenario: Failed login to stockbit
    When user click entry point edit profile
    Then user click public profile
    Then user click edit button in my public profile page
    Then user edit biography profile "I'm QA Engineer"
    Then user choose location "DKI Jakarta"
    Then user click save button edit profile