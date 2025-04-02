package learnFramework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class SubmitOrderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		WebDriver driver = new ChromeDriver();
		//		WebDriver driver = new FirefoxDriver();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		String productName="ZARA COAT 3";
		String URL="https://rahulshettyacademy.com/client/";
		driver.get(URL);
		//		LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("test@sometest.com");
		driver.findElement(By.id("userPassword")).sendKeys("India@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(products.size());

		WebElement prod=products.stream().filter(product->(product.findElement(By.cssSelector("b")).getText().equals(productName))).findFirst().orElse(null);
		prod.findElement(By.cssSelector("div[class*='mb-3'] button:last-child")).click();



		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("ngx-spinner"))));

		driver.findElement(By.cssSelector(".fa-shopping-cart")).click();

		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match=cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country']")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
