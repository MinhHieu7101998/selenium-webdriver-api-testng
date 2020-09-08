package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Locator_Xpath_Exercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("");

		driver.findElement(By.xpath(".//*[@id='send2']")).click();

		String emailMessageError = driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailMessageError, "This is a required field.");

		String passwordMessageError = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordMessageError, "This is a required field.");
	}

	@Test(enabled = false)
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123@456.789");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123456");

		driver.findElement(By.xpath(".//*[@id='send2']")).click();

		String emailMessageError = driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailMessageError, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test(enabled = false)
	public void TC_03_Login_With_Invalid_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("johndoe@domain.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("12");

		driver.findElement(By.xpath(".//*[@id='send2']")).click();

		String passwordMessageError = driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']"))
				.getText();
		Assert.assertEquals(passwordMessageError,
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test(enabled = false)
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("1234567");

		driver.findElement(By.xpath(".//*[@id='send2']")).click();

		String messageError = driver.findElement(By.xpath(".//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(messageError, "Invalid login or password.");
		
	}
	
	@Test(enabled = false)
	public void TC_05_Login_With_Valid_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		String myDashboardText =  driver.findElement(By.xpath(".//div[@class='dashboard']//h1")).getText();
		Assert.assertEquals(myDashboardText,"MY DASHBOARD");
		driver.findElement(By.xpath(".//p[@class='hello']/strong[contains(text(),'Hello, Automation Testing!')]")).isDisplayed();
		driver.findElement(By.xpath(".//div[@class='col-1']//div[@class='box-content']//p[contains(.,'Automation Testing')]")).isDisplayed();
		driver.findElement(By.xpath(".//div[@class='col-1']//div[@class='box-content']//p[contains(.,'automation_13@gmail.com')]")).isDisplayed();
		driver.findElement(By.xpath(".//span[@class='label' and text()='Account'] ")).click();
		driver.findElement(By.xpath(".//a[@title=\"Log Out\"]")).click();
	}
	@Test
	public void TC_06_Create_A_New_Account() {
		String email = "PhamVy" + randomEmail() + "@gmail.com";
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//span[contains(text(),'Create an Account')]")).click();
		driver.findElement(By.xpath(".//*[@id='firstname']")).sendKeys("Pham");
		driver.findElement(By.xpath(".//*[@id='lastname']")).sendKeys("Vy");
		driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("1234567");
		driver.findElement(By.xpath(".//*[@id='confirmation']")).sendKeys("1234567");
		driver.findElement(By.xpath(".//*[@id='form-validate']//button[@title='Register']")).click();
		driver.findElement(By.xpath(".//span[contains(text(),'Thank you for registering with Main Website Store.')]")).isDisplayed();
		driver.findElement(By.xpath(".//div[@class='col-1']//div[@class='box-content']//p[contains(.,'Pham Vy')]")).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath(".//div[@class='col-1']//div[@class='box-content']//p")).getText().contains(email), true);
		driver.findElement(By.xpath(".//span[@class='label' and text()='Account'] ")).click();
		driver.findElement(By.xpath(".//a[@title=\"Log Out\"]")).click();
	}
	public int randomEmail() {
		Random rand = new Random();
		return rand.nextInt(9999999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
