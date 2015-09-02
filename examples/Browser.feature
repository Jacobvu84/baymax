@browser
Feature: Window

  Scenario: Demo in English version
    When the home page is visited
    And the page "http://live.guru99.com/" is visited
    And the page is refreshed
    And maximize the window
    And go backward one page
    And go forward one page
    And the page "https://cucumber.io/" is opened on another window
    And window dimension is changed with size (300,500)
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
    When mở trang chủ
    And mở trang có tên miền là "http://live.guru99.com/"
    And tải lại trang
    And nới rộng cửa sổ tối đa
    And đi đến trang sau
    And đi đến trang trước
    And mở trang có tên miền là "https://cucumber.io/" trên cửa sổ mới và chọn nó
    And cửa sổ được thay đổi với kích thước (400,400)
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
