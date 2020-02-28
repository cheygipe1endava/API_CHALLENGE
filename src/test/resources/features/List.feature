Feature: List


Background: Valid session created
  Given The user has a valid session created with its API Key


  Scenario: The user needs to create a new list in TMDB
    Given A new list needs to be created in TMDB
    When The user send a request to create the list
    Then The service responds with a success result
    And The response contains the new list
