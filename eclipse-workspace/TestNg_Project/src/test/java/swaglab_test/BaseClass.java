
package swaglab_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class BaseClass {
	
	public static WebDriver driver;
	XSSFWorkbook wbook;
	XSSFSheet sheet;
	
	@BeforeMethod
	public void SetUp() {
		
			
			String BrowserName = System.getProperty("Browser");
			
			if(BrowserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}else {
				driver = new ChromeDriver();
			}
			
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}

		
	
	@AfterMethod
	public void TearDown() {
		driver.quit();	
	
	}
	@BeforeTest
	public void SetUpExcel() throws IOException {
		
		
		FileInputStream fis = new FileInputStream("src/test/resources/exceldata.xlsx");
		wbook = new XSSFWorkbook(fis);
		sheet = wbook.getSheet("data");
		
	}

	@AfterTest
	public void CloseExcel() throws IOException {
		FileOutputStream outputStream = new FileOutputStream("output.xlsx");
		wbook.write(outputStream);
		wbook.close();
	  	 outputStream.close();
	
	}

}




