Feature: Frame
  Scenario: General and Frame steps in English
    Given the page "http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled" is visited
    And go inside the "id=iframeResult" frame
    Then assert that the "name=fname" element is enabled
    And assert that the "name=lname" element is disabled
    And assert that the "xpath=//input[@type='submit']" element is present
    And assert that the "xpath=//input[@type='password']" element is absent
    When the page "http://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_hidden" is visited
    And go inside the "id=iframeResult" frame
    Then assert that the "value" attribute of "name=country" is "Norway"
    And assert that the "name=country" element is hidden
    When change "type" attribute of "name=country" into "display"
    Then assert that the "name=country" element is visible
    When change "value" attribute of "name=country" into "VIETNAM"
    Then assert that the "value" attribute of "name=country" is "VIETNAM"
    When highlight "name=country" element
    And set bound for "name=country" element
    And capture image of "name=country" element and save as "norway.png"

  Scenario: General and Frame steps in Vietnam
    Given mở trang có tên miền là "http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled"
    When truy cập vào khung "id=iframeResult"
    Then kiểm tra đối tượng "name=fname" có hiệu lực
    And kiểm tra đối tượng "name=lname" bị vô hiệu hóa
    And kiểm tra đối tượng "xpath=//input[@type='submit']" có tồn tại
    And kiểm tra đối tượng "xpath=//input[@type='password']" không tồn tại
	When mở trang có tên miền là "http://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_hidden"
	And truy cập vào khung "id=iframeResult"
	Then kiểm tra thuộc tính "value" của đối tượng "name=country" có giá trị là "Norway"
	And kiểm tra đối tượng "name=country" bị ẩn
	And thay đổi thuộc tính "type" của đối tượng "name=country" thành "display"
	Then kiểm tra đối tượng "name=country" nhìn thấy được
	When thay đổi thuộc tính "value" của đối tượng "name=country" thành "VIETNAM"
	Then kiểm tra thuộc tính "value" của đối tượng "name=country" có giá trị là "VIETNAM"
	When làm nổi bật đối tượng "name=country"
	And vẽ đường bao quanh đối tượng "name=country"
	And chụp hình đối tượng tại "name=country" và đặt tên ảnh là "vietnam.png"