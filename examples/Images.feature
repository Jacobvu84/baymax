Feature: Images

  Scenario: Capture in English
   Given the page "https://cucumber.io/" is visited
   When capture image of "css=img.img-responsive" element and save as "Step3.png"
   Then assert that image of "css=img.img-responsive" element and expected "Step3.png" image is similar
   
  Scenario: Capture in Vietname
    Given mở trang có tên miền là "https://cucumber.io/"
    When chụp hình đối tượng tại "xpath=//img[@alt='Feature']" và đặt tên ảnh là "vietnam3.png"
    Then kiểm tra hình ảnh của đối tượng tại "xpath=//img[@alt='Feature']" giống với hình ảnh "vietnam3.png"