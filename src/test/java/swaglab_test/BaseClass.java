package swaglab_test;

import java.io.*;
import java.time.Duration;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseClass {
    public static WebDriver driver;
    public static XSSFWorkbook wbook;
    public static XSSFSheet sheet;

    @BeforeMethod
    public void setUp() throws IOException {
        // ✅ Disable Chrome password manager and popup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("credentials_enable_service", false);
            put("profile.password_manager_enabled", false);
        }});
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        // ✅ Excel Load (used in Excel test case)
        FileInputStream fis = new FileInputStream("src/test/resources/exceldata.xlsx");
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("data");
    }

    @AfterMethod
    public void tearDown() throws IOException {
        wbook.close();
        driver.quit();
    }
}
