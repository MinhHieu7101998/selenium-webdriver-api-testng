package webdriver;
	
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_16_UploadFile {
	WebDriver driver;
	String source_folder = System.getProperty("user.dir");
	String image_name_01 = "iPhone.jpg";
	String image_name_02 = "Samsung.jpg";
	String image_name_03 = "Sony.jpg";
	String image_01_path = source_folder + "\\Image\\" + image_name_01;
	String image_02_path = source_folder + "\\Image\\" + image_name_02;
	String image_03_path = source_folder + "\\Image\\" + image_name_03;
	String chromePathUploadMultipleFile = source_folder + "\\AutoIT\\chromeUploadMultiple.exe";
	String firefoxPathUploadMultipleFile = source_folder +"\\AutoIT\\firefoxUploadMultiple.exe";
	public void TC_01_Upload_File_BySendkeys_Chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload");
		WebElement elementInput = driver.findElement(By.xpath("//input[@type='file']"));
		
		elementInput.sendKeys(image_01_path);
		sleepInSecond(2);
		elementInput = driver.findElement(By.xpath("//input[@type='file']"));
		elementInput.sendKeys(image_02_path);
		sleepInSecond(2);
		elementInput = driver.findElement(By.xpath("//input[@type='file']"));
		elementInput.sendKeys(image_03_path);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("td button.start"));
		for(WebElement button : buttonStart) {
			button.click();
		}
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_03+"']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_03+"']")).isDisplayed());
	}

	public void TC_02_Upload_File_Firefox() {
		System.setProperty("webdriver.gecko.driver", source_folder + "\\BrowserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload");
		WebElement elementInput = driver.findElement(By.xpath("//input[@type='file']"));
		
		elementInput.sendKeys(image_01_path);
		sleepInSecond(2);
		elementInput = driver.findElement(By.xpath("//input[@type='file']"));
		elementInput.sendKeys(image_02_path);
		sleepInSecond(2);
		elementInput = driver.findElement(By.xpath("//input[@type='file']"));
		elementInput.sendKeys(image_03_path);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("td button.start"));
		for(WebElement button : buttonStart) {
			button.click();
		}
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_03+"']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_03+"']")).isDisplayed());
		
		
	}
	public void TC_03_Upload_File_Chrome() {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://gofile.io/uploadFiles");
		WebElement elementUpload = driver.findElement(By.xpath("//input[@type='file']"));
		
		elementUpload.sendKeys(image_01_path +"\n" + image_02_path + "\n" + image_03_path);
		sleepInSecond(2);
//		elementUpload = driver.findElement(By.xpath("//input[@type='file']"));
//		
//		elementUpload.sendKeys(image_02_path);
//		sleepInSecond(2);
//		elementUpload = driver.findElement(By.xpath("//input[@type='file']"));
//		
//		elementUpload.sendKeys(image_03_path);
//		sleepInSecond(2);
//		elementUpload = driver.findElement(By.xpath("//input[@type='file']"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("#uploadFiles-btnUpload")).click();
		sleepInSecond(10);
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-editLink']")).click();
		
		String parentID = driver.getWindowHandle();
		switchToWindowByID(parentID);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-info text-center col-lg-6']")).isDisplayed());
		closeAllWindowsWithoutParent(parentID);
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-downloadLink']")).click();
		sleepInSecond(3);
		switchToWindowByID(parentID);
		
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		
	}
	
	public void TC_04_Upload_File_With_Chrome_And_AutoIT() throws IOException {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSecond(3);
		Runtime.getRuntime().exec(new String[] {chromePathUploadMultipleFile,image_01_path,image_02_path,image_03_path});
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("td button.start"));
		for(WebElement button : buttonStart) {
			button.click();
		}
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_03+"']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_03+"']")).isDisplayed());
		
	}

	public void TC_05_Upload_File_With_Firefox_And_AutoIT() throws IOException {
		System.setProperty("webdriver.gecko.driver", source_folder + "\\BrowserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSecond(3);
		Runtime.getRuntime().exec(new String[] {firefoxPathUploadMultipleFile,image_01_path,image_02_path,image_03_path});
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		List<WebElement> buttonStart = driver.findElements(By.cssSelector("td button.start"));
		for(WebElement button : buttonStart) {
			button.click();
		}
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_03+"']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_03+"']")).isDisplayed());
	}

	public void TC_06_Upload_File_With_Chrome_And_AutoIT_Gofile() throws IOException {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://gofile.io/uploadFiles");
		
		driver.findElement(By.xpath("//button[@id='dropZoneBtnSelect']")).click();
		sleepInSecond(3);
		Runtime.getRuntime().exec(new String[] {chromePathUploadMultipleFile,image_01_path,image_02_path,image_03_path});
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("#uploadFiles-btnUpload")).click();
		sleepInSecond(10);
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-editLink']")).click();
		
		String parentID = driver.getWindowHandle();
		switchToWindowByID(parentID);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-info text-center col-lg-6']")).isDisplayed());
		closeAllWindowsWithoutParent(parentID);
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-downloadLink']")).click();
		sleepInSecond(3);
		switchToWindowByID(parentID);
		
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		
		
	}

	public void TC_07_Upload_File_With_Firefox_And_AutoIT_GoFile() throws IOException {
		System.setProperty("webdriver.gecko.driver", source_folder + "\\BrowserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://gofile.io/uploadFiles");
		
		driver.findElement(By.xpath("//button[@id='dropZoneBtnSelect']")).click();
		sleepInSecond(3);
		Runtime.getRuntime().exec(new String[] {firefoxPathUploadMultipleFile,image_01_path,image_02_path,image_03_path});
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_02+"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'"+image_name_03+"')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("#uploadFiles-btnUpload")).click();
		sleepInSecond(10);
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-editLink']")).click();
		
		String parentID = driver.getWindowHandle();
		switchToWindowByID(parentID);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-info text-center col-lg-6']")).isDisplayed());
		closeAllWindowsWithoutParent(parentID);
		driver.findElement(By.xpath("//a[@id='uploadFiles-uploadRowResult-downloadLink']")).click();
		sleepInSecond(3);
		switchToWindowByID(parentID);
		
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']//following-sibling::td[@class]//a[@class='showInfo mr-1']")).isDisplayed());
	}
	@Test
	public void TC_08_Upload_File_With_Chrome_And_Robot_Class() throws AWTException {
		System.setProperty("webdriver.chrome.driver", source_folder + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSecond(3);
		
		StringSelection select = new StringSelection(image_01_path + "\n" + image_02_path );
		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select,null);
		
		Robot robot = new Robot();
		// Nhan phim Enter de vao thanh textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhan Ctrl + V de past data co trong clipboard (la duong dan file)
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nha Ctrl + V
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(2);
		
		// Nhan phim Enter de tim kiem file trong desktop
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("td button.start")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_01+"']")).isDisplayed());
		
	}
	public void TC_09_Upload_File_With_Firefox_And_Robot_Class() throws AWTException {
		System.setProperty("webdriver.gecko.driver", source_folder + "\\BrowserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("http://blueimp.github.io/jQuery-File-Upload");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSecond(3);
		
		StringSelection select = new StringSelection(image_01_path);
		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select,null);
		
		Robot robot = new Robot();
		// Nhan phim Enter de vao thanh textbox
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhan Ctrl + V de past data co trong clipboard (la duong dan file)
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nha Ctrl + V
		
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(2);
		
		// Nhan phim Enter de tim kiem file trong desktop
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'"+image_name_01+"')]")).isDisplayed());
		
		driver.findElement(By.cssSelector("td button.start")).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("p a[title='"+image_name_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src*='"+image_name_01+"']")).isDisplayed());
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
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
	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String window : allWindows) {
			if(!window.equals(parentID)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size()==1) {
			return true;
		}
			return false;
	}
}
