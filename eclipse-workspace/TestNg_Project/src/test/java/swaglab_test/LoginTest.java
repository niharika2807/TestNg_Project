package swaglab_test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import swaglab_pages.LoginPage;

public class LoginTest extends BaseClass {

    @Test(priority = 0)
    public void LoginSuccessTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("standard_user", "secret_sauce");

        WebElement productTitle = driver.findElement(By.className("title"));
        Assert.assertEquals(productTitle.getText(), "Products");
    }

    @Test(priority = 1)
    public void LoginFailureTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("standard_user", "secret_test");

        WebElement errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void LockedUserLoginFailureTest() {
        LoginPage lp = new LoginPage();
        lp.LoginFunction("locked_out_user", "secret_sauce");

        WebElement errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void LoginFailureTestFromExcel() {
        String userNameVal = sheet.getRow(1).getCell(0).getStringCellValue();
        String passwordVal = sheet.getRow(1).getCell(1).getStringCellValue();

        LoginPage lp = new LoginPage();
        lp.LoginFunction(userNameVal, passwordVal);

        WebElement errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");

        sheet.getRow(1).createCell(2).setCellValue("DONE");
    }

    @Test
    public void LoginFailureTestFromProperties() throws IOException {
        FileReader reader = new FileReader("src/test/resources/data.properties");
        Properties prop = new Properties();
        prop.load(reader);

        String userNameVal = prop.getProperty("username");
        String passwordVal = prop.getProperty("password");

        LoginPage lp = new LoginPage();
        lp.LoginFunction(userNameVal, passwordVal);

        WebElement errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertEquals(errorMsg.getText(),
                "Epic sadface: Username and password do not match any user in this service");

        reader.close();
    }
}
