package StepDefinations;

import java.time.Duration;

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
	
	String HomePageTitle;
	String SearchPageTitle;
	String ProductDetailsPageTitle;
	String CartPageTitle;
	String CheckOutPageTitle;

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

	@Given("I am on the Myntra website")
	public void i_am_on_myntra_website() {
		driver.manage().deleteAllCookies();
		driver.get("https://www.myntra.com/");

	}

	@When("I search for {string} in the search textbox")
	public void i_search_for_in_search_textbox(String productname) {
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
	public void i_select_a_product_from_list() throws Exception {
		searchPage.clickOnAnyItem();
		ProductDetailsPageTitle = driver.getTitle();
		// handels.switchToNextTab();
		Screenshot.takeScreenshot("SelectedItem");

	}

	@When("I am on the product details page")
	public void i_am_on_the_product_details_page() {
		System.out.println("You are on " + ProductDetails.productDetaiMetaTitle() + "page.");
	}
	
	@Then("I should see the product name and price")
	public void i_should_see_product_name_and_price() throws Exception {
		//ProductDetailsPageTitle = driver.getTitle();
		System.out.println(ProductDetailsPageTitle);
		handels.switchToWindow(ProductDetailsPageTitle);
		// This is used to check whether the product name and price is displayed or not
		boolean isProductNameDisplayed = ProductDetails.ProdNameisDisplayed();
		Assert.assertTrue(isProductNameDisplayed, "Product name is not displayed.");
		boolean isProductPriceDisplayed = ProductDetails.ProdPriceisDisplayed();
		Assert.assertTrue(isProductPriceDisplayed, "Product price is not displayed.");
		// This is used to print the product brand, name, and price
		System.out.println("Product Brand: " + ProductDetails.getProductBrand());
		System.out.println("Product Name: " + ProductDetails.getProductName());
		System.out.println("Product Price: " + ProductDetails.getProductPrice());
	}

	@Given("I selected a product variant from the list")
	public void i_selected_a_product_variant_from_the_list() throws Exception {
		// Try to select the product color and variant
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
		// This method is used to add the product to cart by clicking on ADD TO CART
		ProductDetails.addToBag();
	}
	
	@And("I am on cart page")
	public void i_am_on_cart_page() throws Exception {
		// This method is used to click the cart icon
		ProductDetails.clickCartIcon();
		// This method is used to take the screenshot of the cart page
		Screenshot.takeScreenshot("Cart_Page");
	}

	@Then("I can see the item in the cart")
	public void i_can_see_the_item_in_the_cart() {
		// This method is used to take the check whether the cart is empty or not
		cart.isCartEmpty();
	}

	@When("Navigate to search details page")
	public void navigate_to_search_page() throws Exception {
		// This method is used to switch to main window (myntra.com) or tab with index 0
		//handels.switchToTab(0);
		SearchPageTitle = driver.getTitle();
		handels.switchToWindow(SearchPageTitle);
	}

//	@When("I can see list of products and names")
//	public void i_added_the_product_to_cart() {
//		// This method is used to switch to tab with index 2
//		handels.switchToTab(2);
//		// This is used to check whether the product name and price is displayed or not
//		boolean isProductNameDisplayed = ProductDetails.ProdNameisDisplayed();
//		Assert.assertTrue(isProductNameDisplayed, "Product name is not displayed.");
//		boolean isProductPriceDisplayed = ProductDetails.ProdPriceisDisplayed();
//		Assert.assertTrue(isProductPriceDisplayed, "Product price is not displayed.");
//		// This is used to print the product brand, name, and price
//		System.out.println("Product Brand: " + ProductDetails.getProductBrand());
//		System.out.println("Product Name: " + ProductDetails.getProductName());
//		System.out.println("Product Price: " + ProductDetails.getProductPrice());
//	}
	
	@And("I select another product with any index from the list")
	public void i_Select_Another_Product_From_List() throws Exception {
		searchPage.clickOnAnyItem();
		// handels.switchToNextTab();
		Screenshot.takeScreenshot("SelectedItem");

	}

	@When("User removes the items from the cart")
	public void user_removes_the_items_from_the_cart() throws Exception {
		// This is used to select all the products from the cart
		cart.selectAllItemsFromCart();
		// This is used to remove all items
		cart.removeAllItems();
	}

	@Then("User should see an empty cart")
	public void user_should_see_an_empty_cart() {
		// This is used to check whether the cart is empty or not
		cart.isCartEmpty();
		// This is used to take the empty cart page
		Screenshot.takeScreenshot("Empty_Cart_Page");
	}

	@Then("Close the browser")
	public void close_the_browser() {
		handels.closeAllTabs();
		driver.quit();
	}

}
