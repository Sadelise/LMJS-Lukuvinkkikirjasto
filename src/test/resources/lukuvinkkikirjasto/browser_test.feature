Feature: A book can be viewed

    Scenario: Page can be viewed
    Given the page "books" has been selected
    Then the page will contain "Lukuvinkit"

    Scenario: A book can be added
      Given the page "books" has been selected
      When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
      Then the page will contain "Booky book"

