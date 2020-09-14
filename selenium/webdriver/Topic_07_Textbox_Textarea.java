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

public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	String email;
	String userID, userPassword;
	String urlHome;
	String customerName, gender, dateOfBirthInput, addressInput, city; 
	String state, pin, moblieNumber, emailRegister;
	String dOB_Month, dOB_Date, dOB_Year;
	String customerID;
	String dateOfBirthIutput, dateOfBirthOutput;
	String addressOutput;
	String editAddress, editCity, editState, editPin, editMobileNumber,editEmail;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		urlHome = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("VMH@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userPassword = driver.findElement(By.xpath("//td[(text()='Password :')]/following-sibling::td")).getText();
		driver.get(urlHome);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Manger Id : ')]")).getText(),"Manger Id : "+userID);
		//data test
		customerName = "Ronaldo de Lima";
		gender = "male";
		dOB_Month = "10";
		dOB_Date = "17";
		dOB_Year = "1998";
		dateOfBirthInput = dOB_Month + "/" + dOB_Date + "/" + dOB_Year;
		dateOfBirthOutput = dOB_Year + "-" + dOB_Month + "-" + dOB_Date;
		addressInput = "232 Xuan Dong\nCho Gao\nTien Giang";
		city = "Ho Chi Minh City";
		state = "Sai Gon";
		pin = "123456";
		moblieNumber = "03662429930";
		emailRegister = "dt"+randomNumber()+"@gmail.com"; 
		addressOutput = addressInput.replace("\n", " ");
		//data edit
		editAddress = "181\n27\nAuDuongLan\nMyTho";
		editCity = "Da Nang";
		editState = "Dinh Tuong";
		editPin = "654321";
		editMobileNumber ="1234567890";
		editEmail = "dt" + randomNumber() +"@hotmail.com";
	}

	@Test
	public void TC_01_New_Customer() throws Exception {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Add New Customer");
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dateOfBirthInput);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(addressInput);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(moblieNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailRegister);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
	
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID at New customer form: "+customerID);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),moblieNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailRegister);
		
		driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

		
		
		
	}
	

	@Test
	public void TC_02_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Edit Customer Form']")).getText(), "Edit Customer Form");
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Edit Customer']")).getText(), "Edit Customer");
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='name']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='gender']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@name='dob']")).isEnabled());
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"),customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='gender']")).getAttribute("value"),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='dob']")).getAttribute("value"),dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(),addressInput);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='city']")).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='state']")).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='pinno']")).getAttribute("value"),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='telephoneno']")).getAttribute("value"),moblieNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='emailid']")).getAttribute("value"),emailRegister);
		
		
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(editAddress);
		
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(editCity);
		
		driver.findElement(By.xpath("//input[@name='state']")).clear();
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(editState);
		
		driver.findElement(By.xpath("//input[@name='pinno']")).clear();
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(editPin);
		
		driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(editMobileNumber);
		
		driver.findElement(By.xpath("//input[@name='emailid']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(editEmail);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).getText(),"Customer details updated Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddress.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editMobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);
		
		
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
