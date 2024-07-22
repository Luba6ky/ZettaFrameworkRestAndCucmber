Feature: Update a post

  Scenario: Update an existing post
    Given I have the PUT endpoint for posts
    Given I update the post with id 1 with title "foo" and body "bar" for user 1
    Then the post should be updated with id 1 and title "foo" and body "bar" for user 1
