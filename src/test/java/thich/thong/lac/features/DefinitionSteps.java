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
		baymax.move_the_window_to(x, y);
	}

	@Khi("^cửa sổ được thay đổi với kích thước \\((\\d+),(\\d+)\\)$")
	@When("^window dimension is changed with size \\((\\d+),(\\d+)\\)$")
	public void adjust_window_dimension(int width, int height) {
		baymax.adjust_window_dimension(width, height);
	}

	@When("^close current window$")
	@Khi("^đóng cửa sổ hiện tại$")
	public void close_current_window() {
		baymax.close_current_window();
	}

	@Khi("^cuộn màn hình lên hoặc xuống với giá trị \\((\\d+),(\\d+)\\)$")
	@When("^scroll up or down in screen with value \\((\\d+),(\\d+)\\)$")
	public void scroll_screen_to(int x, int y) {
		baymax.scroll_screen_to(x, y);
	}

	@Khi("^dừng hình trong (\\d+) giây$")
	@When("^wait for (\\d+) seconds$")
	public void wait_for_seconds(int time) throws Throwable {
		Thread.sleep(time * 1000);
	}

	@Thì("^kiểm tra trang này có chứa đoạn văn bản \"([^\"]*)\"$")
	@When("^assert that the \"([^\"]*)\" text is present$")
	public void the_text_is_present(String textValue) {
		baymax.the_text_is_present(textValue);
	}

	@Thì("^kiểm tra trang này không chứa đoạn văn bản \"([^\"]*)\"$")
	@When("^assert that the \"([^\"]*)\" text is not present$")
	public void the_text_is_not_present(String textValue){
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
	public void assert_that_the_page_title_does_not_have(String pageSubTitle) {
		baymax.page_title_not_have(pageSubTitle);
	}

	@Thì("^kiểm tra tên miền của trang là \"([^\"]*)\"$")
	@Then("^assert that the url is \"([^\"]*)\"$")
	public void the_url_is(String domain) {
		baymax.is_url(domain);
	}

	@Thì("^kiểm tra tên miền của trang không là \"([^\"]*)\"$")
	@Then("^assert that the url is not \"([^\"]*)\"$")
	public void the_url_is_not(String domain) {
		baymax.is_not_url(domain);
	}

	protected static String actualImage = System.getProperty("user.dir") + "\\images\\";
	protected static String tmpImage = System.getProperty("user.dir") + "\\target\\";

	@Khi("^chụp hình đối tượng tại \"([^\"]*)\" và đặt tên ảnh là \"([^\"]*)\"$")
	@When("^capture image of \"([^\"]*)\" element and save as \"([^\"]*)\"$")
	public void capture_image_of_element(String element, String imgName) {
		String pathStorge = actualImage + imgName;
		baymax.capture_and_save_it(element, pathStorge);
	}

	@Thì("^kiểm tra hình ảnh của đối tượng tại \"([^\"]*)\" giống với hình ảnh \"([^\"]*)\"$")
	@Then("^assert that image of \"([^\"]*)\" element and expected \"([^\"]*)\" image is similar$")
	public void actual_image_and_expected_image_should_be_similar(String element, String imgName) {
		String pathStorge = tmpImage + imgName;
		baymax.capture_and_save_it(element, pathStorge);

		String actual = actualImage + imgName;

		baymax.actual_image_and_expected_image_should_be_similar(pathStorge, actual);
	}

	@Thì("^kiểm tra văn bản của đối tượng \"(.*?)\" là \"(.*?)\"$")
	@Then("^assert that the text \"(.*?)\" element is \"(.*?)\"$")
	public void the_text_element_is(String target, String value) throws Throwable {
		baymax.the_text_element_should_be(target, value);
	}

	@Thì("^kiểm tra văn bản của đối tượng \"(.*?)\" không là \"(.*?)\"$")
	@Then("^assert that the text \"(.*?)\" element is not \"(.*?)\"$")
	public void the_text_element_should_is_not(String target, String value) throws Throwable {
		baymax.the_text_element_should_not_be(target, value);
	}

	@Thì("^kiểm tra văn bản của đối tượng \"(.*?)\" có chứa \"(.*?)\"$")
	@Then("^assert that the text \"(.*?)\" element has \"(.*?)\"$")
	public void the_text_element_should_contain(String target, String value) throws Throwable {
		baymax.the_text_element_should_contain(target, value);
	}

	@Thì("^kiểm tra văn bản của đối tượng \"(.*?)\" không chứa \"(.*?)\"$")
	@Then("^assert that the text \"(.*?)\" element does not have \"(.*?)\"$")
	public void the_text_element_should_not_contain(String target, String value) throws Throwable {
		baymax.the_text_element_should_not_contain(target, value);
	}

	@Thì("^kiểm tra thuộc tính \"(.*?)\" của đối tượng \"(.*?)\" có giá trị là \"(.*?)\"$")
	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" is \"(.*?)\"$")
	public void element_attr_should_be(String attribute, String element, String value) throws Throwable {
		baymax.the_attribute_of_element_should_be(attribute, element, value);
	}

	@Thì("^kiểm tra thuộc tính \"(.*?)\" của đối tượng \"(.*?)\" không có giá trị là \"(.*?)\"$")
	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" is not \"(.*?)\"$")
	public void element_attr_should_not_be(String attribute, String element, String value) {
		baymax.the_attribute_of_element_should_not_be(attribute, element, value);
	}

	@Thì("^kiểm tra thuộc tính \"(.*?)\" của đối tượng \"(.*?)\" có chứa giá trị là \"(.*?)\"$")
	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" has \"(.*?)\"$")
	public void element_attr_should_contain(String attribute, String element, String value) {
		baymax.the_attribute_of_element_should_contain(attribute, element, value);
	}

	@Thì("^kiểm tra thuộc tính \"(.*?)\" của đối tượng \"(.*?)\" không chứa giá trị là \"(.*?)\"$")
	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" does not have \"(.*?)\"$")
	public void element_attr_should_not_contain(String attribute, String element, String value) {
		baymax.the_attribute_of_element_should_not_contain(attribute, element, value);
	}

	@Thì("^kiểm tra đối tượng \"(.*?)\" có hiệu lực$")
	@Then("^assert that the \"(.*?)\" element is enabled$")
	public void the_element_should_be_enabled(String element) {
		baymax.the_element_should_be_enabled(element);
	}

	@Thì("^kiểm tra đối tượng \"(.*?)\" bị vô hiệu hóa$")
	@Then("^assert that the \"(.*?)\" element is disabled$")
	public void the_element_should_be_disabled(String element) {
		baymax.the_element_should_be_disabled(element);
	}

	@Thì("^kiểm tra đối tượng \"(.*?)\" có tồn tại$")
	@Then("^assert that the \"(.*?)\" element is present$")
	public void the_element_should_be_present(String element) {
		baymax.the_element_should_be_present(element);
	}

	@Thì("^kiểm tra đối tượng \"(.*?)\" không tồn tại$")
	@Then("^assert that the \"(.*?)\" element is absent$")
	public void the_element_should_not_be_present(String element) {
		baymax.the_element_should_not_be_present(element);
	}

	@Thì("^kiểm tra đối tượng \"(.*?)\" nhìn thấy được$")
	@Then("^assert that the \"(.*?)\" element is visible$")
	public void the_element_should_be_visible(String element) {
		baymax.the_element_should_be_visible(element);
	}

	@Thì("^kiểm tra đối tượng \"(.*?)\" bị ẩn$")
	@Then("^assert that the \"(.*?)\" element is hidden$")
	public void the_element_should_be_hidden(String element) {
		baymax.the_element_should_be_hidden(element);
	}
	
	@Khi("^thay đổi thuộc tính \"([^\"]*)\" của đối tượng \"([^\"]*)\" thành \"([^\"]*)\"$")
	@When("^change \"([^\"]*)\" attribute of \"([^\"]*)\" into \"([^\"]*)\"$")
	public void change_attribute_of_into(String attr, String element, String attrValue){
		baymax.change_attribute_of_into(attr, element, attrValue);
	}

	@Khi("^làm nổi bật đối tượng \"([^\"]*)\"$")
	@When("^highlight \"([^\"]*)\" element$")
	public void highlight_element(String element){
		baymax.highlight_element(element);
	}

	@Khi("^vẽ đường bao quanh đối tượng \"([^\"]*)\"$")
	@When("^set bound for \"([^\"]*)\" element$")
	public void set_bound_for_element(String element){
		baymax.set_bound_for_element(element);
	}
	
	@Khi("^truy cập vào khung \"([^\"]*)\"$")
	@When("^go inside the \"([^\"]*)\" frame$")
	public void swich_to_frame(String iframeName){
		baymax.swich_to_frame(iframeName);
	}
	
	@Khi("quay lại khung trước đó$")
	@When("^come back to parent frame$")
	public void swich_back_to_parent_frame(){
		baymax.swich_back_to_parent_frame();
	}

	@Khi("quay lại khung chính$")
	@When("^come back to main frame$")
	public void swich_back_to_main_frame(){
		baymax.swich_back_to_main_frame();
	}
	
	@Khi("tải lên tài liệu \"([^\"]*)\"$")
	@When("^\"([^\"]*)\" file is uploaded$")
	public void upload_file(String pathFile){
		baymax.upload_file(pathFile);
	}
	
	@Khi("^thêm \"([^\"]*)\" vào trường \"([^\"]*)\"$")
	@When("^\"([^\"]*)\" is sent into \"([^\"]*)\" field$")
	public void fill_into_filed(String target, String value){
		baymax.enter_into_the_field_with(target, value);
	}

	@Khi("^điền \"([^\"]*)\" vào trường \"([^\"]*)\"$")
	@When("^\"([^\"]*)\" is typed into \"([^\"]*)\" field$")
	public void type_into_the_field_with(String target, String value){
		baymax.type_into_the_field_with(target, value);
	}

	@Khi("^nhấn chuột trái vào đối tượng \"([^\"]*)\"$")
	@When("^\"([^\"]*)\" element is clicked$")
	public void click_on_the(String target){
		baymax.click_on_the(target);
	}
	
	@Khi("^chờ đối tượng \"([^\"]*)\" có thể nhấn$")
	@When("^wait for the \"([^\"]*)\" element to be clickable$")
	public void wait_for_the_element_to_be_clickable(String element) throws Throwable {
		baymax.wait_for_the_element_to_be_clickable(element);
	}

	@Khi("^chờ văn bản \"([^\"]*)\" xuất hiện$")
	@When("^wait for any \"([^\"]*)\" text to be present$")
	public void wait_for_any_text_to_be_present(String textValue) throws Throwable {
		baymax.wait_for_any_text_to_be_present(textValue);
	}

}
