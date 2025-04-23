package stepDefs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PizzaHut_StepDefs {
	WebDriver driver = Hooks.driver;

	@Given("I have launched the application")
	public void i_have_launched_the_application() {
		// Write code here that turns the phrase above into concrete actions
		driver.get("https://www.pizzahut.co.in");

	}

	@When("I enter the location as {string}")
	public void i_enter_the_location_as(String location) {
		// Write code here that turns the phrase above into concrete actions
		WebElement fromLocation = driver.findElement(By.xpath("//input[@role='combobox']"));
		System.out.println("fromLocation using xpath : " + fromLocation.getTagName() + "the location  is: " + location);
		driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys(location);// click on that combo
		driver.findElement(By.xpath("//input[@role='combobox']")).click();

		for (int i = 0; i <= 2; i++) {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.DOWN).build().perform();// press down arrow key
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();// press enter
		}
	}

	@When("I select the very first suggestion from the list")
	public void i_select_the_very_first_suggestion_from_the_list() {
		// Write code here that turns the phrase above into concrete actions

	}



	@Then("I select the tab as {string}")
	public void i_select_the_tab_as(String tabChoice) {
		WebElement tab = driver.findElement(By.xpath("//a[@data-synth='link--pizzas--side']"));
		System.out.println("tabchoice : " + tab.getText());
		tab.click();
	}

	@Then("I add {string} to the basket")
	public void i_add_to_the_basket(String choicePizza) {
		// Write code here that turns the phrase above into concrete
		String ProductXpath = "//div[text()='" + choicePizza + "']//following::button[1]";
		System.err.println("ProductXpath " + ProductXpath);
		WebElement AddToCart = driver.findElement(By.xpath(ProductXpath));
		AddToCart.click();

	}

	@Then("I note down the price displayed on the screen")
	public void i_note_down_the_price_displayed_on_the_screen() {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("I should see the pizza {string} is added to the cart")
	public void i_should_see_the_pizza_is_added_to_the_cart(String cartItem) {
		// Write code here that turns the phrase above into concrete actions
		WebElement ProductInCart = driver.findElement(By.xpath("//div[@data-synth='basket-item-type--pizza']"));
		System.out.println("ProductInCart.getText():= " + ProductInCart.getText() + "ProductInCart.getTag():="
				+ ProductInCart.getTagName());
		Assert.assertEquals(ProductInCart.getText(), cartItem);

	}

	@Then("price is also displayed correctly")
	public void price_is_also_displayed_correctly() {
		// Write code here that turns the phrase above into concrete actions
		WebElement productPrice = driver.findElement(
				By.xpath("//div[@class='basket-item-product-price leading-tight bold text-15 text-black']"));
		System.out.println("productPrice.getText():= " + productPrice.getText() + "productPrice.getTag():="
				+ productPrice.getTagName());

	}

	@Then("I click on the Checkout button")
	public void i_click_on_the_Checkout_button() {
		// Write code here that turns the phrase above into concrete actions
		WebElement checkOut = driver.findElement(By.xpath("//a[@class='button button--primary  justify-between']"));
		System.out.println(checkOut.getTagName());
		checkOut.click();
	}

	@Then("I should be landed on the secured checkout page with url {string}")
	public void i_should_be_landed_on_the_secured_checkout_page_with_url(String url) {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("I enter the First Name {string}")
	public void i_enter_the_First_Name(String fName) {
		// Write code here that turns the phrase above into concrete actions
		WebElement firstName = driver.findElement(By.id("checkout__name"));
		firstName.sendKeys(fName);

	}

	
	@Then("I enter the Mobile {string}")
	public void i_enter_the_Mobile(String mobile) {
	    // Write code here that turns the phrase above into concrete actions
		WebElement mobileNo = driver.findElement(By.id("checkout__phone"));
		mobileNo.sendKeys(mobile);
	}


	@Then("I enter the email {string}")
	public void i_enter_the_email(String emailId) {
		// Write code here that turns the phrase above into concrete actions
		WebElement eid = driver.findElement(By.id("checkout__email"));
		eid.sendKeys(emailId);
	}

}