@calculator
Feature: Calculator

  Scenario: multiply
    Given open calcutor application
    When press "8" button
    And press "multiply" button
    And press "3" button
    And press "equals" button
    Then the result is "24"
    Then the result is not "66"
