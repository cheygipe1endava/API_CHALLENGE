Feature: Authentication
  As a user
  I want to create, modify and delete employees
  So that I can manage the existing employees

  Scenario: Create employee
    Given A new employee needs to be created
    When The user send a request to create the employee
    Then The service responds with a status code "200"


  Scenario: Retrieve an employee
    Given The user have the employee data
      | id     | first_name | last_name |
      | 500000 | Daniel     | Rodriguez |
    When The user send a request to get the employee by its id
    Then The service responds with a status code "200"
    And The response contains the employee


  Scenario: Delete an employee
    Given The user have the employee data
      | id     | first_name | last_name |
      | 500000 | Daniel     | Rodriguez |
    When The user send a request to delete the employee by its id
    Then The service responds with a status code "200"
    And The response deletes the employee