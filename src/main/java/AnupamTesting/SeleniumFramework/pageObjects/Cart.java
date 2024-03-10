package AnupamTesting.SeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AnupamTesting.SeleniumFramework.AbstractComponents.AbstractComponents;

public class Cart extends AbstractComponents{
	
	WebDriver driver;
	
	public Cart(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	By productList = By.xpath("//div[@class='cart'] //h3");
	By cartButton = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	public void goToCart()
	{
		waitForElementToAppear(cartButton);
		driver.findElement(cartButton).click();
	}
	
	public boolean itemVerification(String productName)
	{
		List<WebElement> cartProducts = driver.findElements(productList);
		boolean match = cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public Payment goToPayment()
	{
		Payment payment = new Payment(driver);
		return payment;
	}
	
	
	
}
