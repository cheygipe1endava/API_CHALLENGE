Feature: TV Episodes Rate

  Background: Valid session and guest session created
    Given the user has a valid session and guest session created with API KEY

  Scenario: The user needs to rate a TV episode in TMDB
    Given a TV episode must be rated in TMDB
    When the user sends a request to add the TV episode rating
    Then the service returns a successful TV episode rating result

  Scenario: The user needs to delete a rate of a TV episode in TMDB
    Given a TV episode rating must be deleted from TMDB
    When the user sends a request to delete the TV episode rating
    Then the service returns a successful TV episode rating elimination