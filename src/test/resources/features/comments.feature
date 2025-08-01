Feature: Comments Management
  As an API consumer
  I want to interact with post comments
  So that I can retrieve and verify comment data

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  Scenario Outline: Retrieve comments for valid post ID
    When I get comments for post <postId>
    Then the response status code should be 200
    And the response should contain only comments for post <postId>

    Examples:
      | postId |
      | 1      |
      | 5      |
      | 10     |

  Scenario Outline: Retrieve single comment by ID
    When I get comment with id <id>
    Then the response status code should be <status>

    Examples:
      | id   | status |
      | 1    | 200    |
      | 9999 | 404    |
