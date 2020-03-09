Feature: List

Background: Valid session created
    Given the user has a valid session created with its API Key

  Scenario: The user needs to create a new list in TMDB
    Given a new list needs to be created in TMDB
    When the user send a request to create the list
    Then the service responds with a success result
    And the response contains the new list

  Scenario: The user needs to add a new movie to the list
    And the user wants to add a new movie to the list in TMDB
    When the user send a request to add a movie
    Then the response contains a status code for the added movie

  Scenario: The user needs to ask for the list details of the created list
    Given the details of the list must be shown
    When the user send a request to get details of the list
    Then the response contains details of the list

  Scenario: The user needs to verify the items in the list
    Given the items contained in the list must be shown
    When the user send a request to get those items contained in list
    Then the response contains information of items in the list















