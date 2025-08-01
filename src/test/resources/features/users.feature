Feature: Users Management
  As an API consumer
  I want to interact with user data
  So that I can retrieve user information

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  Scenario Outline: Retrieve user by valid ID
    When I get user with id <id>
    Then the response status code should be 200
    And the response should contain valid user details
    And the user email should be valid

    Examples:
      | id |
      | 1  |
      | 3  |
      | 5  |

  Scenario Outline: Verify invalid user requests
    When I get user with id <id>
    Then the response status code should be <status>

    Examples:
      | id   | status |
      | 11   | 404    |
      | -1   | 404    |
