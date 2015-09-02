package thich.thong.lac.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.vi.Cho;
import cucumber.api.java.vi.Khi;
import cucumber.api.java.vi.Thì;
import net.thucydides.core.annotations.Steps;
import thich.thong.lac.steps.BaymaxSteps;

public class DefinitionSteps {

    @Steps
    BaymaxSteps baymax;

    @Given("the home page is visited")
    @Cho("^mở trang chủ$")
    public void home_page_is_visited() {
        baymax.visit();
    }
  
    @Khi("^mở trang có tên miền là \"([^\"]*)\"$")
    @When("^the page \"([^\"]*)\" is visited$")
    public void open_the(String url) {
        baymax.open_the(url);
    }
    
    @Khi("^mở trang có tên miền là \"([^\"]*)\" trên cửa sổ mới và chọn nó$")
    @When("^the page \"([^\"]*)\" is opened on another window$")
    public void open_in_new_window(String url) {
    	baymax.open_in_new_window(url);
    }
   
    @Khi("^mở liên kết \"([^\"]*)\" trên cửa sổ mới và chọn nó$")
    @When("^open \"([^\"]*)\" link in new window and switch to it$")
    public void open_link_in_new_window(String linkText) {
    	baymax.open_link_in_new_window(linkText);
    }
    
    @Khi("^mở hộp thoại \"([^\"]*)\" và chọn nó$")
    @When("^open \"([^\"]*)\" dialog and switch to it$")
    public void open_dialog(String element) {
    	baymax.open_dialog(element);
    }
    
    @Khi("^quay lại cửa sổ ban đầu$")
    @When("^switch back to the original window$")
    public void switch_back_to_the_original_window() {
    	baymax.switch_back_to_the_original_window();
    }
    
    @Khi("^nới rộng cửa sổ tối đa$")
    @When("^maximize the window$")
    public void maximize_the_window() {
    	baymax.maximize_the_window();
    }
    
    @Khi("^đi đến trang trước$")
    @When("^go forward one page$")
    public void go_forward_one_page() {
    	baymax.go_forward_one_page();
    }

    @Khi("^đi đến trang sau$")
    @When("^go backward one page$")
    public void move_backward_one_page() {
    	baymax.go_backward_one_page();
    }

    @Khi("^tải lại trang$")
    @When("^the page is refreshed$")
    public void reload_the_page() {
    	baymax.refresh_page();
    }
    
    @Khi("^cửa sổ được di chuyển tới vị trí có tọa độ \\((\\d+),(\\d+)\\)$")
    @When("^window is moved to location with coordinates \\((\\d+),(\\d+)\\)$")
    public void move_window_to(int x, int y) {
    	baymax.move_the_window_to(x,y);
    }
    
    @Khi("^cửa sổ được thay đổi với kích thước \\((\\d+),(\\d+)\\)$")
    @When("^window dimension is changed with size \\((\\d+),(\\d+)\\)$")
    public void adjust_window_dimension(int width, int height) {
    	baymax.adjust_window_dimension(width,height);
    }
   
    @When("^close current window$")
    @Khi("^đóng cửa sổ hiện tại$")
    public void close_current_window() {
 	baymax.close_current_window();
    }
	
    @Khi("^cuộn màn hình lên hoặc xuống với giá trị \\((\\d+),(\\d+)\\)$")
    @When("^scroll up or down in screen with value \\((\\d+),(\\d+)\\)$")
    public void scroll_screen_to(int x, int y) {
    	baymax.scroll_screen_to(x,y);
    }
	
    @Khi("^dừng hình trong (\\d+) giây$")
    @When("^wait for (\\d+) seconds$")
    public void wait_for_seconds(int time) throws Throwable {
    	Thread.sleep(time*1000);
    }
    
    @Thì("^kiểm tra trang này có chứa đoạn văn bản \"([^\"]*)\"$")
    @When("^assert that the \"([^\"]*)\" text is present$")
    public void the_text_is_present(String textValue) {
    	baymax.the_text_is_present(textValue);
    }

    @Thì("^kiểm tra trang này không chứa đoạn văn bản \"([^\"]*)\"$")
    @When("^assert that the \"([^\"]*)\" text is not present$")
    public void the_text_is_not_present(String textValue) throws Throwable {
    	baymax.the_text_is_not_present(textValue);
    }
    
    @Thì("^kiểm tra tiêu đề trang là \"([^\"]*)\"$")
    @Then("^assert that the page title is \"([^\"]*)\"$")
    public void page_title_is(String pageTitle) {
    	baymax.page_title_is(pageTitle);
    }

    @Thì("^kiểm tra tiêu đề trang không là \"([^\"]*)\"$")
    @Then("^assert that the page title is not \"([^\"]*)\"$")
    public void page_title_is_not(String pageTitle) {
    	baymax.page_title_not_is(pageTitle);
    }

    @Thì("^kiểm tra tiêu đề trang có chứa \"([^\"]*)\"$")
    @Then("^assert that the page title has \"([^\"]*)\"$")
    public void assert_that_the_page_title_has(String pageSubTitle) {
    	baymax.page_title_has(pageSubTitle);
    }

    @Thì("^kiểm tra tiêu đề trang không chứa \"([^\"]*)\"$")
    @Then("^assert that the page title does not have \"([^\"]*)\"$")
    public void assert_that_the_page_title_does_not_have(String pageSubTitle){
    	baymax.page_title_not_have(pageSubTitle);
    }
    
    @Thì("^kiểm tra tên miền của trang là \"([^\"]*)\"$")
    @Then("^assert that the url is \"([^\"]*)\"$")
    public void the_url_is(String domain){
    	baymax.is_url(domain);
    }

    @Thì("^kiểm tra tên miền của trang không là \"([^\"]*)\"$")
    @Then("^assert that the url is not \"([^\"]*)\"$")
    public void the_url_is_not(String domain){
    	baymax.is_not_url(domain);
    }
    
	protected static String actualImage = System.getProperty("user.dir") + "\\images\\";
	protected static String tmpImage = System.getProperty("user.dir") + "\\target\\";
    
	@Khi("^chụp hình đối tượng tại \"([^\"]*)\" và đặt tên ảnh là \"([^\"]*)\"$")
	@When("^capture image of \"([^\"]*)\" element and save as \"([^\"]*)\"$")
	public void capture_image_of_element(String element, String imgName){
		String pathStorge = actualImage + imgName;
		baymax.capture_and_save_it(element, pathStorge);
	}
	
	@Khi("^kiểm tra hình ảnh của đối tượng tại \"([^\"]*)\" giống với hình ảnh \"([^\"]*)\"$")
	@Then("^assert that image of \"([^\"]*)\" element and expected \"([^\"]*)\" image is similar$")
	public void actual_image_and_expected_image_should_be_similar(String element, String imgName) {
		String pathStorge = tmpImage + imgName;
		baymax.capture_and_save_it(element,pathStorge);
		
		String actual = actualImage + imgName;
		
		baymax.actual_image_and_expected_image_should_be_similar(pathStorge, actual);
	}
}
