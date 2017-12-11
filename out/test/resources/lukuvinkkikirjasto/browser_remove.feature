Feature: A book can be removed with the browser

    Scenario: One book is first added and then removed
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the delete button is clicked
    And the confirmation prompt is accepted
    Then the page will contain "Vinkin poistaminen onnistui!"

    Scenario: One book is first added and removal is interrupted
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    And the delete button is clicked
    And the confirmation prompt is not accepted
    Then the page will contain "Vinkkiä ei poistettu!"
    And the page will contain "Booky book"