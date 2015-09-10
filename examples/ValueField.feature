@field
Feature: Hello!
  Scenario: Value of field
    Given the page "http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_value" is visited
    When go inside the "id=iframeResult" frame
    Then assert that the value of "name=fname" is "John"
    And assert that the value of "name=fname" is not "Doe"
    And assert that the value of "name=fname" has "Jo"
    And assert that the value of "name=fname" does not have "De"
