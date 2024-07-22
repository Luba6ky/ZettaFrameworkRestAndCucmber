Feature: API Testing for GET Posts

  Scenario: Get all posts
    Given I have the GET endpoint for posts
    When I send a GET request to the endpoint
    Then the response status code for GET should be 200
    And the response should contain posts
