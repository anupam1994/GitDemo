package AnupamTesting.SeleniumFramework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AnupamTesting.SeleniumFramework.TestComponents.BaseTest;
import AnupamTesting.SeleniumFramework.pageObjects.Cart;
import AnupamTesting.SeleniumFramework.pageObjects.LandingPage;
import AnupamTesting.SeleniumFramework.pageObjects.Payment;
import AnupamTesting.SeleniumFramework.pageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void loginValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String email = "anupam@gmail.com";
		String password = "Welcome2025";
	
		landingPage.loginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());

	}
	
	@Test(groups = {"ErrorHandling"})
	public void productValidation()
	{
		String email = "anupam@gmail.com";
		String password = "Welcome2024";
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

		Cart cart = productCatalogue.addProductToCart(productName);
		cart.goToCart();
		Boolean match = cart.itemVerification(productName);
		Assert.assertEquals("true", match);
	}

}
