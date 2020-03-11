Feature: Movie Rate

  Background: Valid session and guest session created
    Given the user has a valid session and guest session created with its API Key

  Scenario: The user needs to rate a movie in TMDB
    Given the movie "330457" must be rated as "8" in TMDB
    When the user sends a request to add the movie rating
    Then the service returns a successful movie rating result

  Scenario: The user needs to delete a rate of a movie in TMDB
    Given the movie "330457" rating must be deleted from TMDB
    When the user sends a request to delete the rating
    Then the service returns a successful movie rating elimination