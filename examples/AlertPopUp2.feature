@popup2
# do NOT use
Feature: 2 - Behavior on Dialogs and Alerts

  Scenario: Answer the prompt box
    Given the page "http://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt" is visited
    When go inside the "id=iframeResult" frame
    And "css=button" element is clicked
    And input "Baymax" on prompt dialog box
    Then assert that the text "id=demo" element is "Hello Baymax! How are you today?"

  Scenario: Dimiss the confirm box
    Given the page "http://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm" is visited
    When go inside the "id=iframeResult" frame
    And "css=button" element is clicked
    And choose CANCEL on confirm dialog box
    Then assert that the text "id=demo" element is "You pressed Cancel!"

  Scenario: Accept the confirm box
    Given the page "http://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm" is visited
    When go inside the "id=iframeResult" frame
    And "css=button" element is clicked
    And choose OK on confirm dialog box
    Then assert that the text "id=demo" element is "You pressed OK!"

  Scenario: Verify text on the popup
    Given the page "http://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm" is visited
    And go inside the "id=iframeResult" frame
    And "css=button" element is clicked
    Then assert that text on popup box is "Press a button!"
