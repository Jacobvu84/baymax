package thich.thong.lac.steps;
import java.util.HashMap;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import thich.thong.lac.pages.BaymaxCore;

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

}