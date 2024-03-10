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
import org.testng.Assert;

import AnupamTesting.SeleniumFramework.AbstractComponents.AbstractComponents;

public class Payment extends AbstractComponents {

	WebDriver driver;

	public Payment(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By checkoutButton = By.xpath("//ul //button[@type='button']");
	By countryButton = By.xpath("//div[@class='user__address'] //input[@class='input txt text-validated']");
	By countryList = By.cssSelector(".form-group button");
	By dropdown = By.xpath("//div[@class='form-group'] //section");
	By placeOrder = By.cssSelector(".actions a");
	By confirmationText = By.cssSelector(".hero-primary");
	

	public void goToPaymentPage() 
	{
		driver.findElement(checkoutButton).click();
	}

	public void shippingInfo(String country) 
	{
		driver.findElement(countryButton).sendKeys(country);

		List<WebElement> lists = driver.findElements(countryList);

		waitForElementToAppear(dropdown);
		
		for (WebElement list : lists) 
		{
			if (list.getText().trim().equalsIgnoreCase("India")) 
			{
				list.click();
				break;
			}
		}
	}
	
	public void placeOrder()
	{
		driver.findElement(placeOrder).click();
	}

	public String orderConfirmation()
	{
		String result = driver.findElement(confirmationText).getText();
		return result;
	}
	
	
	
}
