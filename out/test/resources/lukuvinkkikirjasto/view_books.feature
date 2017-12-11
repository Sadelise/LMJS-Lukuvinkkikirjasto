Feature: Added books can be viewed

  Scenario: Two added books can be viewed with 'view' command
    Given a book titled "Art of War" by "Sun Tzu" has been added
    And a book titled "Prince" by "Machiavelli" has been added
    When command view has been selected
    Then system response will contain "Art of War"
    And system response will contain "Prince"
