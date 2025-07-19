package swaglab_test;

import java.io.*;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseClass {
    public static WebDriver driver;
    public static XSSFWorkbook wbook;
    public static XSSFSheet sheet;

    @BeforeMethod
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // Excel Load (used in Excel test case)
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
