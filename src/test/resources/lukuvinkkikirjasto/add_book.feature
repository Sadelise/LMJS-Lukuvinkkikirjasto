Feature: A new book can be added to the tips

  Scenario: a new book with title and author is added to the list
    Given command add is selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered
    Then system will respond with "Book added!"
