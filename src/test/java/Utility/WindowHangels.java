package Utility;

import java.util.ArrayList;

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
