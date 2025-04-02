package learnFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learnFramework.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(css="tr td:nth-of-type(2)")
	List<WebElement> productNames;


	public Boolean verifyOrderDisplay(String productName) {
		Boolean match=productNames.stream().anyMatch(product->product.getText().equals(productName));


		return match;
	}



}
