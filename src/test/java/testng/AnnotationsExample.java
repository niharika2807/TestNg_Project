
package testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AnnotationsExample extends BaseClass{
		
	@Test
	public void test1() {
		
		System.out.println("Inside test case1");
	}
	
	@Test
	public void test2() {
		
		System.out.println("Inside test case2");
	}

}
