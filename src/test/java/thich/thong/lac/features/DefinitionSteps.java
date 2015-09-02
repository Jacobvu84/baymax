package thich.thong.lac.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.vi.Cho;
import cucumber.api.java.vi.Khi;
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
	
	@When("^wait for (\\d+) seconds$")
	public void wait_for_seconds(int time) throws Throwable {
	   Thread.sleep(time*1000);
	}

}
