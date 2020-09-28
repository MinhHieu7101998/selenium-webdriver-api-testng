package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_04_DataDriven {
	WebDriver driver;
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
	  	driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
  }
  @Test(dataProvider = "user_password")
  public void TC_01_Login_To_System(String usename, String password) {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(usename);
	  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
	  driver.findElement(By.xpath("//button[@name='send']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  driver.findElement(By.xpath("//a//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//li//a[text()='Log Out']")).click();
	  
	  //driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  //driver.navigate().refresh();
	  
  }

  @DataProvider(name="user_password")
  public Object[][] usernameAndPasswordData() {
    return new Object[][] {
    	{"selenium_11_01@gmail.com","111111"},
    	{"selenium_11_02@gmail.com","111111"},
    	{"selenium_11_03@gmail.com","111111"},
    };
  }
  

  @AfterClass(alwaysRun = true)
  public void afterClass() {
	  driver.quit();
  }

}
