package swaglab_test;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import swaglab_pages.LoginPage;
import java.time.Duration;

public class ProductsTest extends BaseClass {

    @Test
   
        public void addToCartTest() {
            // Step 1: Login
            LoginPage lp = new LoginPage(driver);
            lp.LoginFunction("standard_user", "secret_sauce");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Step 2: Handle popup
            try {
                wait.withTimeout(Duration.ofSeconds(5));
                WebElement chromePopupOkButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='OK' or text()='Ok' or text()='ok']")));
                chromePopupOkButton.click();
                System.out.println("✅ Chrome popup closed");
            } catch (Exception e) {
                System.out.println("ℹ️ No Chrome popup appeared");
            }

            // Reset wait timeout
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Step 3: Click product with JS fallback
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Sauce Labs Backpack']")
            ));

            try {
                firstProduct.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);
            }

            // Step 4: Wait for product detail page via back button
            WebElement backButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("back-to-products")
            ));

            // Step 5: Assert
            Assert.assertTrue(backButton.isDisplayed(), "Back button should be visible on product page");
        }

        
}
