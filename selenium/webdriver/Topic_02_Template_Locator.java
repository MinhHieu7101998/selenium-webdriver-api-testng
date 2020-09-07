package webdriver;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Template_Locator {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khởi tạo browser trên firefox.
		driver = new FirefoxDriver();
		// Chờ element được hiển thị để thao tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Mở browser ở chế độ maximize ( not fullscreen)
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() throws InterruptedException {
		// Mở tra một trang web
		driver.get("https://www.thegioididong.com/dtdd/iphone-11-pro-max-512gb");
		//ID
		driver.findElement(By.id("txtEditor")).sendKeys("Mình cần được tư vấn.");;
		Thread.sleep(3000);
		
		//Class
		driver.findElement(By.className("readmore")).click();
		Thread.sleep(3000);
		
		//Name
		//driver.findElement(By.name("OLED")).click();
		//Thread.sleep(3000);
		
		//LinkText
		driver.findElement(By.linkText("Gửi đánh giá của bạn")).click();
		Thread.sleep(3000);
		
		
		//PartialLinkText
//		driver.findElement(By.partialLinkText("SIM VNMB")).click();
//		Thread.sleep(3000);
		
		//TagName
		@SuppressWarnings("unused")
		int count = driver.findElements(By.tagName("input")).size();
		System.out.println(count);
		Thread.sleep(3000);
		
		//CSS
//		driver.findElement(By.cssSelector(".icontgdd-box")).click();
//		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector(".iconmobile-uncheck")).click();
		Thread.sleep(3000);
		
		//Xpath
		driver.findElement(By.xpath("html/body/section/div[5]/aside[1]/div[4]/div/div/form/input")).sendKeys("Samsung");
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
