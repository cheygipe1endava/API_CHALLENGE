Feature: TV Shows Rate

  Background: Valid session and guest session created
    Given the user has a valid session and guest session created

  Scenario: The user needs to rate a TV show in TMDB
    Given the TV show "39852" must be rated "8" in TMDB
    When the user sends a request to add the TV show rating
    Then the service returns a successful TV show rating result

  Scenario: The user needs to delete a rate of a TV show in TMDB
    Given the TV show "39852" rating must be deleted from TMDB
    When the user sends a request to delete the TV show rating
    Then the service returns a successful TV show rating elimination