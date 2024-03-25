package pageFactory;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import Utility.ScreenshotUtility;
import Utility.ScrollUtility;
import Utility.SeleniumHighlighterUtility;

public class ProductDetailsPage {

	WebDriver driver;
	SeleniumHighlighterUtility highlighter;
	ScreenshotUtility screenshot;
	ScrollUtility scroll;
//	@FindBy(xpath = "//h1[@data-auid='PDP_ProductName']")
//	WebElement productName;
//	
//	@FindBy(xpath = "//div[@data-auid='regPrice'])[1]")
//	WebElement productPrice;
//	

	@FindBy(xpath = "//h1[contains(@class,'pdp-title')]")
	WebElement productBrand;

	@FindBy(xpath = "//h1[contains(@class,'pdp-name')]")
	WebElement productName;

	@FindBy(xpath = "//span[@class='pdp-price']")
	WebElement productPrice;

	@FindBys(@FindBy(xpath = "(//img[@class='colors-image'])"))
	List<WebElement> productColour;

	@FindBys(@FindBy(xpath = "(//button[contains(@class,'size-buttons-size-button size-buttons-size-button-default')])"))
	List<WebElement> productSize;

	@FindBy(xpath = "//div[contains(text(),'ADD TO BAG')]")
	WebElement addToBag;

	@FindBy(xpath = "//p[contains(text(), 'Added to bag')]")
	WebElement alertMessageOfAddToBag;

	@FindBy(xpath = "//span[contains(@class,'desktop-badge')]")
	WebElement nofCartItems;

	@FindBy(xpath = "(//div[@class='desktop-user'])")
	WebElement userIcon;

	@FindBy(xpath = "//div[@class='desktop-wishlist']")
	WebElement wishListIcon;

	@FindBy(xpath = "//span[contains(@class,'desktop-iconBag')]")
	WebElement cartIcon;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.highlighter = new SeleniumHighlighterUtility(driver);
		this.scroll = new ScrollUtility(driver);
	}

	// Returns the Product name
	public String getProductName() {
		highlighter.highlightElement(productName);
		return productName.getText();
	}

	// Returns the product brand
	public String getProductBrand() {
		highlighter.highlightElement(productBrand);
		return productBrand.getText();
	}

	// Returns the Product price
	public String getProductPrice() {
		highlighter.highlightElement(productPrice);
		return productPrice.getText();
	}

	public Boolean ProdPriceisDisplayed() {
		return productPrice.isDisplayed();
	}

	public Boolean ProdNameisDisplayed() {
		return productName.isDisplayed();
	}

	public String productDetaiMetaTitle() {
		String pageTittle = driver.getTitle();
		return pageTittle;

	}

	public void selectProductColour() throws Exception {
		int maxProductColours = productColour.size();
		if (maxProductColours >= 0) {
			Random random = new Random();
			int randomProductColour = random.nextInt(maxProductColours);
			scroll.scrollElementIntoView(productColour.get(randomProductColour));
			highlighter.highlightElement(productColour.get(randomProductColour));
			productColour.get(randomProductColour).click();
			Thread.sleep(5000);
		} else {
			System.out.println("No product colours for the selected product.");
		}

	}

	public void selectProductSize() throws Exception {
		int maxProductSizes = productSize.size();
		Random random = new Random();
		if (maxProductSizes >= 0) {
			int randomProductSize = random.nextInt(maxProductSizes);
			scroll.scrollElementIntoView(productSize.get(randomProductSize));
			highlighter.highlightElement(productSize.get(randomProductSize));
			productSize.get(randomProductSize).click();
			Thread.sleep(5000);
		} else {
			System.out.println("No product Size for this product");
		}

	}

	public void addToBag() throws Exception {
		highlighter.highlightElement(addToBag);
		addToBag.click();
		Thread.sleep(1500);
		if (alertMessageOfAddToBag.getText().equals("Added to bag")) {
			System.out.println("Product added successfully");
		} else {
			System.out.println("Please try again to add the product to the cart");
		}
		Thread.sleep(3000);
//		if (addToBag.getText().equals("GO TO BAG")) {
//			System.out.println("Product added successfully");
//			if (nofCartItems.isDisplayed()) {
//				highlighter.highlightElement(nofCartItems);
//				System.out.println("No.Of Products In Cart: " + nofCartItems.getText() + " Items.");
//			} else {
//				System.out.println("No products in the cart");
//			}
//		}else{
//		System.out.println("Please try again to add the product to the cart");
//		}
	}

//	public void nofItemsInCart() {
//		if (nofCartItems.isDisplayed()) {
//			highlighter.highlightElement(nofCartItems);
//			System.out.println("No.Of Products In Cart: " + nofCartItems.getText() + " Items.");
//		} else {
//			System.out.println("No products in the cart");
//		}
//	}

	public void clickCartIcon() throws Exception {
		highlighter.highlightElement(cartIcon);
		cartIcon.click();
		Thread.sleep(3000);
		if (driver.getTitle().equals("SHOPPING BAG")) {
			System.out.println("You are in CART page");
		} else {
			System.out.println("You are in " + driver.getTitle() + " page\"");
		}
	}

	public void clickWishListIcon() {
		wishListIcon.click();
		if (driver.getTitle().equals("Wishlist")) {
			highlighter.highlightElement(wishListIcon);
			System.out.println("You are in CART page");
		} else {
			System.out.println("You are in " + driver.getTitle() + " page\"");
		}
	}

}
