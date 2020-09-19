package webdriver;
	
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_User_Interactions {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String rootFolder = System.getProperty("user.dir");
	String javascriptPath = rootFolder + "\\drapAndDrop\\drag_and_drop_helper.js";
	String	jqueryPath = rootFolder + "\\drapAndDrop\\jquery_load_helper.js";
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver",".\\BrowserDriver\\chromedriver.exe");
//		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
	}
	public void TC_01_Hover() {
		
		driver.get("https://www.myntra.com/");
		WebElement element = driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"));
		action.moveToElement(element).perform();
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='Kids Home Bath']")).isDisplayed());
		
	}
	
	public void TC_02_Click_And_Hold() {
		
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(6)).release().perform();
		sleepInSecond(2);
		List<WebElement> allItemsIsSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		for (WebElement webElement : allItemsIsSelected) {
			System.out.println(webElement.getText());
			
		}
		System.out.println("Count items is selected: "+allItemsIsSelected.size());
		Assert.assertEquals(allItemsIsSelected.size(), 6);
	}
	
	public void TC_03_Click_And_Hold_Random() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.keyDown(Keys.CONTROL).click(allItems.get(0)).click(allItems.get(5)).click(allItems.get(4)).click(allItems.get(10)).keyUp(Keys.CONTROL).perform();
		sleepInSecond(2);
		List<WebElement> allItemsIsSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		for (WebElement webElement : allItemsIsSelected) {
			System.out.println(webElement.getText());
		}
		System.out.println("Count items is selected: "+allItemsIsSelected.size());
		Assert.assertEquals(allItemsIsSelected.size(), 4);
	}
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));
		action.doubleClick(element).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
	}
	
	public void TC_05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement element = driver.findElement(By.xpath("//span[text()='right click me']"));
		
		action.contextClick(element).perform();
		
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		
		action.moveToElement(element).perform();
		
		String quitAttributeClass = driver.findElement(By.cssSelector(".context-menu-icon-quit")).getAttribute("class");
		
		System.out.println(quitAttributeClass);
		
		Assert.assertTrue(quitAttributeClass.contains("context-menu-icon-quit"));
		Assert.assertTrue(quitAttributeClass.contains("context-menu-visible"));
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		
	}
	@Test
	public void TC_06_Drap_And_Drop_HTML4() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		sleepInSecond(3);
		WebElement source = driver.findElement(By.cssSelector("#draggable"));
		WebElement target = driver.findElement(By.cssSelector("#droptarget"));	
		WebElement cover = driver.findElement(By.xpath("//div[@class='demo-section k-content']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",cover);
		sleepInSecond(5);
		action.dragAndDrop(source, target).perform();
		//action.clickAndHold(source).moveToElement(target).release().perform();
		sleepInSecond(3);
		
		System.out.println(target.getText());
		
		Assert.assertEquals(target.getText(),"You did great!");
		
	}
	
	public void TC_07_Drap_And_Drop_HTML5() throws IOException {
		
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String source_A = "#column-a";
		String target_B = "#column-b";
		String java_script = readFile(javascriptPath);
		
		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + source_A + "\").simulateDragDrop({ dropTarget: \"" + target_B + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		// B to A
		java_script ="$(\"" + target_B + "\").simulateDragDrop({ dropTarget: \"" + source_A + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
	}
	
	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
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
