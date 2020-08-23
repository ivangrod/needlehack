Feature: Create a new post
  In order to have post on the platform
  As a user with admin permissions
  I want to create a new post

  Scenario: A valid non existing post
    Given I create a post with title 'Ready for changes with Hexagonal Architecture' and uri 'https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749' and author 'Damir Svrtan' from Netflix
    Then the response status code should be 201