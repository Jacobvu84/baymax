Feature: General Steps

  Scenario: Element text in English
   Given the page "https://cucumber.io/" is visited
   Then assert that the text "css=h1[title='Cucumber']" element is "Simple, human collaboration"
   And assert that the text "css=h1[title='Cucumber']" element is not "human collaboration"
   And assert that the text "css=h1[title='Cucumber']" element has "collaboration"
   And assert that the text "css=h1[title='Cucumber']" element does not have "Simples"
   
  Scenario: Element text in Vietname
   Given mở trang có tên miền là "https://cucumber.io/"
   Then kiểm tra văn bản của đối tượng "css=h1[title='Cucumber']" là "Simple, human collaboration"
   And kiểm tra văn bản của đối tượng "css=h1[title='Cucumber']" không là "human collaboration"
   And kiểm tra văn bản của đối tượng "css=h1[title='Cucumber']" có chứa "collaboration"
   And kiểm tra văn bản của đối tượng "css=h1[title='Cucumber']" không chứa "Simples"