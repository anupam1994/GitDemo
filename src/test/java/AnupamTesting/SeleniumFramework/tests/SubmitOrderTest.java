package AnupamTesting.SeleniumFramework.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
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
import AnupamTesting.SeleniumFramework.pageObjects.OrderPage;
import AnupamTesting.SeleniumFramework.pageObjects.Payment;
import AnupamTesting.SeleniumFramework.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	
	String country = "ind";
	String msg = "THANKYOU FOR THE ORDER.";

	@Test(dataProvider="getData" , groups= {"Purchase"})
//	public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException { //When data is provided normally, without hashmap or JSON
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

//		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password); //without HashMap
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
//		Cart cart = productCatalogue.addProductToCart(productName);//without HashMap
		Cart cart = productCatalogue.addProductToCart(input.get("product"));
		cart.goToCart();
//		Boolean match = cart.itemVerification(productName);//without HashMap
		Boolean match = cart.itemVerification(input.get("product"));
		Assert.assertTrue(match);

		Payment payment = cart.goToPayment();
		payment.goToPaymentPage();
		payment.shippingInfo(country);
		payment.placeOrder();
		String result = payment.orderConfirmation();
		Assert.assertTrue(result.equalsIgnoreCase(msg));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() {
		String email = "anupam@gmail.com";
		String password = "Welcome2024";
		String productName = "ZARA COAT 3";
		
		landingPage.loginApplication(email, password);
		OrderPage orderpage = new OrderPage(driver);
		orderpage.goToOrdersPage();
		boolean match = orderpage.orderVerification(productName);
		Assert.assertTrue(match);
	}
	
//	@DataProvider //This way is only when we are directly pushing the data through Data Provider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"anupam@gmail.com","Welcome2024","ZARA COAT 3"},{"anupam@gmail.com","Welcome2024","ADIDAS ORIGINAL"}};
//	} 
	
//	@DataProvider //Using HashMap
//	public Object[][] getData()
//	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "anupam@gmail.com");
//		map.put("password", "Welcome2024");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "anupam@gmail.com");
//		map1.put("password", "Welcome2024");
//		map1.put("product", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map1}};
//	}
	
	@DataProvider //Using Json File
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJsonDataToMap((System.getProperty("user.dir") + "//src//test//java//AnupamTesting//SeleniumFramework//data//PurchaseOrder.json"));
	return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	

}
