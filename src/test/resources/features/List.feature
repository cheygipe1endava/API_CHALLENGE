Feature: List

Background: Valid session created
    Given the user has a valid session created with its API Key

  Scenario: The user needs to create a new list in TMDB
    Given a new list needs to be created in TMDB
    When the user sends a request to create the list
    Then the service responds with a success result
    And the response contains the new list
    And the list created is erased
    And the session is erased

  Scenario: The user needs to add a new movie to the list
    Given a new list must be created in TMDB
    And the user wants to add a new movie "330457" to the list in TMDB
    When the user sends a request to add a movie
    Then the response contains a status code for the added movie
    And the list created is erased
    And the session is erased

  Scenario: The user needs to ask for the list details of the created list
    Given the details of the list "134219" must be shown
    When the user sends a request to get details of the list
    Then the response contains details of the list
    And the session is erased

  Scenario: The user needs to verify the items in the list
    Given the movie "330457" contained in the list "134219" must be shown
    When the user sends a request to get those items contained in list
    Then the response contains information of items in the list
    And the session is erased

  Scenario: The user needs to remove movies in the list
    Given a new list must be created in TMDB
    And a movie must be inserted in the list
    And movies in the list must be removed
    When the user sends a request to remove movies contained in list
    Then the response shows successful remove action of the movie
    And the list created is erased
    And the session is erased

  Scenario: The user needs to clear records of the list
    Given a new list must be created in TMDB
    And a movie must be inserted in the list
    And all records in the list must be cleared
    When the user sends a request to clear the list
    Then the response shows successful clear of the list
    And the list created is erased
    And the session is erased

  Scenario: The user needs to delete the list
    Given a new list must be created in TMDB
    And the created list must be erased from TMDb
    When the user sends a request to delete the list
    Then the response shows successful delete of the list
    And the session is erased














