package swaglab_pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import swaglab_test.BaseClass;

public class LoginPage {

	WebDriver driver = BaseClass.driver;

	// ====================== Lsinssssocators =========================

	@FindBy(xpath = "//input[@id='user-name']")
	WebElement UserName;

	@FindBy(xpath = "//input[@id='password']")
	WebElement Password;

	@FindBy(xpath = "//input[@id='login-button']")
	WebElement LoginBtn;

	// ======================= Methods ======================
	
	// constructor to initialize all the web elements 
	public LoginPage() {
		
		PageFactory.initElements(driver, this);

	}

	public void LoginFunction(String UserNameVal, String PasswordVal) {

		UserName.sendKeys(UserNameVal);
		Password.sendKeys(PasswordVal);
		LoginBtn.click();

	}

}
