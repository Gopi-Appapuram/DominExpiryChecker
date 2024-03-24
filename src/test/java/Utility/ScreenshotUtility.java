package Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ScreenshotUtility {

	private WebDriver driver;


	public ScreenshotUtility(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void takeScreenshot(String fileName) {
		try {
			//Capture screenshot of the current page
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File("D:\\Cucumber_Projects\\BDD_Framework\\ScreenShots\\" + fileName + ".png"));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}
	
	public void takeScreenshotOfElement(WebElement element, String fileName) {
        try {
            // Capture screenshot of the specific element
            File screenshot = element.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Exception while taking element screenshot: " + e.getMessage());
        }
    }
	
	public void takeScreenshotOfElements(List<WebElement> element, String fileName) {
        try {
            // Capture screenshot of the specific element
            File screenshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Exception while taking element screenshot: " + e.getMessage());
        }
    }

	
}


