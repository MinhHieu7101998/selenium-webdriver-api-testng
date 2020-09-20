package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Iframe_Window_Tab {
	WebDriver driver;
	JavascriptExecutor JsExecutor;
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		JsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		//img[@class='fancybox-image']
		WebElement popup = driver.findElement(By.xpath("//img[@class='fancybox-image']"));
		Assert.assertTrue(popup.isDisplayed());
		if(popup.isDisplayed()) {
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(10);
			//Assert.assertFalse(popup.isDisplayed());
		}
		
		WebElement iframeFacebook = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
		Assert.assertTrue(iframeFacebook.isDisplayed());
		driver.switchTo().frame(iframeFacebook);
		
		String textFacebookLike = driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText();
		Assert.assertEquals(textFacebookLike,"169K likes");
		
		driver.switchTo().defaultContent();
		sleepInSecond(5);
		
		WebElement iframeChat = driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']"));
		Assert.assertTrue(iframeChat.isDisplayed());
		driver.switchTo().frame(iframeChat);
		driver.findElement(By.cssSelector(".textarea")).sendKeys("Automation Fc");
		sleepInSecond(3);
		driver.findElement(By.cssSelector(".textarea")).sendKeys(Keys.ENTER);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("#sign-in-menu")).isDisplayed());
		sleepInSecond(10);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Java");
		sleepInSecond(2);
		driver.findElement(By.cssSelector(".search-button")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector(".menu-heading h1")).getText(), "'Java'");
		
		
	}

	public void TC_02_Window_Tab() {
		String parentTitle = "SELENIUM WEBDRIVER FORM DEMO";
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'GOOGLE')]")).click();
		switchToWindowByTitle("Google");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(),"Google");
		switchToWindowByTitle(parentTitle);
		
		driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();;
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
		
		switchToWindowByTitle(parentTitle);
		
		driver.findElement(By.xpath("//a[contains(text(),'TIKI')]")).click();
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		sleepInSecond(5);
		
		closeAllWindowsWithoutParent(parentID);
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(), parentTitle);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
	}

	public void TC_03_Window_Tab() {
		driver.get("https://kyna.vn/");
		String parentTitle = "Kyna.vn - Học online cùng chuyên gia";
		String parentID = driver.getWindowHandle();
		//img[@class='fancybox-image']
		WebElement popup = driver.findElement(By.xpath("//img[@class='fancybox-image']"));
		Assert.assertTrue(popup.isDisplayed());
		if(popup.isDisplayed()) {
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(5);
			//Assert.assertFalse(popup.isDisplayed());
		}
		
		//Facebook
		WebElement facebook = driver.findElement(By.xpath("//img[@alt='facebook']"));
		clickElementByJs(facebook);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		
		//Youtube
		switchToWindowByTitle(parentTitle);
		WebElement youtube = driver.findElement(By.xpath("//img[@alt='youtube']"));
		clickElementByJs(youtube);
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");
		
		//Zalo
		switchToWindowByTitle(parentTitle);
		WebElement zalo = driver.findElement(By.xpath("//img[@alt='zalo']"));
		clickElementByJs(zalo);
		switchToWindowByTitle("Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");
		Assert.assertEquals(driver.getCurrentUrl(), "https://zalo.me/1985686830006307471");
		
		//Android
		switchToWindowByTitle(parentTitle);
		WebElement android = driver.findElement(By.xpath("//img[@alt='android-app-icon']"));
		clickElementByJs(android);
		switchToWindowByTitle("Kyna.vn Official – Học Online cùng chuyên gia - Ứng dụng trên Google Play");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn Official – Học Online cùng chuyên gia - Apps on Google Play");
		Assert.assertEquals(driver.getCurrentUrl(), "https://play.google.com/store/apps/details?id=com.navikyna");
		
		//Fanpage
		switchToWindowByTitle(parentTitle);
		WebElement iframeFacebook = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
		driver.switchTo().frame(iframeFacebook);
		WebElement kyna = driver.findElement(By.xpath("//a[@title='Kyna.vn']"));
		clickElementByJs(kyna);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");
		
		// Close tabs
		driver.switchTo().defaultContent();
		switchToWindowByTitle(parentTitle);
		closeAllWindowsWithoutParent(parentID);
		
		
	}
	@Test
	public void TC_04_Window_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
		driver.findElement(By.xpath("//a[@title='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'The product Sony Xperia has been added to comparis')]")).getText(),"The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();	
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'The product Samsung Galaxy has been added to compa')]")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.xpath("//div[@class='block block-list block-compare']//button[@class='button']")).click();
		sleepInSecond(3);
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowsWithoutParent(parentID);
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[contains(text(),'Clear All')]")).click();
		sleepInSecond(3);
		alert = driver.switchTo().alert();
		alert.accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).getText(), "The comparison list was cleared.");
		
	}
	
	public void clickElementByJs(WebElement element) {
		JsExecutor.executeScript("arguments[0].click();", element);
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
	public void switchToWindowByTitle(String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for(String window : allWindow) {
			driver.switchTo().window(window);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(title)) {
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
