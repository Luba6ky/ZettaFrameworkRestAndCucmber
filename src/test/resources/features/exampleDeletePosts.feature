Feature: Delete a post

  Scenario: Delete an existing post
    Given I have the DELETE endpoint for posts
    Given I delete the post with id 1
    Then the post should be deleted successfully
