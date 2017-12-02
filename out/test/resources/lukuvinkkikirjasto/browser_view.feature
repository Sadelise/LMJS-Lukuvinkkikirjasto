Feature: A book can be viewed with the browser

    Scenario: One book is first added and then viewed
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the book "Booky book" is selected
    Then the page will contain "Booky book"