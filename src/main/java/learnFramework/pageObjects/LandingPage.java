package learnFramework.pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learnFramework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver)
	{
		super(driver);


		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement passwordElement;

	@FindBy(id="login")
	WebElement submit;

	@FindBy(css=".ng-star-inserted")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) { 
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);	
		return productCatalogue;

	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}


	public void goTo(String URL) {
		driver.get(URL);
	}
}
