package StepDefinations;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Utility.ScreenshotUtility;
import Utility.SeleniumHighlighterUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.AcademyHomePage;
import pageFactory.CartPage;
import pageFactory.ProductDetailsPage;
import pageFactory.SearchResultPage;
import pageFactory.WebDriverManager;

public class SearchShirtsStepDefination {

	WebDriver driver;
	AcademyHomePage homepage;
	ProductDetailsPage ProductDetails;
	SearchResultPage searchPage;
	SeleniumHighlighterUtility highlighter;
	ScreenshotUtility Screenshot;
	CartPage cart;

//	public  SearchShirtsStepDefination() {
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		driver.manage().window().maximize();
//	}

	@Before
	public void setUp() {
		driver = WebDriverManager.getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Screenshot = new ScreenshotUtility(driver);
		homepage = new AcademyHomePage(driver);
		searchPage = new SearchResultPage(driver);
		ProductDetails = new ProductDetailsPage(driver);
		cart = new CartPage(driver);
		
	}

	@Given("I am on the Academy website")
	public void iAmOnAcademyWebsite() {
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		driver.manage().window().maximize();
//		// driver.get("https://www.academy.com/");
		driver.get("https://www.myntra.com/");

	}

	@When("I search for {string} in the search textbox")
	public void iSearchForInSearchTextbox(String productname) {
		//homepage = new AcademyHomePage(driver);
		homepage.searchForProduct(productname);
		homepage.clicksearchbtn();

	}

	@Then("I can see list of product list page")
	public void i_can_see_list_of_product_list() {
		//searchPage = new SearchResultPage(driver);
		searchPage.isProdListDisplayed();
	}

	@Given("I can see list of products")
	public void i_can_see_list_of_products() {
		//searchPage = new SearchResultPage(driver);
		searchPage.isProdListDisplayed();

	}

	@When("I applied any filter for price range")
	public void i_applied_any_filter_for_price_range() {
		//searchPage = new SearchResultPage(driver);
		searchPage.selectPriceRange();
		Screenshot.takeScreenshot("Pricerange");
	}

	@Then("I can see only products within the specified price range")
	public void i_can_see_only_products_within_the_specified_price_range() throws Exception {
		//searchPage = new SearchResultPage(driver);
		searchPage.FiltersChecked();
		Screenshot.takeScreenshot("Filters_checked");
	}

	@And("I select a product with any index from the list")
	public void iSelectAProductFromList() throws Exception {

		//searchPage = new SearchResultPage(driver);
		searchPage.clickOnAnyItem();
		Screenshot.takeScreenshot("SelectedItem");
		// Get the current window handle
		String mainWindowHandle = driver.getWindowHandle();
		// Switch to the new window
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

	}

	@When("I am on the product details page")
	public void i_am_on_the_product_details_page() {
		//ProductDetails = new ProductDetailsPage(driver);
		System.out.println("You are on " + ProductDetails.productDetaiMetaTitle() + "page.");
		//Screenshot.takeScreenshot("Product_Meta_Title");
	}

	@Then("I should see the product name and price")
	public void iShouldSeeProductNameAndPrice() throws Exception {
//		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tabs.get(1));
//		Set<String> windowhandels = driver.getWindowHandles();
//		List<String> tabs= new ArrayList <String>(windowhandels);
//	    driver.switchTo().window(tabs.get(1));
		//ProductDetails = new ProductDetailsPage(driver);
		String Brand = ProductDetails.getProductBrand();
		String Name = ProductDetails.getProductName();
		String Price = ProductDetails.getProductPrice();
		// To Check weather the Product Name is displayed
//		Assert.assertEquals(true, ProductDetails.ProdNameisDisplayed());
//		Assert.assertEquals(true, ProductDetails.ProdPriceisDisplayed());	
		// Assertion to check if the product name is displayed
		boolean isProductNameDisplayed = ProductDetails.ProdNameisDisplayed();
		Assert.assertTrue(isProductNameDisplayed, "Product name is not displayed.");

		// Assertion to check if the product price is displayed
		boolean isProductPriceDisplayed = ProductDetails.ProdPriceisDisplayed();
		Assert.assertTrue(isProductPriceDisplayed, "Product price is not displayed.");
		
		// Print Product Name and Price
		System.out.println("Product Brand: " + Brand);
		System.out.println("Product Name: " + Name);
		System.out.println("Product Price: " + Price);
		// Take Screen Shot of Producer details page
		Screenshot.takeScreenshot("Product_Details");
	}

	@Given("I selected a product variant from the list")
	public void i_selected_a_product_variant_from_the_list() throws Exception {
		try {
			ProductDetails.selectProductColour();
		} catch (Exception e) {
			System.out.println("No Colour variants for this product found");
		}
		try {
			ProductDetails.selectProductSize();
		} catch (Exception e) {
			System.out.println("No product Size is avilable");
		}
//		ProductDetails.selectProductColour();
//		ProductDetails.selectProductSize();
	}

	@When("I add the item to the cart")
	public void i_add_the_item_to_the_cart() throws Exception {
		ProductDetails.addToBag();
	}
	
	@And("I am on cart page")
	public void i_am_on_cart_page() throws Exception {
		ProductDetails.clickCartIcon();
		Screenshot.takeScreenshot("Cart_Page");
	}

	@Then("I can see the item in the cart")
	public void i_can_see_the_item_in_the_cart() {
		cart.isCartEmpty();
	}

//	@Given("I can see the items in the cart")
//	public void i_can_see_items_in_the_cart() {
//		
//	}

	@When("User removes the items from the cart")
	public void user_removes_the_items_from_the_cart() {
		cart.removeAllItemsFromCart();
	}

	@Then("User should see an empty cart")
	public void user_should_see_an_empty_cart() {
		cart.isCartEmpty();
	}

	@Then("Close the browser")
	public void close_the_browser() {
		driver.close();
		driver.quit();
	}

}
