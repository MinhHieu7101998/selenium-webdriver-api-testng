package webdriver;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_TestBug {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC04_Iframe() {
		
		driver.get("https://kyna.vn/");
//		// if popup displayed - close it
//		try {
//			explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".fancybox-image")));
//		} catch (Exception ex) {
//			System.out.println("The popup is not displayed");
//		}

		if (driver.findElement(By.cssSelector(".fancybox-image")).isDisplayed()) {
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}

		// Verify the iframe facebook is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']/iframe")).isDisplayed());

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));

		// verify the number of like is 169k
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "169K likes");

		// switch back to home
		driver.switchTo().defaultContent();

		// Verify chat iframe is displayed
		Assert.assertTrue(driver.findElement(By.id("cs_chat_iframe")).isDisplayed());

		// Switch to chat iframe
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));

		// send some text
		driver.findElement(By.tagName("textarea")).sendKeys("Selenium");
		driver.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);

		// Verify the form is displayed
		Assert.assertTrue(driver.findElement(By.tagName("form")).isDisplayed());

		// Search the keyword "Java" at the main page
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.className("search-button")).click();

		// Verify the text result is "Java"
		Assert.assertEquals(driver.findElement(By.cssSelector(".menu-heading>h1")).getText(), "'Java'");
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
