Feature: A book can be viewed with the browser

  Scenario: One book is first added and then viewed
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the book "Booky book" is selected
    Then the page will contain "Booky book"

  Scenario: Books are seen separated in different lists
    Given the page "books" has been selected
    When a book titled "Art of War" by "Sun Tzu" has been added and marked read
    And title "Prince" and author "Machiavelli" are entered into correct fields
    Then a book titled "Art of War" is found on list "read"
    And a book titled "Prince" is found on list "notRead"
