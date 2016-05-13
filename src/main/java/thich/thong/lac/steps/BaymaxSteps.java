package thich.thong.lac.steps;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.webdriver.DriverSource;
import thich.thong.lac.pages.BaymaxCore;
import thich.thong.lac.util.CompareGraph;

public class BaymaxSteps extends ScenarioSteps{

	BaymaxCore onBaymax;

	HashMap<String, String> listVar = new HashMap<String, String>();	

	@Step
	public void visit() {
		//onBaymax.open();
		onBaymax.newDriver().get("http://google.com");
	}
	
	@Step
	public void open_the(String url) {
		onBaymax.openAt(url);
	}
	
	@Step
	public void open_in_new_window(String url) {
		onBaymax.openUrlInNewWindow(url);
	}
	
	@Step
	public void open_link_in_new_window(String linkText) {
		onBaymax.openLinkInNewWindow(linkText);
	}
	
	@Step
	public void open_dialog(String element) {
		onBaymax.openDialog(element);
	}
	
	@Step
	public void switch_back_to_the_original_window() {
		onBaymax.backOriginalWindow();
	}
	
	@Step
	public void maximize_the_window() {
		onBaymax.maximizeWindow();
	}
	
	@Step
	public void go_forward_one_page() {
		onBaymax.goForward();
	}

	@Step
	public void go_backward_one_page() {
		onBaymax.goBack();
	}

	@Step
	public void refresh_page() {
		onBaymax.refreshPage();
	}
	
	@Step
	public void move_the_window_to(int x, int y) {
		onBaymax.moveWindowToLocation(x, y);
	}
	
	@Step
	public void adjust_window_dimension(int width, int height) {
		onBaymax.resizeWindow(width, height);
	}

	@Step
	public void close_current_window() {
		onBaymax.closeWindow();
	}
	
	@Step
	public void scroll_screen_to(int x, int y) {
		onBaymax.scrollTo(x, y);
	}

	@Step
	public void the_text_is_present(String textValue) {
		assertThat(onBaymax.containsText(textValue), is(true));
	}

	@Step
	public void the_text_is_not_present(String textValue) {
		assertThat(onBaymax.containsText(textValue), is(false));
	}
	
	@Step
	public void page_title_has(String pageSubTitle) {
		assertThat(onBaymax.getTitle(), containsString(pageSubTitle));
	}

	@Step
	public void page_title_not_have(String pageSubTitle) {
		assertThat(onBaymax.getTitle(), not(containsString(pageSubTitle)));
	}

	@Step
	public void page_title_is(String pageTitle) {
		assertThat(onBaymax.getTitle(), equalTo(pageTitle));
	}

	@Step
	public void page_title_not_is(String pageTitle) {
		assertThat(onBaymax.getTitle(), not(equalTo(pageTitle)));
	}

	@Step
	public void is_url(String url) {
		assertThat(onBaymax.getUrl(), equalTo(url));
	}
	
	@Step
	public void is_not_url(String url) {
		assertThat(onBaymax.getUrl(), not(equalTo(url)));
	}
	
	@Step
	public void actual_image_and_expected_image_should_be_similar(String expectImg, String actualImge) {
		assertThat(CompareGraph.Result.Matched, equalTo(CompareGraph.CompareImage(expectImg, actualImge)));
	}

