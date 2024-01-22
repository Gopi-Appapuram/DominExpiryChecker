//package StepDefinations;
//
//import java.time.Duration;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import pageObjects.loginPage;
//
//public class POMLoginStepDefination {
//	
//	WebDriver driver;
//	loginPage login;
//	@Given("User is on Login page")
//	public void user_is_on_login_page() {
//	    // Write code here that turns the phrase above into concrete actions
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//	    driver.get("https://www.saucedemo.com/");
//	    driver.manage().window().maximize();
//
//	}
//
//	
//	@When("User enters valid {string} and {string}")
//	public void user_enters_valid_and(String username, String password) throws InterruptedException {
//		
//		login = new loginPage(driver);
//		login.enterUsername(username);
//		login.enterPassword(password);
////	     Write code here that turns the phrase above into concrete actions
////	    driver.findElement(By.id("user-name")).sendKeys(username);
////	    driver.findElement(By.id("password")).sendKeys(password);
////	    Thread.sleep(2000);
//
//	}
//
//	@And("Clicks on Login Button")
//	public void clicks_on_login_button() {
//	   login.clickLogin();
////	   driver.findElement(By.id("login-button")).click();
//	}
//
//	@Then("User is navigated to Home Page")
//	public void user_is_navigated_to_home_page() {
//		login.isLabbleDisplayed();
////	    Assert.assertTrue(driver.findElements(By.xpath("//div[@class='app_logo']")).size()>0,"User has been navigated to the home page");
//	}
//
//	@Then("Close the browser")
//	public void close_the_browser() {
//	    driver.quit();
//	}
//}
