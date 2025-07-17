package swaglab_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swaglab_pages.LoginPage;
import swaglab_pages.ProductsPage;

public class ProductsTest extends BaseClass {

    @BeforeMethod
    public void openLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void addToCartTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("standard_user", "secret_sauce");

        ProductsPage pp = new ProductsPage();
        pp.AddToCart("Sauce Labs Bolt T-Shirt");

        WebElement productInCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        Assert.assertEquals(productInCart.getText(), "Sauce Labs Bolt T-Shirt");
    }
}