	@Step
	public void capture_and_save_it(String element, String pathStorge) {
			try {
				FileUtils.copyFile(onBaymax.captureElementBitmap(element), new File(pathStorge));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	@Step
	public void the_text_element_should_be(String element, String value) {
		assertThat(getText(element), equalTo(value));
	}

	public String getText(String element) {
		return onBaymax.getWebElement(element).getText();
	}

	public String getAttribute(String attrName, String element) {
		return onBaymax.getWebElement(element).getAttribute(attrName);
	}

	@Step
	public void the_text_element_should_not_be(String element, String value) {
		assertThat(getText(element), not(equalTo(value)));
	}

	@Step
	public void the_text_element_should_contain(String element, String value) {
		String elementText = onBaymax.getWebElement(element).getText();
		assertThat(elementText, containsString(value));
	}

	@Step
	public void the_text_element_should_not_contain(String element, String value) {
		String elementText = onBaymax.getWebElement(element).getText();
		assertThat(elementText, not(containsString(value)));
	}
	
	@Step
	public void the_element_should_be_enabled(String element) {
		assertThat(onBaymax.isEnabled(element), is(true));
	}

	@Step
	public void the_element_should_be_disabled(String element) {
		assertThat(onBaymax.isEnabled(element), is(false));
	}

	@Step
	public void the_element_should_be_present(String element) {
		assertThat(onBaymax.isPresent(element), is(true));
	}

	@Step
	public void the_element_should_not_be_present(String element) {
		assertThat(onBaymax.isPresent(element), is(false));
	}

	@Step
	public void the_element_should_be_visible(String element) {
		assertThat(onBaymax.isVisible(element), is(true));
	}

	@Step
	public void the_element_should_be_hidden(String element) {
		assertThat(onBaymax.isVisible(element), is(false));
	}
	
	@Step
	public void the_attribute_of_element_should_contain(String attrName, String element, String value) {
		assertThat(getAttribute(attrName, element), containsString(value));
	}

	@Step
	public void the_attribute_of_element_should_be(String attrName, String element, String value) {
		assertThat(getAttribute(attrName, element), equalTo(value));
	}

	@Step
	public void the_attribute_of_element_should_not_contain(String attrName, String element, String value) {
		assertThat(getAttribute(attrName, element), not(containsString(value)));
	}

	@Step
	public void the_attribute_of_element_should_not_be(String attrName, String element, String value) {
		assertThat(getAttribute(attrName, element), not(equalTo(value)));
	}
	
	@Step
	public void change_attribute_of_into(String attr, String element, String attrValue) {
		onBaymax.setAttribute(attr, element, attrValue);
	}

	@Step
	public void highlight_element(String element) {
		onBaymax.highlightElement(element);
	}

	@Step
	public void set_bound_for_element(String element) {
		onBaymax.setBounds(element);
	}
	
	@Step
	public void swich_to_frame(String iframeName) {
		onBaymax.swichToFrame(iframeName);
	}

	@Step
	public void swich_back_to_parent_frame() {
		onBaymax.backToUpFrame();
	}

	@Step
	public void swich_back_to_main_frame() {
		onBaymax.backToMainFrame();
	}
	
	@Step
	public void upload_file(String pathFile) {
		onBaymax.upload_file(pathFile);
	}
	
	@Step
	public void enter_into_the_field_with(String element, String value) {
		WebElement webElement = onBaymax.getWebElement(element);
		onBaymax.element(webElement).clear();
		onBaymax.element(webElement).sendKeys(value);
	}
	
	@Step
	public void type_into_the_field_with(String element, String value) {
		WebElement webElement = onBaymax.getWebElement(element);
		onBaymax.typeInto(webElement, value);
	}

	@Step
	public void click_on_the(String element) {
		WebElement webElement = onBaymax.getWebElement(element);
		onBaymax.clickOn(webElement);
	}
	
	@Step
	public void wait_for_the_element_to_be_clickable(String element) {
		onBaymax.wait_for_the_element_to_be_clickable(element);
	}
	
	@Step
	public void wait_for_any_text_to_be_present(String textValue) {
		onBaymax.waitForAnyTextToAppear(textValue);
	}
	
	@Step
	public void accept_dialog_popup(String element) {
		onBaymax.acceptPopUp(element);
	}
	
	@Step
	public void accept_dialog_popup() {
		onBaymax.acceptPopUp();
	}

	@Step
	public void dimiss_dialog_popup(String element) {
		onBaymax.dismissPopUp(element);
	}
	
	@Step
	public void dimiss_dialog_popup() {
		onBaymax.dismissPopUp();
	}
	
	@Step
	public void answer_dialog_popup(String answer, String element) {
		onBaymax.answerPopUp(answer, element);
	}
	
	@Step
	public void answer_dialog_popup(String answer) {
		onBaymax.answerPopUp(answer);
	}
	
	@Step
	public void text_on_popup_box(String element,String value) {
		assertThat(onBaymax.getTextAlert(element), equalTo(value));
	}

	@Step
	public void text_on_popup_box_is(String value) {
		assertThat(onBaymax.getTextAlert(), equalTo(value));
	}

	@Step
	public void assert_that_the_value(String element, String value) {
		assertThat(onBaymax.getValueField(element), equalTo(value));
	}
	
	@Step
	public void assert_that_the_value_not(String element, String value) {
		assertThat(onBaymax.getValueField(element), not(equalTo(value)));
	}

	@Step
	public void assert_that_the_value_has(String element, String value) {
		assertThat(onBaymax.getValueField(element), containsString(value));
	}

	public void assert_that_the_value_has_no(String element, String value) {
		assertThat(onBaymax.getValueField(element), not(containsString(value)));
	}
	
	@Step
	public void select_label_from_drop_down_list(String visibleLabel, String element) {
		WebElement webElement = onBaymax.getWebElement(element);
		onBaymax.selectFromDropdown(webElement, visibleLabel);
	}

	@Step
	public void select_value_from_drop_down_list(String valueOption, String element) {
		onBaymax.selectValueInListBox(valueOption, element);
	}

	@Step
	public void select_index_from_drop_down_list(int indexOption, String element) {
		onBaymax.selectIndexInListBox(indexOption, element);
	}
	
	@Step
	public void add_multi_select_options_from_drop_down_list(String listOption, String element) {
		onBaymax.clickOptionItem(listOption, element);
	}

	@Step
	public void remove_multi_select_options_from_drop_down_list(String listOption, String element) {
		onBaymax.clickOptionItem(listOption, element);
	}
	
	@Step
	public void the_option_label_in_should_be_selected(String visibleText, String element) {
		assertThat(onBaymax.isOptionSelected(element), equalTo(visibleText));
	}

	@Step
	public void the_option_label_in_should_not_be_selected(String visibleText, String element) {
		assertThat(onBaymax.isOptionSelected(element), not(equalTo(visibleText)));
	}
	
	@Step
	public void click_on_the_at_coordinates(String element, int xOffset, int yOffset) {
		onBaymax.clickElementAt(element, xOffset, yOffset);
	}
	
	@Step
	public void double_click_on_the(String element) {
		onBaymax.doubleClickElement(element);
	}

	@Step
	public void double_click_on_the_at_coordinates(String element, int xOffset, int yOffset) {
		onBaymax.doubleClickElementAt(element, xOffset, yOffset);
	}
	
	@Step
	public void right_click_on_the(String element) {
		onBaymax.rightClickElement(element);
	}

	@Step
	public void right_click_on_the_at_coordinates(String element, int xOffset, int yOffset) {
		onBaymax.rightClickElementAt(element, xOffset, yOffset);
	}

	@Step
	public void right_click_on_the_and_click_on_item_has_index(String element, int indexIterm) {
		onBaymax.pressItemOnContentMenu(element, indexIterm);
	}
	
	@Step
	public void click_and_hold(String element) {
		onBaymax.clickAndHoldElement(element);
	}

	@Step
	public void move_mouse_to(String element) {
		onBaymax.moveMouseToElement(element);
	}

	@Step
	public void move_mouse_to_at_coordinates(String element, int xOffset, int yOffset) {
		onBaymax.moveMouseToElementAt(element, xOffset, yOffset);
	}
	
	@Step
	public void drag_the_and_drop_to_the(String resouce, String dest) {
		onBaymax.drag_the_and_drop_to_the(resouce, dest);
	}

	@Step
	public void html5_drag_the_and_drop_to_the(String resouce, String dest) throws AWTException {
		onBaymax.drag_the_and_drop_html5(resouce, dest, 0, 70);
	}
	
	@Step
	public void store_the_text_of_element_in_variable(String element, String var) {
		String value = onBaymax.getWebElement(element).getText();
		listVar.put(var, value);
	}
	
	@Step
	public void variable_should_be(String var, String valueCompare) {
		assertThat(listVar.get(var), equalTo(valueCompare));
	}
	
	@Step
	public void check_checkbox(String element) {
		onBaymax.checkBox(element);
	}

	@Step
	public void uncheck_checkbox(String element) {
		onBaymax.uncheckBox(element);
	}
	
	@Step
	public void navigate_to(String url) {
		onBaymax.navigate_to(url);
	}

	@Step
	public void enter_into_the_field_with_variable(String element, String var) {
		WebElement webElement = onBaymax.getWebElement(element);
		onBaymax.waitFor(webElement);
		onBaymax.element(webElement).clear();
		onBaymax.element(webElement).sendKeys(listVar.get(var));
	}

	@Step
	public void element_is_scrolled_into_view(String target) {
		onBaymax.scrolled_element_into_view(target);
	}

	
}