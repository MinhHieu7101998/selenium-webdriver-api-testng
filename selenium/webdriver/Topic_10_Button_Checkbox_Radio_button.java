package webdriver;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Button_Checkbox_Radio_button {
	WebDriver driver;
	JavascriptExecutor JsExecutor;
	By firstCheckbox = By.xpath("//input[@value='Anemia']");
	By secondCheckbox = By.xpath("//input[@value='Asthma']");
	By thirdCheckbox = By.xpath("//input[@value='Arthritis']");
	
	By firstRaido = By.xpath("//input[@value='1-2 days']");
	By secondRadio = By.xpath("//input[@value='5+ cups/day']");
	
	By allCheckbox = By.xpath("//div[@class='form-single-column']//input[@type='checkbox']");
	
	By temp = By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input");
	By temp2 = By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		JsExecutor = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		By btnLogin = By.xpath("//button[@class='fhs-btn-login']");
		
		driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();
		
		Assert.assertFalse(driver.findElement(btnLogin).isEnabled());
		System.out.println(driver.findElement(btnLogin).getText());
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("automationFC@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("1234567");
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(btnLogin).isEnabled());
		System.out.println(driver.findElement(btnLogin).getText());
		
		driver.findElement(btnLogin).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-popup-msg fhs-login-msg']")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");
		
		driver.navigate().refresh();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();
		
		WebElement buttonLogin = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		
		Assert.assertFalse(buttonLogin.isEnabled());
		
		removeDisabledAttributeByJs(buttonLogin);
		sleepInSecond(2);
		
		buttonLogin.click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert'][contains(text(),'Thông tin này không th')]")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']//div[@class='fhs-input-alert'][contains(text(),'Thông tin này không th')]")).getText(), "Thông tin này không thể để trống");
		
		
		
	}
	
	public void TC_02_Checkbox_Radio_Default() throws InterruptedException {
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Kiem tra 3 checkbox dau tien 
		Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(secondCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(thirdCheckbox).isSelected());
		
		
		// Click vao 3 checkbox dau tien
		driver.findElement(firstCheckbox).click();
		sleepInSecond(1);
		driver.findElement(secondCheckbox).click();
		sleepInSecond(1);
		driver.findElement(thirdCheckbox).click();
		sleepInSecond(3);
		
		// Kiem tra da click
		Assert.assertTrue(driver.findElement(firstCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(secondCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(thirdCheckbox).isSelected());
		// Kiem tra 2 radio
		Assert.assertFalse(driver.findElement(firstRaido).isSelected());
		Assert.assertFalse(driver.findElement(secondRadio).isSelected());
		
		// Click vao 2 radio
		driver.findElement(firstRaido).click();
		driver.findElement(secondRadio).click();
		sleepInSecond(2);
		// Kiem tra 2 radio button do da click hay chua
		Assert.assertTrue(driver.findElement(firstRaido).isSelected());
		Assert.assertTrue(driver.findElement(secondRadio).isSelected());
		
		driver.navigate().refresh();
		List<WebElement> elements = driver.findElements(allCheckbox);
		for (WebElement element : elements) {
			element.click();
			Thread.sleep(500);
			
		}
		
		for (WebElement element : elements) {
			element.click();
			Thread.sleep(500);
			
		}
		
	}

	public void TC_03_Checkbox_Radio_Default() {
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		sleepInSecond(5);
		
		clickCheckboxByJs(driver.findElement(temp));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(temp).isSelected());
		
		clickCheckboxByJs(driver.findElement(temp));
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(temp).isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		driver.findElement(temp2).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(temp2).isSelected());
		
		
	}
	public void TC_04_Checkbox_Radio_button_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Summer')]/preceding-sibling::div/input"));
		
		clickCheckboxByJs(element);
		sleepInSecond(5);
		
		Assert.assertTrue(element.isSelected());
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		WebElement element_Checker = driver.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input"));
		WebElement element_Indeterminate = driver.findElement(By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div/input"));
		
		clickCheckboxByJs(element_Checker);
		clickCheckboxByJs(element_Indeterminate);
		sleepInSecond(5);
		
		Assert.assertTrue(element_Checker.isSelected());
		Assert.assertTrue(element_Indeterminate.isSelected());
		
		clickCheckboxByJs(element_Checker);
		clickCheckboxByJs(element_Indeterminate);
		sleepInSecond(5);
		
		Assert.assertFalse(element_Checker.isSelected());
		Assert.assertFalse(element_Indeterminate.isSelected());
		
		
		
		
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeDisabledAttributeByJs(WebElement element) {
		JsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}
	public void clickCheckboxByJs(WebElement element) {
		JsExecutor.executeScript("arguments[0].click();", element);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
