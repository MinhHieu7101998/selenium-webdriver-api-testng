package webdriver;
	
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Alert {
	WebDriver driver;
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();;
		alert = driver.switchTo().alert();
		sleepInSecond(1);
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");
	}
	

	@Test
	public void TC_02_Confirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		sleepInSecond(1);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
		
	}
	

	@Test
	public void TC_03_Prompt() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		sleepInSecond(1);
		alert.sendKeys("Automation FC");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: Automation FC");
		
	}
	@Test
	public void TC_04_Authentication() {
		String username = "admin";
		String password = "admin";
		driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).getText(), "Basic Auth");
		
		
		
	}
	@Test
	public void TC_05_Authentication() {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		String basciHref = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		handleAuthenticationAlert(basciHref, username, password);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).getText(), "Basic Auth");
	}
	
	@Test
	public void TC_06_Authentication_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		String rootFolder = System.getProperty("user.dir");
		String firefoxAuthen = rootFolder + "\\AutoIT\\authen_firefox.exe";
		String chromeAuthen = rootFolder + "\\AutoIT\\authen_chrome.exe";
		String authenUrl = "http://the-internet.herokuapp.com/basic_auth";
		
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {firefoxAuthen,username,password});
		}else if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {chromeAuthen,username,password});
		}
		driver.get(authenUrl);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).getText(), "Basic Auth");
		
	}
	public void handleAuthenticationAlert(String Link, String userName,  String password) {
		String splitLink[] = Link.split("//");
		Link = splitLink[0] +"//"+ userName +":"+ password+"@"+splitLink[1];
		driver.get(Link);
		
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
