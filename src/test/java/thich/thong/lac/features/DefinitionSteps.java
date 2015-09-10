package thich.thong.lac.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import thich.thong.lac.steps.BaymaxSteps;

public class DefinitionSteps {

	@Steps
	BaymaxSteps baymax;

	@Given("the home page is visited")
	public void home_page_is_visited() {
		baymax.visit();
	}

	@When("^the page \"([^\"]*)\" is visited$")
	public void open_the(String url) {
		baymax.open_the(url);
	}

	@When("^the page \"([^\"]*)\" is opened on another window$")
	public void open_in_new_window(String url) {
		baymax.open_in_new_window(url);
	}

	@When("^open \"([^\"]*)\" link in new window and switch to it$")
	public void open_link_in_new_window(String linkText) {
		baymax.open_link_in_new_window(linkText);
	}

	@When("^open \"([^\"]*)\" dialog and switch to it$")
	public void open_dialog(String element) {
		baymax.open_dialog(element);
	}

	@When("^switch back to the original window$")
	public void switch_back_to_the_original_window() {
		baymax.switch_back_to_the_original_window();
	}

	@When("^maximize the window$")
	public void maximize_the_window() {
		baymax.maximize_the_window();
	}

	@When("^go forward one page$")
	public void go_forward_one_page() {
		baymax.go_forward_one_page();
	}

	@When("^go backward one page$")
	public void move_backward_one_page() {
		baymax.go_backward_one_page();
	}

	@When("^the page is refreshed$")
	public void reload_the_page() {
		baymax.refresh_page();
	}

	@When("^window is moved to location with coordinates \\((\\d+),(\\d+)\\)$")
	public void move_window_to(int x, int y) {
		baymax.move_the_window_to(x, y);
	}

	@When("^window dimension is changed with size \\((\\d+),(\\d+)\\)$")
	public void adjust_window_dimension(int width, int height) {
		baymax.adjust_window_dimension(width, height);
	}

	@When("^close current window$")
	public void close_current_window() {
		baymax.close_current_window();
	}

	@When("^scroll up or down in screen with value \\((\\d+),(\\d+)\\)$")
	public void scroll_screen_to(int x, int y) {
		baymax.scroll_screen_to(x, y);
	}

	@When("^wait for (\\d+) seconds$")
	public void wait_for_seconds(int time) throws Throwable {
		Thread.sleep(time * 1000);
	}

	@When("^assert that the \"([^\"]*)\" text is present$")
	public void the_text_is_present(String textValue) {
		baymax.the_text_is_present(textValue);
	}

	@When("^assert that the \"([^\"]*)\" text is not present$")
	public void the_text_is_not_present(String textValue){
		baymax.the_text_is_not_present(textValue);
	}

	@Then("^assert that the page title is \"([^\"]*)\"$")
	public void page_title_is(String pageTitle) {
		baymax.page_title_is(pageTitle);
	}

	@Then("^assert that the page title is not \"([^\"]*)\"$")
	public void page_title_is_not(String pageTitle) {
		baymax.page_title_not_is(pageTitle);
	}

	@Then("^assert that the page title has \"([^\"]*)\"$")
	public void assert_that_the_page_title_has(String pageSubTitle) {
		baymax.page_title_has(pageSubTitle);
	}

	@Then("^assert that the page title does not have \"([^\"]*)\"$")
	public void assert_that_the_page_title_does_not_have(String pageSubTitle) {
		baymax.page_title_not_have(pageSubTitle);
	}

	@Then("^assert that the url is \"([^\"]*)\"$")
	public void the_url_is(String domain) {
		baymax.is_url(domain);
	}

	@Then("^assert that the url is not \"([^\"]*)\"$")
	public void the_url_is_not(String domain) {
		baymax.is_not_url(domain);
	}

	protected static String actualImage = System.getProperty("user.dir") + "\\images\\";
	protected static String tmpImage = System.getProperty("user.dir") + "\\target\\";

	@When("^capture image of \"([^\"]*)\" element and save as \"([^\"]*)\"$")
	public void capture_image_of_element(String element, String imgName) {
		String pathStorge = actualImage + imgName;
		baymax.capture_and_save_it(element, pathStorge);
	}

	@Then("^assert that image of \"([^\"]*)\" element and expected \"([^\"]*)\" image is similar$")
	public void actual_image_and_expected_image_should_be_similar(String element, String imgName) {
		String pathStorge = tmpImage + imgName;
		baymax.capture_and_save_it(element, pathStorge);

		String actual = actualImage + imgName;

		baymax.actual_image_and_expected_image_should_be_similar(pathStorge, actual);
	}

