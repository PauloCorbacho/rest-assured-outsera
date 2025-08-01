Feature: Posts Management
  As an API consumer
  I want to interact with blog posts
  So that I can retrieve and manage post data

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  Scenario Outline: Retrieve posts by valid user ID
    When I get posts for user <userId>
    Then the response status code should be 200
    And the response should contain only posts from user <userId>

    Examples:
      | userId |
      | 1      |
      | 3      |
      | 7      |

  Scenario Outline: Retrieve single post by ID
    When I get post with id <id>
    Then the response status code should be <status>

    Examples:
      | id   | status |
      | 1    | 200    |
      | 9999 | 404    |

  Scenario Outline: Create post with dynamic data
    Given I prepare a new post for user <userId>
    When I create the post
    Then the response status code should be 201
    And the response should match the request data

    Examples:
      | userId |
      | 1      |
      | 5      |