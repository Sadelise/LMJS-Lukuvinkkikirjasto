Feature: Tip tags can be used to make searches

  Scenario: Search with correct keyword is performed when a tag is clicked
    Given the page "books" has been selected
    And a book by title "Pieni kirjanen" and author "Kirjailija" and tags "Runous; Kokoelma" exists
    When tag "Kokoelma" is clicked
    Then the page will contain the message "Pieni kirjanen"

  Scenario: Only the correct part of the tag string is searched for
    Given the page "books" has been selected
    And a book by title "Booky book" and author "Pekkanen, Paavo" and tags "Runous;" exists
    And a book by title "Pieni kirjanen" and author "Kirjailija" and tags "Runous; Kokoelma" exists
    When tag "Kokoelma" is clicked
    Then the page will not contain the message "Booky book"
