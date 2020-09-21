package webdriver;
	
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_15_JsExecutor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	String customerName, gender, dateOfBirthInput, addressInput, city; 
	String state, pin, moblieNumber, emailRegister;
	String dOB_Month, dOB_Date, dOB_Year;
	String dateOfBirthIutput, dateOfBirthOutput;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		customerName = "Ronaldo de Lima";
		gender = "male";
		dOB_Month = "10";
		dOB_Date = "17";
		dOB_Year = "1998";
		dateOfBirthInput = dOB_Month + "/" + dOB_Date + "/" + dOB_Year;
		dateOfBirthOutput = dOB_Year + "-" + dOB_Month + "-" + dOB_Date;
		addressInput = "232 Xuan Dong Cho Gao Tien Giang";
		city = "Ho Chi Minh City";
		state = "Sai Gon";
		pin = "123456";
		moblieNumber = "03662529930";
		emailRegister = "dt"+randomNumber()+"@gmail.com"; 
	}

	public void TC_01_Javascript_Executor() {
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
		String domainLiveGuru = (String) executeForBrowser("return document.domain;");
		String urlLiveGuru =  (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(domainLiveGuru,"live.demoguru99.com");
		Assert.assertEquals(urlLiveGuru, "http://live.demoguru99.com/");
		clickToElementByJS("//a[contains(text(),'Mobile')]");
		sleepInSecond(2);
		
		clickToElementByJS("//a[contains(text(),'Samsung Galaxy')]//parent::h2//following-sibling::div[@class='actions']//button");
		sleepInSecond(2);
		Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		clickToElementByJS("//a[contains(text(),'Customer Service')]");
		sleepInSecond(2);
		String titleLiveGuru = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(titleLiveGuru, "Customer Service");
		scrollToElement("//address[@class='copyright']");
		sleepInSecond(2);
		verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo.");
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(2);
		String domainDemoGuru = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(domainDemoGuru, "demo.guru99.com");
	}
	
	public void TC_02_Remove_Attribute() {
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String UserID = "mngr285765";
		String password = "jahUnEm";
		sendkeyToElementByJS("//input[@name='uid']", UserID);
		sendkeyToElementByJS("//input[@name='password']", password);
		sleepInSecond(2);
		clickToElementByJS("//input[@name='btnLogin']");
		sleepInSecond(2);
		clickToElementByJS("//a[contains(text(),'New Customer')]");
		sleepInSecond(2);
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dateOfBirthInput);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(addressInput);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(moblieNumber);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(emailRegister);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
//		sendkeyToElementByJS("//input[@name='name']", "Ronaldo de Lima");
//		clickToElementByJS("//input[@value='f']");
//		sendkeyToElementByJS("//input[@id='dob']", "10/10/1998");
//		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(addressInput);
//		sendkeyToElementByJS("//input[@name='city']", "Ho Chi Minh City");
//		sendkeyToElementByJS("//input[@name='state']", "Sai Gon");
//		sendkeyToElementByJS("//input[@name='pinno']", "123456");
//		sendkeyToElementByJS("//input[@name='telephoneno']", "03662529930");
//		sendkeyToElementByJS("//input[@name='emailid']", "dt198264@gmail.com");
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		sleepInSecond(5);
		clickToElementByJS("//input[@name='sub']");
		sleepInSecond(3);
		Assert.assertTrue(verifyTextInInnerText("Customer Registered Successfully!!!"));
	}
	

	@Test
	public void TC_03_Create_An_Account() {
		String email = "vmh" + randomNumber() +"@gmail.com";
		navigateToUrlByJS("http://live.demoguru99.com/");
		clickToElementByJS("//div[@id='header-account']//a[contains(text(),'My Account')]");
		sleepInSecond(3);
		clickToElementByJS("//span[contains(text(),'Create an Account')]");
		sleepInSecond(3);
		sendkeyToElementByJS("//input[@id='firstname']", "Vo Minh");
		sendkeyToElementByJS("//input[@id='lastname']", "Hieu");
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", "1234567");
		sendkeyToElementByJS("//input[@id='confirmation']", "1234567");
		sleepInSecond(3);
		clickToElementByJS("//div[@class='buttons-set']//button[@class='button']");
		Assert.assertTrue(verifyTextInInnerText("Thank you for registering with Main Website Store."));
		sleepInSecond(3);
		clickToElementByJS("//a[contains(text(),'Log Out')]");
		sleepInSecond(3);
		String domainDemoGuruHome = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(domainDemoGuruHome, "live.demoguru99.com");
		
	}
	// function of Javascript Executor
	//1 - Browser
	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(	) {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

//	public boolean waitToJQueryAndJSLoadedSuccess() {
//		implicitlyWait = new WebDriverWait(driver, 30);
//
//		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(driver) {
//				try {
//					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
//				} catch (Exception e) {
//					return true;
//				}
//			}
//		};
//
//		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
//			}
//		};
//
//		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
//	}
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
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}
