package AnupamTesting.SeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AnupamTesting.SeleniumFramework.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) 
	{	
	this.driver = driver;
	}

	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderPage;
	
	public void waitForElementToAppear(By Locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}
	
	public void waitForWebElementToAppear(WebElement Locator)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Locator));
	}
	
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void goToOrdersPage()
	{
		orderPage.click();
	}
}
