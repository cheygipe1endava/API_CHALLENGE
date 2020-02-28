Feature: List


Background: Valid session created
  Given The user has a valid session created with its API Key


  Scenario: The user needs to create a new list in TMDB
    Given A new list needs to be created in TMDB
    When The user send a request to create the list
    Then The service responds with a success result
    And The response contains the new list

  Scenario: The user needs to add a new movie to list
    Given A new movie needs to be added to the list in TMDB
    When The user send a request to add a movie
    Then Response contains status code
    And Movie was successfully added

  Scenario: The user needs to ask for list details to list
    Given Details of the list must be shown
    When The user send a request to get details of the list
    Then Response contains details of the list

  Scenario: The user needs to ask for list details to list
    Given Details of the list must be shown
    When The user send a request to get details of the list
    Then Response contains JSON object for details of the list

  Scenario: The user needs to verify the items in the list
    Given Items contained in list must be shown
    When The user send a request to get items contained in list
    Then Response contains JSON object for items in the list

  Scenario: The user needs to remove movies in the list
    Given Movies in the list must be removed
    When The user send a request to remove movies contained in list
    Then Response show successful remove of movies

  Scenario: The user needs to clear records of the list
    Given All records in the list must be cleared
    When The user send a request to clear the list
    Then Response show successful clear of the list

  Scenario: The user needs to delete the list
    Given List must be erased from TMDb
    When The user send a request to delete the list
    Then Response show successful delete of the list