	@Then("^assert that the text \"(.*?)\" element is \"(.*?)\"$")
	public void the_text_element_is(String target, String value) throws Throwable {
		baymax.the_text_element_should_be(target, value);
	}

	@Then("^assert that the text \"(.*?)\" element is not \"(.*?)\"$")
	public void the_text_element_should_is_not(String target, String value) throws Throwable {
		baymax.the_text_element_should_not_be(target, value);
	}

	@Then("^assert that the text \"(.*?)\" element has \"(.*?)\"$")
	public void the_text_element_should_contain(String target, String value) throws Throwable {
		baymax.the_text_element_should_contain(target, value);
	}

	@Then("^assert that the text \"(.*?)\" element does not have \"(.*?)\"$")
	public void the_text_element_should_not_contain(String target, String value) throws Throwable {
		baymax.the_text_element_should_not_contain(target, value);
	}

	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" is \"(.*?)\"$")
	public void element_attr_should_be(String attribute, String element, String value) throws Throwable {
		baymax.the_attribute_of_element_should_be(attribute, element, value);
	}

	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" is not \"(.*?)\"$")
	public void element_attr_should_not_be(String attribute, String element, String value) {
		baymax.the_attribute_of_element_should_not_be(attribute, element, value);
	}

	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" has \"(.*?)\"$")
	public void element_attr_should_contain(String attribute, String element, String value) {
		baymax.the_attribute_of_element_should_contain(attribute, element, value);
	}

	@Then("^assert that the \"(.*?)\" attribute of \"(.*?)\" does not have \"(.*?)\"$")
	public void element_attr_should_not_contain(String attribute, String element, String value) {
		baymax.the_attribute_of_element_should_not_contain(attribute, element, value);
	}

	@Then("^assert that the \"(.*?)\" element is enabled$")
	public void the_element_should_be_enabled(String element) {
		baymax.the_element_should_be_enabled(element);
	}

	@Then("^assert that the \"(.*?)\" element is disabled$")
	public void the_element_should_be_disabled(String element) {
		baymax.the_element_should_be_disabled(element);
	}

	@Then("^assert that the \"(.*?)\" element is present$")
	public void the_element_should_be_present(String element) {
		baymax.the_element_should_be_present(element);
	}

	@Then("^assert that the \"(.*?)\" element is absent$")
	public void the_element_should_not_be_present(String element) {
		baymax.the_element_should_not_be_present(element);
	}

	@Then("^assert that the \"(.*?)\" element is visible$")
	public void the_element_should_be_visible(String element) {
		baymax.the_element_should_be_visible(element);
	}

	@Then("^assert that the \"(.*?)\" element is hidden$")
	public void the_element_should_be_hidden(String element) {
		baymax.the_element_should_be_hidden(element);
	}
	
	@When("^change \"([^\"]*)\" attribute of \"([^\"]*)\" into \"([^\"]*)\"$")
	public void change_attribute_of_into(String attr, String element, String attrValue){
		baymax.change_attribute_of_into(attr, element, attrValue);
	}

	@When("^highlight \"([^\"]*)\" element$")
	public void highlight_element(String element){
		baymax.highlight_element(element);
	}

	@When("^set bound for \"([^\"]*)\" element$")
	public void set_bound_for_element(String element){
		baymax.set_bound_for_element(element);
	}
	
	@When("^go inside the \"([^\"]*)\" frame$")
	public void swich_to_frame(String iframeName){
		baymax.swich_to_frame(iframeName);
	}
	
	@When("^come back to parent frame$")
	public void swich_back_to_parent_frame(){
		baymax.swich_back_to_parent_frame();
	}

	@When("^come back to main frame$")
	public void swich_back_to_main_frame(){
		baymax.swich_back_to_main_frame();
	}
	
	@When("^\"([^\"]*)\" file is uploaded$")
	public void upload_file(String pathFile){
		baymax.upload_file(pathFile);
	}
	
	@When("^\"([^\"]*)\" is sent into \"([^\"]*)\" field$")
	public void fill_into_filed(String target, String value){
		baymax.enter_into_the_field_with(target, value);
	}

	@When("^\"([^\"]*)\" is typed into \"([^\"]*)\" field$")
	public void type_into_the_field_with(String target, String value){
		baymax.type_into_the_field_with(target, value);
	}

	@When("^\"([^\"]*)\" element is clicked$")
	public void click_on_the(String target){
		baymax.click_on_the(target);
	}
	
	@When("^wait for the \"([^\"]*)\" element to be clickable$")
	public void wait_for_the_element_to_be_clickable(String element){
		baymax.wait_for_the_element_to_be_clickable(element);
	}

	@When("^wait for any \"([^\"]*)\" text to be present$")
	public void wait_for_any_text_to_be_present(String textValue){
		baymax.wait_for_any_text_to_be_present(textValue);
	}
	
	@When("^choose OK on confirm dialog box \"([^\"]*)\"$")
	public void dialog_box_is_opened_and_accept_it(String element) {
		baymax.accept_dialog_popup(element);
	}
	
	@When("^choose OK on confirm dialog box$")
	public void accept_dialog_popup() {
		baymax.accept_dialog_popup();
	}

	@When("^choose CANCEL on confirm dialog box \"([^\"]*)\"$")
	public void dialog_box_is_opened_and_dimiss_it(String element){
		baymax.dimiss_dialog_popup(element);
	}
	
	@When("^choose CANCEL on confirm dialog box$")
	public void dimiss_dialog_popup(){
		baymax.dimiss_dialog_popup();
	}
	
	@When("^input \"([^\"]*)\" on prompt dialog box \"([^\"]*)\"$")
	public void input_on_prompt_dialog_box(String answer, String element) {
		baymax.answer_dialog_popup(answer, element);
	}
	
	@When("^input \"([^\"]*)\" on prompt dialog box$")
	public void answer_dialog_popups(String answer) {
		baymax.answer_dialog_popup(answer);
	}
	
	@Then("^assert that text on \"([^\"]*)\" popup box is \"([^\"]*)\"$")
	public void assert_that_text_on_popup_box(String element, String value){
		baymax.text_on_popup_box(element,value);
	}
	
	@Then("^assert that text on popup box is \"([^\"]*)\"$")
	public void assert_that_text_on_popup_box_is(String value){
		baymax.text_on_popup_box_is(value);
	}
	
	@Then("^assert that the value of \"([^\"]*)\" is \"([^\"]*)\"$")
	public void assert_that_the_value_of_is(String element, String value){
		baymax.assert_that_the_value(element,value);
	}

	@Then("^assert that the value of \"([^\"]*)\" is not \"([^\"]*)\"$")
	public void assert_that_the_value_of_is_not(String element, String value){
		baymax.assert_that_the_value_not(element,value);
	}

	@Then("^assert that the value of \"([^\"]*)\" has \"([^\"]*)\"$")
	public void assert_that_the_value_of_has(String element, String value){
		baymax.assert_that_the_value_has(element,value);
	}

	@Then("^assert that the value of \"([^\"]*)\" does not have \"([^\"]*)\"$")
	public void assert_that_the_value_of_does_not_have(String element, String value){
		baymax.assert_that_the_value_has_no(element,value);
	}
	
	@When("^select \"(.*?)\" label from \"(.*?)\" drop-down list$")
	public void select_label_from_drop_down_list(String visibleLabel, String element) throws Throwable {
		baymax.select_label_from_drop_down_list(visibleLabel, element);
	}

	@When("^select \"(.*?)\" value from \"(.*?)\" drop-down list$")
	public void select_value_from_drop_down_list(String valueOption, String element) throws Throwable {
		baymax.select_value_from_drop_down_list(valueOption, element);
	}

	@When("^select (\\d+) index from \"(.*?)\" drop-down list$")
	public void select_index_from_drop_down_list(int indexOption, String element) throws Throwable {
		baymax.select_index_from_drop_down_list(indexOption, element);
	}

	@When("^add multi-select \"([^\"]*)\" options from \"([^\"]*)\" drop-down list$")
	public void add_multi_select_options_from_drop_down_list(String listOption, String element) throws Throwable {
		baymax.add_multi_select_options_from_drop_down_list(listOption, element);
	}

	@When("^remove multi-select \"([^\"]*)\" options from \"([^\"]*)\" drop-down list$")
	public void remove_multi_select_options_from_drop_down_list(String listOption, String element) throws Throwable {
		baymax.remove_multi_select_options_from_drop_down_list(listOption, element);
	}
	
	@Then("^the \"(.*?)\" option label in \"(.*?)\" should be selected$")
	public void the_option_label_in_should_be_selected(String visibleText, String element) throws Throwable {
		baymax.the_option_label_in_should_be_selected(visibleText, element);
	}

	@Then("^the \"(.*?)\" option label in \"(.*?)\" should not be selected$")
	public void the_option_label_in_should_not_be_selected(String visibleText, String element) throws Throwable {
		baymax.the_option_label_in_should_not_be_selected(visibleText, element);
	}

}
