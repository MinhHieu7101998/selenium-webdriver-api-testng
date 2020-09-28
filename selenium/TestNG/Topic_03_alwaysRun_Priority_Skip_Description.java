package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
	
public class Topic_03_alwaysRun_Priority_Skip_Description {
	WebDriver driver;
  @Test(priority=3)
  public void TC_01() {
	  System.out.println("Run testcase 01");
  }
  @Test(priority=1, enabled = true, description ="Block code will run")
  public void TC_02() {
	  System.out.println("Run testcase 02");
  }
  @Test(priority=2, enabled=false,description ="Block code will not run")
  public void TC_03() {
	  System.out.println("Run testcase 03");
  }
  @BeforeClass
  public void beforeClass() {
	  	System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
	  	driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//Assert.assertTrue(false);
		 System.out.println("Run before class");
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  driver.quit();
	  System.out.println("Run after class");
  }

}
