package swaglab_test;

import org.testng.annotations.Test;

import swaglab_pages.LoginPage;
import swaglab_pages.ProductsPage;

public class ProductsTest extends BaseClass{
	
	@Test
	public void AddtoCartTest() {
		
		LoginPage lp = new LoginPage();
		lp.LoginFunction("standard_user", "secret_sauce");
		
		ProductsPage pp = new ProductsPage();
		pp.AddToCart("Sauce Labs Bolt T-Shirt");
		
	}

}



