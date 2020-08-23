Feature: Search posts
  In order to be able to view posts on the platform
  As a user of the platform
  I want to search existing posts

  Scenario: A valid existing post
    Given I have an existing post with title 'Improving our video encodes for legacy devices' and uri 'https://netflixtechblog.com/improving-our-video-encodes-for-legacy-devices-2b6b56eec5c9' and author 'Greg Burrell' from Netflix
    # TODO:   Review key-value parameter for the search endpoint
    When I search a post containing 'legacy'
    Then the response status code should be 200
    # TODO:   Multiple results. Define a deterministic test
    And the response body should contain 'Improving our video encodes for legacy devices'