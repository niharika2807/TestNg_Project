package swaglab_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglab_test.BaseClass;

public class ProductsPage {
	
	WebDriver driver = BaseClass.driver;
	
	
	//============ Locators ==============
	
	@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
	WebElement CartIcon;
	
	
	//============= Methods ===============
	
	public ProductsPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void AddToCart(String ProductName) {
		
		String ProductXapth = "//div[text()='" + ProductName + "']//following::button[1]";
		
		WebElement AddToCart = driver.findElement(By.xpath(ProductXapth));
		AddToCart.click();
	}

}

