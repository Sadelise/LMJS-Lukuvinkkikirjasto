Feature: Books are separated in two lists by viewing status

  Scenario: Books are seen separated in different lists
    Given the page "books" has been selected
    When a book titled "Art of War" by "Sun Tzu" has been added and marked read
    And title "Prince" and author "Machiavelli" are entered into correct fields
    Then a book titled "Art of War" is found on list "read"
    And a book titled "Prince" is found on list "notRead"
