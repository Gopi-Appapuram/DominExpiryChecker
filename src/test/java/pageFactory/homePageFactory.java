package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePageFactory {

	WebDriver driver;
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	WebElement lbl_cart;

	public void isCartDisplayed() {
		lbl_cart.isDisplayed();
	}

	public homePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

} 
