package StepDefinations;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utility.ScreenshotUtility;
import Utility.SeleniumHighlighterUtility;
import Utility.WindowHangels;
import io.cucumber.java.Before;
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
	WindowHangels handels;

	@Before
	public void setUp() {
		driver = WebDriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Screenshot = new ScreenshotUtility(driver);
		homepage = new AcademyHomePage(driver);
		searchPage = new SearchResultPage(driver);
		ProductDetails = new ProductDetailsPage(driver);
		cart = new CartPage(driver);
		handels = new WindowHangels(driver);

	}

	@Given("I am on the Academy website")
	public void iAmOnAcademyWebsite() {
		driver.manage().deleteAllCookies();
		driver.get("https://www.myntra.com/");

	}

	@When("I search for {string} in the search textbox")
	public void iSearchForInSearchTextbox(String productname) {
		homepage.searchForProduct(productname);
		homepage.clicksearchbtn();

	}

	@Then("I can see list of product list page")
	public void i_can_see_list_of_product_list() {
		searchPage.isProdListDisplayed();
	}

	@Given("I can see list of products")
	public void i_can_see_list_of_products() {
		searchPage.isProdListDisplayed();

	}

	@When("I applied any filter for price range")
	public void i_applied_any_filter_for_price_range() {
		searchPage.selectPriceRange();
		Screenshot.takeScreenshot("Pricerange");
	}

	@Then("I can see only products within the specified price range")
	public void i_can_see_only_products_within_the_specified_price_range() throws Exception {
		searchPage.FiltersChecked();
		Screenshot.takeScreenshot("Filters_checked");
	}

	@And("I select a product with any index from the list")
	public void iSelectAProductFromList() throws Exception {
		searchPage.clickOnAnyItem();
		//handels.switchToNextTab();
		Screenshot.takeScreenshot("SelectedItem");

	}

	@When("I am on the product details page")
	public void i_am_on_the_product_details_page() {
		System.out.println("You are on " + ProductDetails.productDetaiMetaTitle() + "page.");
	}

	@Then("I should see the product name and price")
	public void iShouldSeeProductNameAndPrice() throws Exception {
//		Set<String> windowhandels = driver.getWindowHandles();
//		List<String> tabs= new ArrayList <String>(windowhandels);
//	    driver.switchTo().window(tabs.get(1));
		handels.switchToTab(1);
		String Brand = ProductDetails.getProductBrand();
		String Name = ProductDetails.getProductName();
		String Price = ProductDetails.getProductPrice();
		boolean isProductNameDisplayed = ProductDetails.ProdNameisDisplayed();
		Assert.assertTrue(isProductNameDisplayed, "Product name is not displayed.");
		boolean isProductPriceDisplayed = ProductDetails.ProdPriceisDisplayed();
		Assert.assertTrue(isProductPriceDisplayed, "Product price is not displayed.");
		System.out.println("Product Brand: " + Brand);
		System.out.println("Product Name: " + Name);
		System.out.println("Product Price: " + Price);
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

	@When("Navigate to search details page")
	public void navigate_to_search_page() throws Exception {
//		String mainWindowHandle = driver.getWindowHandle();
//		for (String windowHandle : driver.getWindowHandles()) {
//			if (!windowHandle.equals(mainWindowHandle)) {
//				driver.switchTo().window(windowHandle);
//				break;
//			}
//		}
		handels.switchToTab(0);
		
	}

	@When("I can see list of products and names")
	public void i_added_the_product_to_cart() {
		handels.switchToTab(2);
		boolean isProductNameDisplayed = ProductDetails.ProdNameisDisplayed();
		Assert.assertTrue(isProductNameDisplayed, "Product name is not displayed.");
		boolean isProductPriceDisplayed = ProductDetails.ProdPriceisDisplayed();
		Assert.assertTrue(isProductPriceDisplayed, "Product price is not displayed.");
		System.out.println("Product Brand: " + ProductDetails.getProductBrand());
		System.out.println("Product Name: " + ProductDetails.getProductName());
		System.out.println("Product Price: " + ProductDetails.getProductPrice());
	}

	@When("User removes the items from the cart")
	public void user_removes_the_items_from_the_cart() throws Exception {
		cart.selectAllItemsFromCart();
		cart.removeAllItems();
	}

	@Then("User should see an empty cart")
	public void user_should_see_an_empty_cart() {
		cart.isCartEmpty();
		Screenshot.takeScreenshot("Empty_Cart_Page");
	}

	@Then("Close the browser")
	public void close_the_browser() {
		driver.close();
		driver.quit();
	}

}
