package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPageFactory {

	WebDriver driver;

	@FindBy(id = "user-name")
	WebElement txt_usernmae;

	@FindBy(id = "password")
	WebElement txt_password; 

	@FindBy(id = "login-button")
	WebElement btn_login;

	public void enterUsername(String username) {
		txt_usernmae.sendKeys(username);

	}

	public void enterPassword(String password) {
		txt_password.sendKeys(password);
	}

	public void clickLoginbtn() {
		btn_login.click();
	}

	public loginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
