package swaglab_test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseClass {
    public static WebDriver driver;
    public static XSSFWorkbook wbook;
    public static XSSFSheet sheet;

    @BeforeClass
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        FileInputStream fis = new FileInputStream("exceldata.xlsx");
        wbook = new XSSFWorkbook(fis);
        sheet = wbook.getSheet("data");
        if (sheet == null) {
            throw new IllegalStateException("Sheet named 'data' not found!");
        }
    }

    @AfterClass
    public void tearDown() throws IOException {
        wbook.close();
        driver.quit();
    }
}
