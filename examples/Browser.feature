@browser
Feature: Window

  Scenario: Demo in English version
    Given the home page is visited
    And the page "http://live.guru99.com/" is visited
    And the page is refreshed
    And maximize the window
    And go backward one page
    And go forward one page
    And the page "https://cucumber.io/" is opened on another window
    Then assert that the "Describe behaviour in plain text" text is present
    And assert that the "Write a step definition in Java" text is not present
    And assert that the page title is "Cucumber"
    And assert that the page title is not "Serenity BDD"
    And assert that the page title has "Cu"
    And assert that the page title does not have "Cucun"
    And assert that the url is "https://cucumber.io/"
	And assert that the url is not "https://cucumber.com/"
    When window dimension is changed with size (300,500)
    And scroll up or down in screen with value (0,1000)
    And window is moved to location with coordinates (200,300)
    And close current window
    And switch back to the original window
    And open "link=TV" link in new window and switch to it
    And close current window
    And switch back to the original window
    And open "css=img[alt='Additional Options']" dialog and switch to it
    And window is moved to location with coordinates (150,200)
    And maximize the window
    And close current window
    And switch back to the original window
    And window dimension is changed with size (300,500)
    And window is moved to location with coordinates (100,200)
    

  Scenario: Demo in Vietnam version
    Given mở trang chủ
    And mở trang có tên miền là "http://live.guru99.com/"
    And tải lại trang
    And nới rộng cửa sổ tối đa
    And đi đến trang sau
    And đi đến trang trước
    And mở trang có tên miền là "https://cucumber.io/" trên cửa sổ mới và chọn nó
    Then kiểm tra trang này có chứa đoạn văn bản "Describe behaviour in plain text"
    And kiểm tra trang này không chứa đoạn văn bản "Write a step definition in C#"
    And kiểm tra tiêu đề trang là "Cucumber"
    And kiểm tra tiêu đề trang không là "Serenity BDD"
    And kiểm tra tiêu đề trang có chứa "Cu"
    And kiểm tra tiêu đề trang không chứa "Cucun"
    And kiểm tra tên miền của trang là "https://cucumber.io/"
    And kiểm tra tên miền của trang không là "https://cucumber.vn/"
    When cửa sổ được thay đổi với kích thước (400,400)
    And cuộn màn hình lên hoặc xuống với giá trị (0,700)
    And cửa sổ được di chuyển tới vị trí có tọa độ (200,250)
    And đóng cửa sổ hiện tại
    And quay lại cửa sổ ban đầu
    And mở liên kết "link=TV" trên cửa sổ mới và chọn nó
    And đóng cửa sổ hiện tại
    And quay lại cửa sổ ban đầu
    And mở hộp thoại "css=img[alt='Additional Options']" và chọn nó
    And window is moved to location with coordinates (150,180)
    And nới rộng cửa sổ tối đa
    And đóng cửa sổ hiện tại
    And quay lại cửa sổ ban đầu
    And cửa sổ được thay đổi với kích thước (400,400)
    And cửa sổ được di chuyển tới vị trí có tọa độ (200,240)
