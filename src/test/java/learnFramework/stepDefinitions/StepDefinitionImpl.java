package learnFramework.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learnFramework.TestComponents.BaseTest;
import learnFramework.pageObjects.CartPage;
import learnFramework.pageObjects.CheckoutPage;
import learnFramework.pageObjects.ConfirmationPage;
import learnFramework.pageObjects.LandingPage;
import learnFramework.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
	productCatalogue= landingPage.loginApplication(username,password);
	}
	
	@When("I add the product (.+) to Cart$")
	public void i_add_the_product_to_Cart(String productName){
		{
			productCatalogue.addProductToCart(productName);	
		}
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage= productCatalogue.goToCartPage();

		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();		

		checkoutPage.selectCountry("India");
		confirmationPage=checkoutPage.submitOrder();
	}

	
	@Then( "${string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) throws Throwable
	{
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
		
	}
	
	@Then("${strArg1} message is displayed")
	public void something_message_is_displayed(String strArg1) throws Throwable
	{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
