package pageFactory;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.ExcelUtility;
import Utility.SeleniumHighlighterUtility;

public class Domain_Expiration_Checker_Home_Page {

	WebDriver driver;
	SeleniumHighlighterUtility highlighter;
	ExcelUtility excel;

	// Academy xpath
//	@FindBy(xpath = "//input[@id='search']")
//	WebElement searchInput;

//	@FindBy(xpath = "//button[@type='submit'][1]")
//	WebElement searchButton; 

	// Xpath For Myntra

	@FindBy(xpath = "//input[@id='dc-input']")
	WebElement searchInputBox;

	@FindBy(xpath = "//button[@id='dc-submit']")
	WebElement searchButton;

	@FindBys(@FindBy(xpath = "//p[@class='dc-status']/child::span[contains(@class,'dc-status')]"))
	List<WebElement> dominStatus;
	
	@FindBy(xpath = "//p[@class='dc-status dc-status-error']")
	WebElement unavilableDomin;
	
	@FindBys(@FindBy(xpath = "(//div[@class='dc-result-items']/child::p)"))
	List<WebElement> dominStatusResults;

	@FindBys(@FindBy(xpath = "(//div[@class='dc-result-items']//p[contains(@id,'dc-result')]//span[@class='dc-result-item-value'])"))
	List<WebElement> dateStatus;

	@FindBy(xpath = "//div[@id='preload-sk-circle']")
	WebElement lodder;

	public Domain_Expiration_Checker_Home_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.highlighter = new SeleniumHighlighterUtility(driver);
	}

	public void sendDominURL(String dominurl) {
		highlighter.highlightElement(searchInputBox);
		searchInputBox.clear();
		searchInputBox.sendKeys(dominurl);
	}

	public void clickSearchBtn() {
		try {
			highlighter.highlightElement(searchButton);
			searchButton.click();
			Thread.sleep(1500);
			if (lodder.isDisplayed()) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.invisibilityOf(lodder));
			}
		} catch (Exception e) {
			System.err.println("Error clicking search button: " + e.getMessage());
		}

	}

	public String dominStatus() {
	    String DominStatus = null;
	    if (dominStatus != null && dominStatus.size() > 0) {
	        String statusText = dominStatus.get(0).getText();
	        if (statusText.equals("Registered")) {
	            DominStatus = statusText;
	        } else if (statusText.equals("Available")) {
	            DominStatus = statusText;
	        } else {
	            DominStatus = "Unavailable_domain";
	        }
	    } else {
	        DominStatus = "Unavailable_domain";
	    }
	    return DominStatus;
	}

	
	public String expiryDate() {
		String ExpiryDate = null;
//		String name = dominStatusResults.get(1).getText();
//		System.out.println(name);
		for (int i = 0; i <= dateStatus.size(); i++) {
			if (dominStatusResults.get(i).getText().startsWith("Expires")) {
				ExpiryDate = dateStatus.get(i).getText();
				break;
			}
		}
		// System.out.println(dominStatusResults.get(0).getText().contains("Expires"));
		return ExpiryDate;
	}

	public String createdOn() {

		String CreatedDate = null;
		for (int i = 0; i <= dateStatus.size(); i++) {
			if (dominStatusResults.get(i).getText().startsWith("Created")) {
				CreatedDate = dateStatus.get(i).getText();
				break;
			}
		}
		// System.out.println(dominStatusResults.get(i).getText().contains("Created"));
		return CreatedDate;
	}

	public String updateOn() {
		String UpdatedOn = null;
		for (int i = 0; i <= dateStatus.size(); i++) {
			if (dominStatusResults.get(i).getText().startsWith("Updated")) {
				UpdatedOn = dateStatus.get(i).getText();
				break;
			}
		}
		// System.out.println(dominStatusResults.get(2).getText().contains("Created"));
		return UpdatedOn;
	}

}
