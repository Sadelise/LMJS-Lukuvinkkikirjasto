Feature: A book can be added with the browser

    Scenario: One book is added
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    Then the page will contain "Booky"