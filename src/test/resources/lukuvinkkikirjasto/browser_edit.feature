Feature: A book can be edited with the browser

    Scenario: One book is first added and then edited
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the book "Booky book" is selected
    And the description "Desc" is entered
    Then the page will contain "Kirjan muokkaaminen onnistui!"