package webdriver;
	
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;
import com.google.common.base.Functions;

import net.bytebuddy.implementation.bytecode.Duplication;

public class Topic_17_Webdriver_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String source_folder = System.getProperty("user.dir");
	String image_name_01 = "iPhone.jpg";
	String image_name_02 = "Samsung.jpg";
	String image_name_03 = "Sony.jpg";
	String image_01_path = source_folder + "\\Image\\" + image_name_01;
	String image_02_path = source_folder + "\\Image\\" + image_name_02;
	String image_03_path = source_folder + "\\Image\\" + image_name_03;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,15);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_FindElement_Wait() {
		driver.get("https://www.facebook.com/");
		// tim duoc 1 element
		System.out.println("1 - Start: " + date());
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		System.out.println("1 - End: " + date());
		// tim duoc >2 element
		System.out.println("2 - Start: " + date());
		driver.findElement(By.cssSelector("#pageFooterChildren li")).click();
		System.out.println("2 - End: " + date());
		// 0 tim duoc element
		System.out.println("3 - Start: " + date());
		try {
			driver.findElement(By.xpath("//input[@name ='address']"));
		} catch (Exception e) {
			System.out.println("3 - End: " + date());
		}
			
		
	}
	

	public void TC_02_FindElements_Wait() {
		driver.get("https://www.facebook.com/");
		
		// tim duoc 1 element
		System.out.println("1 - Start: " + date());
		List<WebElement> elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("1 - Size of elements: " + elements.size());
		System.out.println("1 - End: " + date());
		// tim duoc >2 element
		System.out.println("2 - Start: " + date());
		elements = driver.findElements(By.cssSelector("#pageFooterChildren li"));
		System.out.println("2 - Size of elements: " + elements.size());
		System.out.println("2 - End: " + date());
		// 0 tim duoc element
		System.out.println("3 - Start: " + date());
		elements = driver.findElements(By.xpath("//input[@name ='address']"));
		System.out.println("3 - Size of elements: " + elements.size());
		System.out.println("3 - End: " + date());
			
		
	}
	
	public void TC_03_FindElement_Wait() {
		driver.get("https://www.facebook.com/");
		System.out.println("1 - Start: " + date());
		driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("1234567");
		System.out.println("1 - End: " + date());
	}
	public void TC_04_Implicit_Wait() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
		
	}
	public void TC_05_Static_Wait() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//div[@id='start']//button")).click();
		Thread.sleep(10000);
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
		
	}
	
	public void TC_06_ExplicitWait_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button"))).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");	
		
	}

	public void TC_07_ExplicitWait_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='start']/button"))).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");	
		
	}
	
	public void TC_08_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ctl00_ContentPlaceholder1_Panel1")));
		WebElement dateText = driver.findElement(By.cssSelector(".datesContainer div"));
		System.out.println(dateText.getText());
		driver.findElement(By.xpath("//td//a[text()='24']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".datesContainer div")));
		dateText = driver.findElement(By.cssSelector(".datesContainer div"));
		System.out.println(dateText.getText());
		Assert.assertEquals(dateText.getText(),"Thursday, September 24, 2020");
		
	}

	public void TC_09_ExplicitWait() {
		driver.get("https://gofile.io/uploadFiles");
		String parentID = driver.getWindowHandle();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='dropZoneBtnSelect']")));
		WebElement elementUpload = driver.findElement(By.xpath("//input[@type='file']"));
		
		elementUpload.sendKeys(image_01_path +"\n" + image_02_path + "\n" + image_03_path);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='iPhone.jpg']")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='uploadFiles-btnUpload']"))).click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='uploadFiles-uploadRowResult-downloadLink']"))).click();
		switchToWindowByID(parentID);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='I have a VPN already']"))).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		
	}

	public void TC_10_Mix_Implicit_And_Explicit_Found_Element() {
		driver.get("https://www.facebook.com");
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Time start: " + date() );
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']"))).sendKeys("Automation@gmail.com");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='login']"))).click();
		System.out.println("Time end: " + date() );
	}
	
	public void TC_11_Mix_Implicit_Greater_Than_Explicit_Not_Found_Element(){
		// apply explicit ( tham so la By)
		driver.get("https://www.facebook.com");
		explicitWait = new WebDriverWait(driver,5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Time start: " + date() );
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='notfound']")));
			System.out.println("Switch to try");
		} catch (Exception erorr) {
			System.out.println("TC_11_Mix_Implicit_Greater_Than_Explicit_Not_Found_Element" + erorr.getMessage() );
			System.out.println("Switch to catch");
		}
		System.out.println("Time end: " + date() );
	}
	
	public void TC_12_Mix_Implicit_Lesser_Than_Explicit_Not_Found_Element(){
		// apply explicit ( tham so la By)
		driver.get("https://www.facebook.com");
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Time start: " + date() );
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='notfound']")));
			System.out.println("Switch to try");
		} catch (Exception erorr) {
			System.out.println("TC_11_Mix_Implicit_Greater_Than_Explicit_Not_Found_Element" + erorr.getMessage() );
			System.out.println("Switch to catch");
		}
		System.out.println("Time end: " + date() );
	}
	
	public void TC_13_Mix_Implicit_Lesser_Than_Explicit_Not_Found_WebDriver(){
		// apply explicit ( tham so la WebElement)
		driver.get("https://www.facebook.com");
		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Time start: " + date() );
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='notfound']"))));
			System.out.println("Switch to try");
		} catch (Exception erorr) {
			System.out.println("TC_11_Mix_Implicit_Greater_Than_Explicit_Not_Found_Element" + erorr.getMessage() );
			System.out.println("Switch to catch");
		}
		System.out.println("Time end: " + date() );
	}
	@Test
	public void TC_14_Fluent_Wait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FluentWait<WebElement> fluentElement;
		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement>(countdown);
		fluentElement.withTimeout(Duration.ofSeconds(15))
			//Tan so moi lan check 0.2s
			.pollingEvery(Duration.ofMillis(200))
			// Neu gap exception la tim thay, khong thi bo qua
			.ignoring(NoSuchElementException.class)
			//Kiem tra dieu kien
			.until(new Function<WebElement,Boolean>(){
				public Boolean apply(WebElement element) {
					// Kiem tra dieu kien countdown = 00
					boolean flag = element.getText().endsWith("00");
					System.out.println("Time = " + element.getText());
					// return value cho apply
					return flag;
				}
		});
		
	}
	public void TC_15_Fluent_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("Time start: " + date() );
		waitForElementAndClick(By.xpath("//div[@id='start']//button"), 100, 6000);
		waitForElementAndDisplay(By.xpath("//div[@id='finish']//h4[text()='Hello World!']"), 100, 6000);
		System.out.println("Time end: " + date() );
		
	}
	public String date() {
		Date date = new Date();
		return date.toString();
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public void switchToWindowByID(String parentID) {
		// Lay tat ca window
		Set<String> allWindow = driver.getWindowHandles();
		// Duyet qua cac window do, xem window nao co ID khac ID parent thi switch to vao
		for (String window : allWindow) {
			if(!window.equals(parentID)) {
				driver.switchTo().window(window);
				break;
			}
		}
		
	}
	public WebElement waitedElement(By locator, int timeout,int interval) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval))
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	public void waitForElementAndClick(By locator,int timeout,int interval) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval))
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}
	public Boolean waitForElementAndDisplay(By locator, int timeout, int interval) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = waitedElement(locator,timeout,interval);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(interval))
				.ignoring(NoSuchElementException.class);
			boolean _isDisplayed =  wait.until(new Function<WebElement,Boolean>(){
				public Boolean apply(WebElement element) {
					boolean flag = element.isDisplayed();
					return flag;
				}
			});
			return _isDisplayed;
	}
}

