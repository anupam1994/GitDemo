package AnupamTesting.SeleniumFramework.tests;

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

import AnupamTesting.SeleniumFramework.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.browser",
				"/Users/anupam/BrowserDrivers/chromedriver-mac-arm64/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		LandingPage landingPage = new LandingPage(driver); // Creating object to call constructor

		String email = "anupam@gmail.com";
		String password = "Welcome2024";
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement product = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().contains("ZARA")).findFirst().orElse(null);

		product.findElement(By.xpath("//button[2]")).click();

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cart'] //h3"));

		boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);

		driver.findElement(By.xpath("//ul //button[@type='button']")).click();

		driver.findElement(By.xpath("//div[@class='user__name mt-5'] //input[@class='input txt text-validated']"))
				.sendKeys("ind");

		List<WebElement> lists = driver.findElements(By.cssSelector(".form-group button"));

		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group'] //section")));

		for (WebElement list : lists) {
			if (list.getText().trim().equalsIgnoreCase("India")) {
				list.click();
				break;
			}
		}

		driver.findElement(By.cssSelector(".actions a")).click();

		String result = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(result.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		Thread.sleep(3000);
		driver.close();

	}

}
