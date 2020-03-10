Feature: Authentication

  Scenario: The user needs a valid guest session created with its API Key
    Given the user requests a guest session
    When the user sends the requests for a guest session
    Then the user creates a valid guest session id
    And the response contains a successful status

  Scenario: The user needs a valid session created with its API Key
    Given the user requests a session token
    When the user requests session with login
    Then the user creates a valid session
    And the session is deleted
