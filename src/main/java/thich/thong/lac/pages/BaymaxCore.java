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
import java.util.ArrayList;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.thucydides.core.pages.PageObject;

public class BaymaxCore extends PageObject {
	
	String defaultWindow = "";
	
	private  WebElement getWebElement(String target) {
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
}