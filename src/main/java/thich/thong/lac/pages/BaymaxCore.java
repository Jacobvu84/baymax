package thich.thong.lac.pages;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.thucydides.core.pages.PageObject;
import thich.thong.lac.util.LoadObject;

public class BaymaxCore extends PageObject {
	
	String defaultWindow = "";
	
	public static Properties OBJECT;
	public static Properties CONFIG;
	
	public BaymaxCore() {
		try {
			OBJECT = LoadObject.loading_object_repository("objects");
			//CONFIG = LoadObject.loading_config_sys("setting.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  WebElement getWebElement(String target) {
		try {
			return getDriver().findElement(getObject(target));

		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		return null;
	}

	private By getObject(String locator) {
		By by = null;
		try {
			if (locator.startsWith("id=")) {

				locator = locator.substring(3);
				by = By.id(locator);

			} else if (locator.startsWith("name=")) {

				locator = locator.substring(5);
				by = By.name(locator);

			} else if (locator.startsWith("css=")) {

				locator = locator.substring(4);
				by = By.cssSelector(locator);

			} else if (locator.startsWith("link=")) {

				locator = locator.substring(5);
				by = By.linkText(locator);

			} else if (locator.startsWith("xpath=")) {
				locator = locator.substring(6);
				by = By.xpath(locator);
			}

			return by;
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	public void openUrlInNewWindow(String url) {
		defaultWindow = getDriver().getWindowHandle();
		evaluateJavascript("window.open('" + url + "', 'myD', 'fullscreen=yes');");
		getDriver().switchTo().window("myD");
	}
	
	public void openLinkInNewWindow(String linkText) {

		defaultWindow = getDriver().getWindowHandle();
		try {
			WebElement link = element(getWebElement(linkText));
			withAction().moveToElement(link).contextClick(link).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
			Thread.sleep(1000);
		} catch (MoveTargetOutOfBoundsException e) {e.getStackTrace();
		} catch (InterruptedException e) {e.printStackTrace();}

		ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());
		tabs.remove(defaultWindow);
		getDriver().switchTo().window( tabs.get(0));
	}
	
	public void openDialog(String element) {
		getWebElement(element).click();
		defaultWindow = getDriver().getWindowHandle();
		ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());
		tabs.remove(defaultWindow);
		getDriver().switchTo().window( tabs.get(0));

	}
	
	public void backOriginalWindow() {
		getDriver().switchTo().window(defaultWindow);
	}
	
	public void maximizeWindow() {
		getDriver().manage().window().maximize();
	}
	
	public void goForward() {
		getDriver().navigate().forward();
	}

	public void goBack() {
		getDriver().navigate().back();
	}

	public void refreshPage() {
		getDriver().navigate().refresh();
	}
	
	public void moveWindowToLocation(int x, int y) {
		getDriver().manage().window().setPosition(new Point(x, y));	
	}
	
	public void resizeWindow(int width, int height) {
		getDriver().manage().window().setSize(new Dimension(width, height));
	}

	public void closeWindow() {
		getDriver().close();
	}
	
	public void scrollTo(int x, int y) {
		evaluateJavascript("window.scrollTo("+x+", "+y+");");
	}

	public String getUrl() {
		return getDriver().getCurrentUrl();
	}
	
	public File captureElementBitmap(String element) throws Exception {

		File screen = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		WebElement webElemet = getWebElement(element);
		// Create an instance of Buffered Image from captured screenshot
		BufferedImage img = ImageIO.read(screen);

		// Get the Width and Height of the WebElement using getSize()
		int width = webElemet.getSize().getWidth();
		int height = webElemet.getSize().getHeight();

		// Create a rectangle using Width and Heights
		Rectangle rect = new Rectangle(width, height);

		// Get the Location of WebElement in a Point.
		// This will provide X & Y co-ordinates of the WebElement
		Point p = webElemet.getLocation();

		// Create image by_class for element using its location and size.
		// This will give image data specific to the WebElement
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

		// Write back the image data for element in File object
		ImageIO.write(dest, "png", screen);

		// Return the File object containing image data
		return screen;
	}
	
	// disabled - enabled
	public boolean isEnabled(String element) {
		return element(getWebElement(element)).isEnabled();
	}

	// present - absent
	public boolean isPresent(String element) {
		return element(getWebElement(element)).isPresent();
	}

	// visible - hidden
	public boolean isVisible(String element) {
		return element(getWebElement(element)).isVisible();
	}
	
	public void highlightElement(String element) {
		WebElement webElement = getWebElement(element);
		for (int i = 0; i < 10; i++) {
			evaluateJavascript("arguments[0].setAttribute('style', arguments[1]);", webElement,
					"color: green; border: 2px solid green;");
			evaluateJavascript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
		}
	}

	public void setAttribute(String attributeName, String element, String value) {
		WebElement webElement = getWebElement(element);
		evaluateJavascript("arguments[0].setAttribute(arguments[1], arguments[2])", webElement, attributeName, value);
	}

	// highlight element but not clean bound
	public void setBounds(String element) {
		WebElement webElement = getWebElement(element);
		evaluateJavascript("arguments[0].setAttribute('style', arguments[1]);", webElement,
				"color: red; border: 3px solid red;");
	}
	
	public void swichToFrame(String iframeName) {
		WebElement webElement = getWebElement(iframeName);
		getDriver().switchTo().frame(webElement);
	}

	public void backToUpFrame() {
		getDriver().switchTo().parentFrame();
	}

	public void backToMainFrame() {
		getDriver().switchTo().defaultContent();
	}
	
	public void upload_file(String pathFile) {
		uploadFile(pathFile);
	}

	/**
	 * This method will set any parameter string to the system's clipboard.
	 */
	private static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	private static void uploadFile(String fileLocation) {
		try {
			// Setting clip board with file location
			setClipboardData(fileLocation);
			// native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	
	public void wait_for_the_element_to_be_clickable(String element) {
		new WebDriverWait(getDriver(), 60).ignoring(NoSuchElementException.class)
				                          .until(ExpectedConditions.elementToBeClickable(getWebElement(element)));
	}

	public void acceptPopUp(String element) {
		getWebElement(element).click();
		waitForAlert();
		getAlert().accept();
	}
	
	public void acceptPopUp() {
		waitForAlert();
		getAlert().accept();
	}

	private void waitForAlert() {
		new WebDriverWait(getDriver(), 60).ignoring(NoAlertPresentException.class)
										  .until(ExpectedConditions.alertIsPresent());
	}

	public void dismissPopUp(String element) {
		getWebElement(element).click();
		waitForAlert();
		getAlert().dismiss();
	}
	
	public void dismissPopUp() {
		waitForAlert();
		getAlert().dismiss();
		//getAlert().authenticateUsing(new UserAndPassword("admin", "admin"));
	}
	
	public void answerPopUp(String answer, String element) {
		getWebElement(element).click();
		waitForAlert();
		getAlert().sendKeys(answer);
		getAlert().accept();
	}
	
	public void answerPopUp(String answer) {
		waitForAlert();
		getAlert().sendKeys(answer);
		getAlert().accept();
	}
	
	public String getTextAlert(String element) {
		getWebElement(element).click();
		waitForAlert();
		String textPop = getAlert().getText();
		getAlert().accept();
		return textPop;
	}
	
	public String getTextAlert() {
		waitForAlert();
		String textPop = getAlert().getText();
		getAlert().accept();
		return textPop;
	}

	public String getValueField(String element) {
		return element(getWebElement(element)).getValue();
	}
	
	public void clickOptionItem(String listOption, String element) {

		WebElement select = element(getWebElement(element));
		java.util.List<WebElement> list = select.findElements(By.tagName("option"));
		String Data[] = listOption.split(",");

		for (int i = 0; i < Data.length; i++) {
			for (int j = 0; j < list.size(); j++) {
				String str = list.get(j).getText();
				if (str.equalsIgnoreCase(Data[i])) {
					select.sendKeys(Keys.CONTROL);
					list.get(j).click();
					break;
				}
			} // j
		} // i
	}
	
	public void selectValueInListBox(String valueOption, String element) {
		element(getWebElement(element)).selectByValue(valueOption);
	}

	public void selectIndexInListBox(int indexOption, String element) {
		element(getWebElement(element)).selectByIndex(indexOption);
	}
	
	public String isOptionSelected(String element) {
		return element(getWebElement(element)).getSelectedVisibleTextValue();
	}
	
	public void clickElementAt(String element, int xOffset, int yOffset) {
		WebElement toElement = element(getWebElement(element));
		withAction().moveToElement(toElement, xOffset, yOffset).click().perform();
	}
	
	public void doubleClickElement(String element) {
		WebElement onElement = element(getWebElement(element));
		withAction().doubleClick(onElement).perform();
	}

	public void doubleClickElementAt(String element, int xOffset, int yOffset) {
		WebElement toElement = element(getWebElement(element));
		withAction().moveToElement(toElement, xOffset, yOffset).doubleClick().perform();
	}
	
	public void rightClickElement(String element) {
		WebElement link = element(getWebElement(element));
		withAction().contextClick(link).perform();
	}

	public void rightClickElementAt(String element, int xOffset, int yOffset) {
		WebElement toElement = element(getWebElement(element));
		withAction().moveToElement(toElement, xOffset, yOffset).contextClick().perform();
	}
	
	public void pressItemOnContentMenu(String element, int indexItem) {
		try {
			WebElement link = element(getWebElement(element));
			Actions action = new Actions(getDriver());
			action.moveToElement(link).contextClick().perform();
			for (int i = 1; i <= indexItem; i++) {
				action.sendKeys(Keys.ARROW_DOWN);
			}
			action.sendKeys(Keys.ENTER).perform();
		} catch (MoveTargetOutOfBoundsException e) {
			e.getStackTrace();
		}
	}
	
	public void clickAndHoldElement(String element) {
		WebElement elementToMove = getWebElement(element);
		Actions builder = new Actions(getDriver());
		Action drag = builder.clickAndHold(elementToMove).build();
		drag.perform();
	}

	public void moveMouseToElement(String element) {
		WebElement moveToElement = getWebElement(element);

		Actions builder = new Actions(getDriver());
		Action dragAndDrop = builder.moveToElement(moveToElement).release(moveToElement).build();
		dragAndDrop.perform();
	}

	public void moveMouseToElementAt(String element, int xOffset, int yOffset) {
		WebElement moveToElement = getWebElement(element);

		Actions builder = new Actions(getDriver());
		Action dragAndDrop = builder.moveToElement(moveToElement, xOffset, yOffset).release(moveToElement).build();
		dragAndDrop.perform();
	}
	
	public void drag_the_and_drop_to_the(String resouce, String dest) {

		WebElement elementToMove = getWebElement(resouce);
		Actions builder = new Actions(getDriver());
		Action drag = builder.clickAndHold(elementToMove).build();
		drag.perform();

		WebElement moveToElement = getWebElement(dest);
		Actions builder2 = new Actions(getDriver());
		Action dragAndDrop = builder2.moveToElement(moveToElement).release(moveToElement).build();
		dragAndDrop.perform();
	}
	
	public void drag_the_and_drop_html5(String resouce, String des, int xOffset, int yOffset) throws AWTException {

		WebElement dragFrom = getWebElement(resouce);
		WebElement dragTo = getWebElement(des);

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension fromSize = dragFrom.getSize();
		Dimension toSize = dragTo.getSize();

		// Get center distance
		int xCentreFrom = fromSize.width / 2;
		int yCentreFrom = fromSize.height / 2;
		int xCentreTo = toSize.width / 2;
		int yCentreTo = toSize.height / 2;

		Point toLocation = dragTo.getLocation();
		Point fromLocation = dragFrom.getLocation();
		System.out.println(fromLocation.toString());

		// Make Mouse coordinate center of element
		toLocation.x += xOffset + xCentreTo;
		toLocation.y += yOffset + yCentreTo;
		fromLocation.x += xOffset + xCentreFrom;
		fromLocation.y += yOffset + yCentreFrom;

		System.out.println(fromLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(fromLocation.x, fromLocation.y);
		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((toLocation.x - fromLocation.x) / 2) + fromLocation.x,
				((toLocation.y - fromLocation.y) / 2) + fromLocation.y);

		// Move to final position
		robot.mouseMove(toLocation.x, toLocation.y);
		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void checkBox(String element) {
		setCheckbox(getWebElement(element), true);
	}

	public void uncheckBox(String element) {
		setCheckbox(getWebElement(element), false);
	}
	
	public void navigate_to(String url) {
		getDriver().navigate().to(url);
	}

}