package learnFramework.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import learnFramework.TestComponents.BaseTest;
import learnFramework.TestComponents.Retry;
import learnFramework.pageObjects.CartPage;
import learnFramework.pageObjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub	

		landingPage.loginApplication("test@sometest.com", "India@122223");		

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());


	}

	@Test
	public void wrongItem() throws IOException, InterruptedException {
		// TODO Auto-generated method stub


		String productName="ZARA COAT 33";		

		ProductCatalogue productCatalogue= landingPage.loginApplication("test@sometest.com", "India@123");		

		productCatalogue.addProductToCart(productName);		

		CartPage cartPage= productCatalogue.goToCartPage();

		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertFalse(match);
	}
}
