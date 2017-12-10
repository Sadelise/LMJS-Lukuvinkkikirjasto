Feature: A book can be added with the browser

  Scenario: One book is added
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" are entered into correct fields
    Then the page will contain "Booky"

  Scenario: One book is added without the author
    Given the page "books" has been selected
    When only title "Booky book" is entered into the correct field
    Then the page will contain "Kirjan lisääminen epäonnistui. Nimi ja tekijä ovat pakollisia kenttiä."

  Scenario: One book is added without the title
    Given the page "books" has been selected
    When only author "Pekkanen, Paavo" is entered into the correct field
    Then the page will contain "Kirjan lisääminen epäonnistui. Nimi ja tekijä ovat pakollisia kenttiä."

  Scenario: One book is added with all attributes
    Given the page "books" has been selected
    When title "Booky book" and author "Pekkanen, Paavo" and description "good" and ISBN "1234" and reference "Recommender" are entered into correct fields
    And the book "Booky book" is selected    
    Then the page will contain "Booky"
    And the page will contain "good"
    And the page will contain "1234"
    And the page will contain "Recommender"