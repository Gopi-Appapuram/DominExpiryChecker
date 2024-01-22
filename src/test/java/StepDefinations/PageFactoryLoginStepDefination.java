package StepDefinations;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.homePageFactory;
import pageFactory.loginPageFactory;

public class PageFactoryLoginStepDefination {

	WebDriver driver;
	loginPageFactory login;
	homePageFactory homePage;

	@Given("User is on Login page")
	public void user_is_on_login_page() {
		// Write code here that turns the phrase above into concrete actions
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();

	}

	@When("User enters valid {string} and {string}")
	public void user_enters_valid_and(String username, String password) {
		login = new loginPageFactory(driver);
		login.enterUsername(username);
		login.enterPassword(password);

	}

	@And("Clicks on Login Button")
	public void clicks_on_login_button() {
		login.clickLoginbtn();
	}

	@Then("User is navigated to Home Page")
	public void user_is_navigated_to_home_page() {
		// Assert.assertTrue(driver.findElements(By.xpath("//div[@class='app_logo']")).size()>0,"User
		// has been navigated to the home page");
		homePage = new homePageFactory(driver);
		homePage.isCartDisplayed();
	}

	@Then("Close the browser")
	public void close_the_browser() {
		driver.quit();
	}
}
