Feature: Assertation

  Scenario: Assert result in English
    Given the page "https://cucumber.io/" is visited
    Then assert that the "Describe behaviour in plain text" text is present
    And assert that the "Write a step definition in Java" text is not present
    And assert that the page title is "Cucumber"
    And assert that the page title is not "Serenity BDD"
    And assert that the page title has "Cu"
    And assert that the page title does not have "Cucun"
    And assert that the url is "https://cucumber.io/"
	And assert that the url is not "https://cucumber.com/"

  Scenario: Assert result in Vietnam
    Given mở trang có tên miền là "https://cucumber.io/"
    Then kiểm tra trang này có chứa đoạn văn bản "Describe behaviour in plain text"
    And kiểm tra trang này không chứa đoạn văn bản "Write a step definition in C#"
    And kiểm tra tiêu đề trang là "Cucumber"
    And kiểm tra tiêu đề trang không là "Serenity BDD"
    And kiểm tra tiêu đề trang có chứa "Cu"
    And kiểm tra tiêu đề trang không chứa "Cucun"
    And kiểm tra tên miền của trang là "https://cucumber.io/"
    And kiểm tra tên miền của trang không là "https://cucumber.vn/"
