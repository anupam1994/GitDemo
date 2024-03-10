package AnupamTesting.SeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AnupamTesting.SeleniumFramework.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	private @FindBy(xpath="//tr/td[2]")
	List<WebElement> itemName;
	
	
	public boolean orderVerification(String productName)
	{
		boolean match = itemName.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}

	
	
}
