package Utility;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SeleniumHighlighterUtility {

	WebDriver driver;
	JavascriptExecutor js;

	public void highlightElement(WebElement productBrand) {
		highlightElement(productBrand, "2px solid green");
	}

	public void highlightElement(WebElement element, String highlightStyle) {
		// Execute JavaScript to highlight the element
		js.executeScript("arguments[0].style.border = '" + highlightStyle + "'", element);

		// Sleep for a short while to see the highlight (optional)
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reset the element's border after highlighting
		js.executeScript("arguments[0].style.border = ''", element);
	}

	// LIST
	public void highlightElements(List<WebElement> element) {
		highlightElement(element, "2px solid red");
	}

	private void highlightElement(List<WebElement> element, String highlightStyle) {
		// Execute JavaScript to highlight the element
		js.executeScript("arguments[0].style.border = '" + highlightStyle + "'", element);

		// Sleep for a short while to see the highlight (optional)
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reset the element's border after highlighting
		js.executeScript("arguments[0].style.border = ''", element);

	}

	public SeleniumHighlighterUtility(WebDriver driver) {
		this.js = (JavascriptExecutor) driver;
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
