Feature: Upload document

  Scenario: Upload file in English
    Given the page "https://blueimp.github.io/jQuery-File-Upload/angularjs.html" is visited
    When "name=files[]" element is clicked
    And "C:\drag_and_drop_helper.js" file is uploaded
    And wait for any "drag_and_drop_helper" text to be present
    Then assert that the "drag_and_drop_helper.js" text is present
    When close current window

  Scenario: Upload file in VN
    Given mở trang có tên miền là "https://blueimp.github.io/jQuery-File-Upload/angularjs.html"
    When nhấn chuột trái vào đối tượng "name=files[]"
    And tải lên tài liệu "C:\drag_and_drop_helper.js"
    And chờ văn bản "drag_and_drop_helper" xuất hiện
    Then kiểm tra trang này có chứa đoạn văn bản "drag_and_drop_helper.js"