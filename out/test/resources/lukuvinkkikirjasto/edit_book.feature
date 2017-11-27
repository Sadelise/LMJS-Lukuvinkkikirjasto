Feature: A book can be edited

  Scenario: a book can be edited
    Given a book has been added
    And command edit has been selected
    When user gives the book number 1
    And when user enters "ISBN"
    And when user gives the number 1234
    Then system will respond with "Changes saved"
