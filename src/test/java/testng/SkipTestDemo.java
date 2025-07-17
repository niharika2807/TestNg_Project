package testng;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class SkipTestDemo {
	@Test(enabled = false)
	public void test1() {
		System.out.println("Test Case1- This will not executed");
		
	}
	@Test
	public void test2() {
		System.out.println("Test Case2- This will not executed");
		throw new SkipException("Skipping This test2");
	}
	@Test
	public void test3() {
		System.out.println("Test Case3- conditional skip");
		boolean isDataAvailable = false;
		if (isDataAvailable) {
			throw new SkipException("Skipping This test3 on a condition");
		}
	}
}
