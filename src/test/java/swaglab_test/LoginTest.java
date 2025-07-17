package swaglab_test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import swaglab_pages.LoginPage;
import java.time.Duration;

public class LoginTest extends BaseClass {

    @BeforeMethod
    public void openLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 0)
    public void loginSuccessTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("standard_user", "secret_sauce");

        WebElement productTitle = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        Assert.assertEquals(productTitle.getText().trim(), "Products");
    }

    @Test(priority = 1)
    public void loginFailureTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("standard_user", "secretsauce");

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        Assert.assertEquals(errorMessage.getText().trim(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 2)
    public void lockedUserFailureTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("locked_out_user", "secret_sauce");

        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        Assert.assertEquals(errorMessage.getText().trim(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(priority = 3)
    public void loginFailureTestFromExcel() {
        String username = sheet.getRow(1).getCell(0).getStringCellValue();
        String password = sheet.getRow(1).getCell(1).getStringCellValue();

        LoginPage lp = new LoginPage();
        lp.LoginFunction(username, password);

        WebElement errorMsg = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        Assert.assertEquals(errorMsg.getText().trim(),
                "Epic sadface: Username and password do not match any user in this service");

        sheet.getRow(1).createCell(2).setCellValue("DONE");
    }

    @Test(priority = 4)
    public void loginFailureTestFromProperties() throws IOException {
        FileReader reader = new FileReader("data.properties");
        Properties prop = new Properties();
        prop.load(reader);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        LoginPage lp = new LoginPage();
        lp.LoginFunction(username, password);

        WebElement errorMsg = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
        Assert.assertEquals(errorMsg.getText().trim(),
                "Epic sadface: Username and password do not match any user in this service");

        sheet.getRow(1).createCell(2).setCellValue("Completed");
    }
}
