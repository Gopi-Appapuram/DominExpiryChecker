package Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WindowHangels {
	private WebDriver driver;

	public WindowHangels(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void switchToTab(int i) {
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(i));
	}

	public void closeAndSwitchToTab(int i) {
		driver.close();
		ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(i));
	}

	public void closeAllTabs() throws Exception {
		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();
		// Iterate through each handle and close the window
		for (String handle : allWindowHandles) {
			driver.switchTo().window(handle);
			driver.close();
			Thread.sleep(2500);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		}
	}

	/** Switches user to window of user's choice */
	public void switchToWindow(String windowTitle) {
		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();
		// iterate through open tabs. If the title of the page is contained in the tab,
		// switch to it.
		for (String windowHandle : allWindowHandles) {
			String title = driver.getTitle();
			driver.switchTo().window(windowHandle);
			if (title.equalsIgnoreCase(windowTitle)) {
				break;
			}
		}
	}

//    public void switchToNextTab() {
//        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tab.get(1));
//    }
//    
//    public void closeAndSwitchToNextTab() {
//        driver.close();
//        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tab.get(1));
//    }
//
//    public void switchToPreviousTab() {
//        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tab.get(0));
//    }
//
//    public void closeTabAndReturn() {
//        driver.close();
//        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tab.get(0));
//    }
//
//    public void switchToPreviousTabAndClose() {
//        ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
//        driver.switchTo().window(tab.get(1));
//        driver.close();
//    }

}
