Feature: General Steps

  Scenario: Element attribute in English
    Given the page "http://live.guru99.com/" is visited
    Then assert that the "alt" attribute of "xpath=//div[2]/a/img" is "Additional Options"
    Then assert that the "title" attribute of "xpath=//div[2]/a/img" is "Additional Options"
    Then assert that the "src" attribute of "xpath=//div[2]/a/img" is "https://www.paypalobjects.com/en_US/i/bnr/bnr_nowAccepting_150x60.gif"
    Then assert that the "src" attribute of "xpath=//div[2]/a/img" has "bnr_nowAccepting_150x60.gif"
    Then assert that the "src" attribute of "xpath=//div[2]/a/img" does not have "bnr_nowAccepting.gif"
    Then assert that the "src" attribute of "xpath=//div[2]/a/img" is not "https://www.paypalobjects.com"

  Scenario: Element attribute in Vietname
    Given mở trang có tên miền là "http://live.guru99.com/"
    Then kiểm tra thuộc tính "alt" của đối tượng "xpath=//div[2]/a/img" có giá trị là "Additional Options"
    And kiểm tra thuộc tính "title" của đối tượng "xpath=//div[2]/a/img" không có giá trị là "Additional Option"
    And kiểm tra thuộc tính "src" của đối tượng "xpath=//div[2]/a/img" có chứa giá trị là "https://www.paypalobjects.com"
    And kiểm tra thuộc tính "src" của đối tượng "xpath=//div[2]/a/img" không chứa giá trị là "bnr_nowAccepting.gif"