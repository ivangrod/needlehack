Feature: Create a new post
  In order to have post on the platform
  As a user with admin permissions
  I want to create a new post

  Scenario: A valid non existing post
    Given I create a post with title 'Improving our video encodes for legacy devices' and uri 'https://netflixtechblog.com/improving-our-video-encodes-for-legacy-devices-2b6b56eec5c9' and author 'Greg Burrell' from Netflix
#   Then the response status code should be 201