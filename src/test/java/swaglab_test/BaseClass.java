package swaglab_test;

import java.io.*;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;

import javax.print.DocFlavor.URL;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseClass {
    public static WebDriver driver;
    public static XSSFWorkbook wbook;
    public static XSSFSheet sheet;

   /* @BeforeMethod
    public void setUp() throws IOException {
        // ✅ Disable Chrome password manager & use fresh profile
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        // ✅ Use a temp Chrome user profile to prevent Smart Lock popups
        options.addArguments("user-data-dir=C:/selenium/temp-profile"); // Use an empty/new path

        // ✅ Disable password manager
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        // ✅ Excel Load
        FileInputStream fis = new FileInputStream("src/test/resources/exceldata.xlsx");
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("data");
    }*/
    @BeforeMethod
    public void SetUp() throws IOException {
        // Handle Browser
        String BrowserName = System.getProperty("Browser");
        if (BrowserName == null) {
            BrowserName = "chrome";  // default browser
        }

        DesiredCapabilities cap = new DesiredCapabilities();

        if (BrowserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (BrowserName.equalsIgnoreCase("firefox_grid")) {
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.WIN11);
            java.net.URL HubURL = new java.net.URL("http://localhost:4444");
            driver = new RemoteWebDriver(HubURL, cap);
        } else if (BrowserName.equalsIgnoreCase("chrome_grid")) {
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.WIN11);
            java.net.URL HubURL = new java.net.URL("http://localhost:4444");
            driver = new RemoteWebDriver(HubURL, cap);
        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        // ✅ Load Excel file
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
