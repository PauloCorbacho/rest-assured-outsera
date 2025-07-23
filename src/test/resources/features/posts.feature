Feature: Posts API
  As a API consumer
  I want to interact with the Posts endpoint
  So that I can manage post data

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  Scenario: Get all posts
    When I send a GET request to "/posts"
    Then the response status code should be 200
    And the response should contain a list of posts

  Scenario: Get a specific post
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And the response should contain post details with id 1

  Scenario: Try to get a non-existent post
    When I send a GET request to "/posts/9999"
    Then the response status code should be 404

  Scenario: Create a new post
    Given I have the following post data:
      | userId | 1       |
      | title  | Test Post Title |
      | body   | This is the body of the test post. |
    When I send a POST request to "/posts" with the given data
    Then the response status code should be 201
    And the response should contain the created post data

  Scenario: Update a post
    Given I have the following updated post data:
      | id     | 1       |
      | userId | 1       |
      | title  | Updated Post Title |
      | body   | This is the updated body of the post. |
    When I send a PUT request to "/posts/1" with the given data
    Then the response status code should be 200
    And the response should contain the updated post data

  Scenario: Delete a post
    When I send a DELETE request to "/posts/1"
    Then the response status code should be 200