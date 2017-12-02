Feature: Tips can be searched for with keywords

  Scenario: Page will show books containing keyword when it exists
    Given the page "books" has been selected
    And a book by title "Pieni kirjanen" and author "Kirjailija" and description "Kirja" exists
    When keyword "kiRja" has been submitted
    Then the page will contain the message "Pieni kirjanen"

  Scenario: Page will not show books containing keyword when it does not exist
    Given the page "books" has been selected
    When keyword "aaaaaaaaaaaa" has been submitted
    Then the page will contain the message "Mitään ei löytynyt. Hae tyhjällä kentällä jos haluat nähdä kaikki vinkit."
