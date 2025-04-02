package learnFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learnFramework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;

	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(css=".mb-3")
	List<WebElement> products;

	@FindBy(css="ngx-spinner")
	WebElement spinner;


	By productsBy = By.cssSelector(".mb-3");
	By addToCart=By.cssSelector("div[class*='mb-3'] button:last-of-type");

	By toastMessage=By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod=getProductList().stream().filter(product->(product.findElement(By.cssSelector("b")).getText().equals(productName))).findFirst().orElse(null);


		return prod;
	}

	public void addProductToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		if(prod!=null) {
			prod.findElement(addToCart).click();
			waitForElementToDisappearBy(toastMessage);
			waitForElementToDisappear(spinner);
		}
		else return;
	}



}
