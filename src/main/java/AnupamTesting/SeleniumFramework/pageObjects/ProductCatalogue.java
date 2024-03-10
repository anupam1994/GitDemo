package AnupamTesting.SeleniumFramework.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AnupamTesting.SeleniumFramework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	By locator = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//button[2]");
	By toast = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(locator);
		List<WebElement> products = driver.findElements(locator);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement product = getProductList().stream().filter(
				s->s.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
		return product;
	}
	
	public Cart addProductToCart(String productName)
	{
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toast);
		waitForElementToDisappear(animation);
		Cart cart = new Cart(driver);
		return cart;
	}
	
}
