Feature: A Youtube video can be edited with the browser

    Scenario: One Youtube video is first added and then edited
    Given the page "books" has been selected
    When the tip type "YouTubeVideo" has been selected in the dropdown menu
    When title "Vidya video" and URL "http://testurl" are entered into the video-adding form
    And the book "Vidya video" is selected
    And the description "Desc" is entered
    And save is clicked
    Then the page will contain "Videon muokkaaminen onnistui!"