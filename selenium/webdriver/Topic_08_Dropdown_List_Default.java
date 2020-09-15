package webdriver;
	
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown_List_Default {
	WebDriver driver;
	String emailRegister;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailRegister = "dt" + randomNumber() +"@gmail.com";
	}
	@Test
	public void TC_01_Handle_Dropdown_List_Default() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Select element = new Select(driver.findElement(By.name("user_job1"))); 
		Assert.assertFalse(element.isMultiple());
		element.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(element.getFirstSelectedOption().getText(),"Mobile Testing");
		
		element.selectByValue("manual");
		Assert.assertEquals(element.getFirstSelectedOption().getText(),"Manual Testing");
		
		element.selectByIndex(9);
		Assert.assertEquals(element.getFirstSelectedOption().getText(),"Functional UI Testing");
		
		Assert.assertEquals(element.getOptions().size(),10);
		
		Select element_2 = new Select(driver.findElement(By.name("user_job2"))); 
		Assert.assertTrue(element_2.isMultiple());
		
		element_2.selectByVisibleText("Automation");
		element_2.selectByVisibleText("Desktop");
		element_2.selectByVisibleText("Mobile");
		
		List<WebElement> selectedOption = element_2.getAllSelectedOptions();
		
		Assert.assertEquals(selectedOption.size(), 3);
		
		for(WebElement option : selectedOption ) {
			System.out.println("Options are selected: " + option.getText() );
		}
		element_2.deselectAll();
		
		Assert.assertEquals(element_2.getAllSelectedOptions().size(), 0);
		
	}
	

	@Test
	public void TC_02_Handle_Dropdown_List_Default() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.xpath("//input[@id='gender-female']")).click();
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Than");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Tham");
		
		Select dateOfBirtday = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		dateOfBirtday.selectByVisibleText("1");
		Assert.assertEquals(dateOfBirtday.getFirstSelectedOption().getText(),"1");
		Assert.assertEquals(dateOfBirtday.getOptions().size(), 32);
		
		Select dateOfBirtmonth = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		dateOfBirtmonth.selectByVisibleText("May");
		Assert.assertEquals(dateOfBirtmonth.getFirstSelectedOption().getText(),"May");
		Assert.assertEquals(dateOfBirtmonth.getOptions().size(), 13);
		
		Select dateOfBirtyear = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		dateOfBirtyear.selectByVisibleText("1980");
		Assert.assertEquals(dateOfBirtyear.getFirstSelectedOption().getText(),"1980");
		Assert.assertEquals(dateOfBirtyear.getOptions().size(), 112);
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailRegister);
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("THPT Cho Gao");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("03662429923");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("03662429923");
		
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='gender-female']")).getAttribute("value"), "F");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), "Than");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), "Tham");
		
		Select DateOfBirthDay_2 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(DateOfBirthDay_2.getFirstSelectedOption().getText(), "1");
		
		Select DateOfBirthMonth_2 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(DateOfBirthMonth_2.getFirstSelectedOption().getText(), "May");
		
		Select DateOfBirthYear_2 = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(DateOfBirthYear_2.getFirstSelectedOption().getText(), "1980");
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailRegister);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"), "THPT Cho Gao");
		
		System.out.println("Hoan thanh");
	}
	

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
