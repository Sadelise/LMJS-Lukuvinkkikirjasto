Feature: A Youtube Video can be added with the browser

  Scenario: One youtube video is added
    Given the page "books" has been selected
    When the tip type "YouTubeVideo" has been selected in the dropdown menu
    When title "Vidya video" and URL "https://www.youtube.com/watch?testurl" are entered into the video-adding form
    Then the page will contain "Vidya"

  Scenario: Youtube video is added without URL
    Given the page "books" has been selected
    When the tip type "YouTubeVideo" has been selected in the dropdown menu
    When title "Vidya video" and URL "" are entered into the video-adding form
    Then the page will contain "Videon lisääminen epäonnistui. Nimi ja linkki ovat pakollisia kenttiä."

  Scenario: Youtube video is added with invalid URL
    Given the page "books" has been selected
    When the tip type "YouTubeVideo" has been selected in the dropdown menu
    When title "Vidya video" and URL "http://test.com" are entered into the video-adding form
    Then the page will contain "Videon lisääminen epäonnistui. Linkki ei kelvollinen."

  Scenario: Youtube video is added without title
    Given the page "books" has been selected
    When the tip type "YouTubeVideo" has been selected in the dropdown menu
    When title "" and URL "https://www.youtube.com/watch?testurl" are entered into the video-adding form
    Then the page will contain "Videon lisääminen epäonnistui. Nimi ja linkki ovat pakollisia kenttiä."
