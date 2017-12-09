Feature: A book can be edited with the browser

    Scenario: One book is first added and then edited
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the book "Booky book" is selected
    And the description "Desc" is entered
    And the tag "Tag" is entered
    And the reference "Recommender" is entered
    And save is clicked
    Then the page will contain "Kirjan muokkaaminen onnistui!"
    And the page will contain "Tag"
    And the page will contain "Recommender"