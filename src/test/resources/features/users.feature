Feature: Users API
  As a API consumer
  I want to interact with the Users endpoint
  So that I can retrieve user data

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  Scenario: Get all users
    When I send a GET request to "/users"
    Then the response status code should be 200
    And the response should contain a list of users

  Scenario: Get a specific user
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain valid user details with id 1
    And the email should be in valid format