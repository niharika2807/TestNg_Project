package swaglab_test;

import org.testng.annotations.Test;
import swaglab_pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class LoginTest extends BaseClass {

    @Test
    public void loginSuccessTest() {
        LoginPage lp = new LoginPage(driver);
        lp.LoginFunction("standard_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("inventory")));
    }

    @Test
    public void loginFromProperties() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        Properties prop = new Properties();
        prop.load(fis);

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");

        LoginPage lp = new LoginPage(driver);
        lp.LoginFunction(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("inventory")));
    }

    @Test
    public void loginFromExcel() throws IOException {
        String username = sheet.getRow(1).getCell(0).getStringCellValue();
        String password = sheet.getRow(1).getCell(1).getStringCellValue();

        LoginPage lp = new LoginPage(driver);
        lp.LoginFunction(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertTrue(wait.until(ExpectedConditions.urlContains("inventory")));
    }
}
