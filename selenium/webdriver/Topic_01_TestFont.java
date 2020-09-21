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

public class Topic_01_TestFont {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	@Test
	public void TC_Test() {
		driver.get("https://diemthi.tuyensinh247.com/");
		String text = driver.findElement(By.xpath("//ul[@id='list_menu']//strong[contains(text(),'m chu')]")).getText();
		System.out.println(text);
		Assert.assertEquals(text,"võ minh hiếu");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
		


}
