Feature: List


Background: Valid session created
    Given the user has a valid session created with its API Key


  Scenario: The user needs to create a new list in TMDB
    Given a new list needs to be created in TMDB
    When the user send a request to create the list
    Then the service responds with a success result
    And the response contains the new list






