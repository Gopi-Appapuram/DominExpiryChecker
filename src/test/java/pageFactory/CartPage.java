package pageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import Utility.ScreenshotUtility;
import Utility.ScrollUtility;
import Utility.SeleniumHighlighterUtility;

public class CartPage {

	WebDriver driver;
	SeleniumHighlighterUtility highlighter;
	ScrollUtility scroll;
	ScreenshotUtility screenshot;

	@FindBys(@FindBy(xpath = "(//div[contains(@class,'itemContainer-base-brand')])"))
	List<WebElement> brandNameInCart;

	@FindBys(@FindBy(xpath = "(//body/div[@class='layout']/div[@id='mountRoot']/div[@id='appContent']/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[3]//*[name()='svg'])"))
	List<WebElement> productCheckbox;

	@FindBys(@FindBy(xpath = "(//body/div[@class='layout']/div[@id='mountRoot']/div[@id='appContent']/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[3]//*[name()='svg']//*[name()='path' and contains(@fill,'#000')])"))
	List<WebElement> productCrossMark;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.scroll = new ScrollUtility(driver);
		this.highlighter = new SeleniumHighlighterUtility(driver);
	}

	public void removeAllItemsFromCart() {
		if (productCheckbox.isEmpty()) {
			System.out.println("No items in the cart");
			//screenshot.takeScreenshotOfElements(productCheckbox, "checkbox");
		} else {
			for (int i = 0; i < productCheckbox.size(); i++) {
				if (i % 2 == 0) {
					WebElement checkbox = productCheckbox.get(i);
					scroll.scrollElementIntoView(checkbox);
					// highlighter.highlightElement(checkbox);
					if (checkbox.isSelected()) {
						highlighter.highlightElement(checkbox, "2px Solid black");
					} else {
						highlighter.highlightElement(checkbox);
						checkbox.click();
					}

				}
			}
		}
	}

//	public void isItemsAvilableInCart() {
//		if(productCheckbox.isEmpty()) {
//			System.out.println("No Products In The Cart");
//		}else {
//			highlighter.highlightElements(brandNameInCart);
//			System.out.println("Items are avilable in the cart");
//		}
//	}

	public void isCartEmpty() {
		if (productCheckbox.isEmpty()) {
			System.out.println("Cart is empty");
		} else {
			// highlighter.highlightElements(productCheckbox);
			System.out.println("The list of products in the cart are: ");
			for (int i = 0; i < productCheckbox.size(); i++) {
				if (i % 2 == 0) {
					String ProductNameInCart = brandNameInCart.get(i).getText();
					System.out.println("The products are " + ProductNameInCart + ".");
				}

			}
		}
	}
}
