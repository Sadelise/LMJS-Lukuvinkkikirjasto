Feature: A book can be viewed

    Scenario: Page can be viewed
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    Then the page will contain "Prince"