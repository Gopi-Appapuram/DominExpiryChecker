package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.SeleniumHighlighterUtility;

public class AcademyHomePage {

	WebDriver driver;
	SeleniumHighlighterUtility highlighter;

	// Acadamy xpath
//	@FindBy(xpath = "//input[@id='search']")
//	WebElement searchInput;

//	@FindBy(xpath = "//button[@type='submit'][1]")
//	WebElement searchButton; 

	// Xpath For Myntra

	@FindBy(xpath = "//input[contains(@placeholder,'Search for products')]")
	WebElement searchInput;

	@FindBy(xpath = "//a[@class='desktop-submit']")
	WebElement searchButton;

	public void searchForProduct(String productname) {
		highlighter.highlightElement(searchInput);
		searchInput.sendKeys(productname);

	}

	public void clicksearchbtn() {
		highlighter.highlightElement(searchButton);
		searchButton.click();
	}

	public AcademyHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.highlighter = new SeleniumHighlighterUtility(driver);
	}
}
