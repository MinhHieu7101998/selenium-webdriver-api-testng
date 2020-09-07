package webdriver;

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

	@Test
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

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123@456.789");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123456");

		driver.findElement(By.xpath(".//*[@id='send2']")).click();

		String emailMessageError = driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailMessageError, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
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

	@Test
	public void TC_03_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("1234567");

		driver.findElement(By.xpath(".//*[@id='send2']")).click();

		String messageError = driver.findElement(By.xpath(".//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(messageError, "Invalid login or password.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
