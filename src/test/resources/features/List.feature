Feature: List

Background: Valid session created
    Given the user has a valid session created with its API Key

  Scenario: The user needs to create a new list in TMDB
    Given a new list needs to be created in TMDB
    When the user send a request to create the list
    Then the service responds with a success result
    And the response contains the new list

  Scenario: The user needs to add a new movie to the list
    Given the user wants to add a new movie to the list in TMDB
    When the user send a request to add a movie
    Then the response contains status code
    And the movie was successfully added

  Scenario: The user needs to ask for the list details of the created list
    Given the details of the list must be shown
    When the user send a request to get details of the list
    Then the response contains details of the list














