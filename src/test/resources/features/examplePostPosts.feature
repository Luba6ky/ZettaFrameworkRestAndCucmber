Feature: API Testing for POST Posts

  Scenario: Create a new post
    Given I have the POST endpoint for posts
    And I have the post data
    When I send a POST request to the endpoint with the post data
    Then the response status code for POST should be 201
    And the response should contain the post data
    And the post data should be saved correctly in the database
