Feature: A book can be removed with the browser

    Scenario: One book is first added and then removed
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the delete button is clicked
    Then the page will contain "Kirjan poistaminen onnistui!"