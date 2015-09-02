package thich.thong.lac.steps;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import thich.thong.lac.pages.BaymaxCore;
import thich.thong.lac.util.CompareGraph;

public class BaymaxSteps extends ScenarioSteps {

	BaymaxCore onBaymax;

	HashMap<String, String> listVar = new HashMap<String, String>();	

	@Step
	public void visit() {
		onBaymax.open();
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

}