Feature: Comments API
  As a API consumer
  I want to interact with the Comments endpoint
  So that I can retrieve comment data

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  Scenario: Get comments for a post
    When I send a GET request to "/comments" with parameter "postId" equal to "1"
    Then the response status code should be 200
    And the response should contain only comments for post with id 1

  Scenario: Get a specific comment
    When I send a GET request to "/comments/1"
    Then the response status code should be 200
    And the response should contain comment details with id 1