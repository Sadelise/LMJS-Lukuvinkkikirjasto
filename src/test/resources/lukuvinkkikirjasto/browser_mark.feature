Feature: A book can be marked read with the browser

  Scenario: One book is first added and then marked read
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the book "Booky book" is selected
    And the book is marked read
    And save is clicked
    And the book "Booky book" is selected
    Then the page will contain "true"
