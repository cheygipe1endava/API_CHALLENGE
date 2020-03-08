Feature: Authentication


  Scenario: The user needs a valid session created with its API Key
    Given the user requests a session token
    When the user requests session with login
    Then the user creates a valid session