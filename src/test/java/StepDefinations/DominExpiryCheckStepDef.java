package StepDefinations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utility.ExcelUtility;
import Utility.ScreenshotUtility;
import Utility.SeleniumHighlighterUtility;
import Utility.WindowHangels;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.Domain_Expiration_Checker_Home_Page;
import pageFactory.WebDriverManager;
import java.util.Date;
import java.util.Locale;

public class DominExpiryCheckStepDef {
	WebDriver driver;
	SeleniumHighlighterUtility highlighter;
	ScreenshotUtility Screenshot;
	WindowHangels handels;
	ExcelUtility excel;
	Domain_Expiration_Checker_Home_Page homePage;

	@Before
	public void setUp() {
		driver = WebDriverManager.getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Screenshot = new ScreenshotUtility(driver);
		homePage = new Domain_Expiration_Checker_Home_Page(driver);
		handels = new WindowHangels(driver);
		String Excel_Path = "D:\\ESoft_Solutions\\AutomationPractice\\DomineNameExpiryCheck\\domains.xlsx";
		excel = new ExcelUtility(Excel_Path);
	}

	@Given("I am on {string} page")
	public void i_am_on_myntra_website(String Url) {
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@When("I get the URLs from the Excel")
	public void i_get_the_url_from_the_excel() {
		// excel.setSheet("Sheet2"); // Set the Excel sheet name
	}

	@When("I enter the URL in the search box")
	public void i_enter_the_url_in_the_search_box() {
		excel.setSheet("Sheet4");
		int rowCount = excel.rowCount();
		for (int i = 2; i <= rowCount; i++) {
			String urldata = excel.readData(i, 1);
			System.out.println(urldata);
			driver.manage().deleteAllCookies();
			homePage.sendDominURL(urldata);
			homePage.clickSearchBtn();
			String DominStatus = homePage.dominStatus();
			if (DominStatus.equals("Registered")) {
				Date currentDate = new Date();
				// Create a SimpleDateFormat object with the desired pattern
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss", Locale.ENGLISH);
				// Format the current time
				String todaysDate = dateFormat.format(currentDate);
				// Get expiry date from the domin
				String expiryDate = homePage.expiryDate();
				String Updated_date = homePage.updateOn();
				String created_date = homePage.createdOn();

				try {
					// Parse the two dates
					Date date1 = dateFormat.parse(todaysDate);
					Date date2 = dateFormat.parse(expiryDate);

					// Calculate the difference in milliseconds
					long differenceMillis = date2.getTime() - date1.getTime();

					// Convert milliseconds to days
					Long differenceDays = TimeUnit.MILLISECONDS.toDays(differenceMillis);

					// Convert difference in months to a string
					Long differenceMonths = differenceDays / 30;
					String differenceMonthsStr = String.valueOf(differenceMonths);
					// Print the difference in days
					System.out.println("Difference in months: " + differenceMonths);
					String[] DominExpiryData = { urldata, DominStatus, created_date, Updated_date, expiryDate,
							differenceMonthsStr };

					if (differenceMonths <= 1 && differenceMonths > 0) {
						excel.setSheet("Updated_Sheet");
						excel.writeData(0, DominExpiryData, "Light_Orange");
					} else if (differenceMonths < 0) {
						excel.setSheet("Updated_Sheet");
						excel.writeData(0, DominExpiryData, "Red");
					} else if (differenceMonths < 6 && differenceMonths > 1) {
						excel.setSheet("Updated_Sheet");
						excel.writeData(0, DominExpiryData, "Yellow");
					} else if (differenceMonths >= 6) {
						excel.setSheet("Updated_Sheet");
						excel.writeData(0, DominExpiryData, "Green");
					} else {
						System.out.println("invalid data");
						// excel.close();
					}

					/*
					 * String[] DominExpiryData = { urldata, homePage.createdOn(),
					 * homePage.updateOn(), expiryDate, differenceMonthsStr, };
					 */

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				String[] DominAvilabityData = { urldata, DominStatus };
				excel.setSheet("Updated_Sheet");
				excel.writeData(0, DominAvilabityData, "white");

			}
			// Set the sheet back to "Sheet2" after processing all URLs in "Sheet2"
			excel.setSheet("Sheet4");
		}

	}

	@When("I get the expiry date of the domain expiry date is one month ahead I set the cell color as {string}")
	public void i_get_the_expiry_date_of_the_domain_expiry_date_is_one_month_ahead_i_set_the_cell_color_as(
			String Color) {
//		System.out.println(homePage.expiryDate());
//		// Convert days to months
//		differenceMonths = differenceDays / 30;
//		System.out.println("Diffence in Monts: " + differenceMonths);
//		if (differenceMonths <= 1 && differenceMonths >= 1) {
//			excel.setSheet("Sheet3");
//			excel.writeData(0, "DominExpiryData", Color);
//		}
	}

	@When("I get the expiry date of the domain exceeds todays date I set the cell color as {string}")
	public void i_get_the_expiry_date_of_the_domain_exceeds_todays_date_i_set_the_cell_color_as(String Color) {
//		System.out.println(homePage.expiryDate());
//		// Convert days to months
//		differenceMonths = differenceDays / 30;
//		System.out.println("Diffence in Monts: " + differenceMonths);
//		if (differenceMonths < 0) {
//			excel.setSheet("Sheet3");
//			excel.writeData(0, "DominExpiryData", Color);
//		}
	}

	@When("I get the expiry date of the domain expiry date is six months ahead I set the cell color as {string}")
	public void i_get_the_expiry_date_of_the_domain_expiry_date_is_six_months_ahead_i_set_the_cell_color_as(
			String Color) {
//		System.out.println(homePage.expiryDate());
//		// Convert days to months
//		differenceMonths = differenceDays / 30;
//		System.out.println("Diffence in Monts: " + differenceMonths);
//		if (differenceMonths < 6 && differenceMonths > 1 ) {
//			excel.setSheet("Sheet3");
//			excel.writeData(0, "DominExpiryData", Color);
//		}
	}

	@When("I get the expiry date of the domain expiry date is more than six months ahead I set the cell color as {string}")
	public void i_get_the_expiry_date_of_the_domain_expiry_date_is_more_than_six_months_ahead_i_set_the_cell_color_as(
			String Color) {
//		System.out.println(homePage.expiryDate());
//		// Convert days to months
//		differenceMonths = differenceDays / 30;
//		System.out.println("Diffence in Monts: " + differenceMonths);
//		if (differenceMonths >= 6) {
//			excel.setSheet("Sheet3");
//			excel.writeData(0, "DominExpiryData", Color);
//		}
	}

	@Then("I close the browser")
	public void i_close_the_browser() {
		driver.close();
		driver.quit();

	}

}
