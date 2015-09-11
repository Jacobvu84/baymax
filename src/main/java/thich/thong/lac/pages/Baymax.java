package thich.thong.lac.pages;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Baymax{
	
	protected static Screen _screen = new Screen();
	
	static long timeout = 30;
	
	public static void clear(String locator) {
		try {
			_screen.wait(locator, timeout);
			click(locator);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		_screen.type("a", Key.CTRL);
		_screen.type(Key.BACKSPACE);
	}

	public static void scroller(String scroller, int dy) throws FindFailed,
			AWTException {

		_screen.mouseMove(scroller);
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();

		Robot r = new Robot();
		_screen.mouseDown(Button.LEFT);
		r.mouseMove(x, y + dy);
		_screen.mouseUp(Button.LEFT);
	}

	public static void reSize(String img, int x, int y, int dx, int dy) throws AWTException{
		/*
		 * Move mouse into pattern at point(x,y) then move this pattern into
		 * another point(x + dx, y + dy)
		 */
		mouseMoveAt(img, x, y);
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int mouseOX = (int) b.getX();
		int mouseOY = (int) b.getY();

		Robot r = new Robot();
		_screen.mouseDown(Button.LEFT);
		r.mouseMove(mouseOX + dx, mouseOY + dy);
		_screen.mouseUp(Button.LEFT);
	}

	// Action groups
	public static void select(String locator, String label, int index) {
		Pattern pattern = new Pattern(label);
		try {
			_screen.click(locator);
			_screen.click(pattern.targetOffset(0, index));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void select(String locator, String label) {

		try {
			_screen.click(locator);
			_screen.click(label);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void sendKey(String label) {
		_screen.type(label);
	}
	
	public static void sendKey(String locator, String label) {
		_screen.type(locator, label);
	}
	
	public static void type(String value, String keyCode) {
		_screen.type(value, keyCode);
	}
	
	
	public static void mouseMove(String img) {
		Pattern pattern = new Pattern(img);
		
		try {
			_screen.mouseMove(pattern.similar((float) 0.90));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static boolean verifyNotImage(String img, float similarity){
		Pattern pattern = new Pattern(img);
		Match result = _screen.exists(pattern.similar(similarity));

		if (result != null) {
			return false;
		}
		return true;
	}

	public static void dragDrop(String source, String destination) {
		try {
			_screen.dragDrop(source, destination);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	public static void dragDrop(String source, String destination, int x, int y) {
		Pattern pattern = new Pattern(destination);
		try {
			_screen.dragDrop(source, pattern.targetOffset(x, y));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	public static void dragDrop(String source, int x, int y, String destination) {
		Pattern pattern = new Pattern(source);
		try {
			_screen.dragDrop(pattern.targetOffset(x, y), destination);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void rightClickAt(String region, int x, int y) {
		Pattern pattern = new Pattern(region);
		try {
			_screen.rightClick(pattern.targetOffset(x, y));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	public static void rightClick(String region) {
		try {
			_screen.rightClick(region);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	public static void mouseMoveAt(String region, int x, int y) {
		Pattern pattern = new Pattern(region);
		try {
			_screen.mouseMove(pattern.targetOffset(x, y));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void mouseDown(int keyButton) {
		_screen.mouseDown(keyButton);
	}

	public static void mouseUp(int keyButton) {
		_screen.mouseUp(keyButton);
	}

	public static void dropAt(String region) {
		try {
			_screen.dropAt(region);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void click(String region) {
		try {
			_screen.click(region);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void clickAt(String region, int x, int y) {
		Pattern pattern = new Pattern(region);
		try {
			_screen.click(pattern.targetOffset(x, y).similar((float) 0.80));
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void wait(String region, int timeout) {
		try {
			_screen.wait(region, timeout);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	public static void paste(String value) {
		_screen.paste(value);
	}

	public static void doubleClick(String region) {
		try {
			_screen.doubleClick(region);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	// Verify
	public static boolean assertImage(String img, float similarity)
				throws Exception {

			Pattern pattern = new Pattern(img);
			Match result = _screen.exists(pattern.similar(similarity));

			if (result == null) {
				return false;
			}
			return true;
		}
	
	public static Match exists(String region, float similarity) {
		Pattern pattern = new Pattern(region);
		return _screen.exists(pattern.similar((float) similarity));
	}
	
	public static Match exists(String region) {
		return _screen.exists(region);
	}

}
