package learnFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import learnFramework.TestComponents.BaseTest;
import learnFramework.pageObjects.CartPage;
import learnFramework.pageObjects.CheckoutPage;
import learnFramework.pageObjects.ConfirmationPage;
import learnFramework.pageObjects.OrderPage;
import learnFramework.pageObjects.ProductCatalogue;



public class StandAloneTest extends BaseTest{
	String productName="ZARA COAT 3";

	String countryName="India";	

	@Test(dataProvider = "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub		



		ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalogue.addProductToCart(input.get("product"));		

		CartPage cartPage= productCatalogue.goToCartPage();

		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();		

		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();

		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}


	//To verify ZARA COAT 3 is displayed on orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue= landingPage.loginApplication("test@sometest.com", "India@123");
		OrderPage orderPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}




	@DataProvider
	public Object[][] getData() throws IOException 
	{

		//		HashMap<String, String> map=new HashMap<String, String>();
		//		map.put("email", "test@sometest.com");
		//		map.put("password", "India@123");
		//		map.put("product", "ZARA COAT 3");
		//		
		//		HashMap<String, String> map1=new HashMap<String, String>();
		//		map1.put("email", "shetty@gmail.com");
		//		map1.put("password", "Iamking@000");
		//		map1.put("product", "ADIDAS ORIGINAL");


		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//learnFramework//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1) } };
	}

}

