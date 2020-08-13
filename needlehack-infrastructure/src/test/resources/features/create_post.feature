Feature: Create a new post
  In order to have post on the platform
  As a user with admin permissions
  I want to create a new post

  Scenario: A valid non existing post
    Given I create a post with title 'New Title' and uri 'New Uri'
#    Then the response status code should be 201