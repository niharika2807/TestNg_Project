package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GroupDemo {
public static WebDriver driver;
		@BeforeTest
		public void test1() {
			driver= new ChromeDriver();
			System.out.println("Test1 before");
			
		}
		
		@Test(groups={"group1"})
		public void testTitle() {
			driver.get("https://newsapi.org/");
			String expectedTitle="News API – Search News and Blog Articles on the Web";
			
			String actualTitle= driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			System.out.println("testTitle ");
			
		}
		@Test(groups={"group2"})
		public void testClick() {
			driver.findElement(By.xpath("//a[@class='color-inherit dib dim pv4 relative' and text()='Get started']")).click();
			String expectedURL = "https://newsapi.org/docs/get-started";
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL);
			System.out.println("testClick ");
			
			
		}
		
		@AfterTest
		public void closeBrowser() throws InterruptedException {
			
			Thread.sleep(3000);
			driver.close();
			System.out.println("After");
		}
	
}
