package AnupamTesting.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import AnupamTesting.SeleniumFramework.TestComponents.BaseTest;
import AnupamTesting.SeleniumFramework.pageObjects.Cart;
import AnupamTesting.SeleniumFramework.pageObjects.LandingPage;
import AnupamTesting.SeleniumFramework.pageObjects.Payment;
import AnupamTesting.SeleniumFramework.pageObjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public Cart cart;
	public Payment payment;
	String country = "ind";

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = lunchApplication();

	}

	@Given("^Logging in with username (.+) and password (.+)$")
	public void logging_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) {
		cart = productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		cart.goToCart();
		Boolean match = cart.itemVerification(productName);
		Assert.assertTrue(match);

		Payment payment = cart.goToPayment();
		payment.goToPaymentPage();
		payment.shippingInfo(country);
		payment.placeOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationPage(String string) {
		String result = payment.orderConfirmation();
		Assert.assertTrue(result.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {

		Assert.assertEquals(string, landingPage.getErrorMsg());
		driver.close();
	}

}
