package webdriver;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Popup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://zingpoll.com/");
		driver.findElement(By.cssSelector("#Loginform")).click();
		
		WebElement popup = driver.findElement(By.cssSelector("div.modal-dialog.modal_dialog_custom"));
		sleepInSecond(5);
		Assert.assertTrue(popup.isDisplayed());
		
		driver.findElement(By.cssSelector(".modal-dialog.modal_dialog_custom .close")).click();
		sleepInSecond(10);
		Assert.assertFalse(popup.isDisplayed());
		
		
		
	}
	@Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bni.vn/");
		
		WebElement element = driver.findElement(By.cssSelector("#sgpb-popup-dialog-main-div"));
		Assert.assertTrue(element.isDisplayed());
		
		driver.findElement(By.cssSelector(".sgpb-popup-close-button-1")).click();
		sleepInSecond(5);
		
		Assert.assertFalse(element.isDisplayed());
		
		
	}
	
	@Test
	public void TC_03_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		
		WebElement element = driver.findElement(By.cssSelector(".mailch-wrap.rocket-lazyload"));
		sleepInSecond(10);
		Assert.assertTrue(element.isDisplayed());
		if(element.isDisplayed()) {
			driver.findElement(By.cssSelector("#close-mailch")).click();
			sleepInSecond(10);
			Assert.assertFalse(element.isDisplayed());
		}
		
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		
		List<WebElement> allTitle = driver.findElements(By.xpath("//h3[@class='post-title']"));
		for (WebElement title : allTitle) {
			String textTitle = title.getText().trim();
			Assert.assertTrue(textTitle.contains("Selenium"));
		}
		
		
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
