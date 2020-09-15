package webdriver;
	
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Dropdown_List_Custom {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		expliciWait = new WebDriverWait(driver,30);
		
		jsExecutor = (JavascriptExecutor) driver;
	}
	@Test
	public void TC_01_Dropdown_List_Custom_jQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemDropdownListCustom("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='19']")).isDisplayed());
	}
	

	@Test
	public void TC_02_Dropdown_List_Custom_ReactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemDropdownListCustom("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']//div", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[text()='Stevie Feliciano']")).isDisplayed());
	}
	

	@Test
	public void TC_03_Dropdown_List_Custom_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemDropdownListCustom("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Second Option')]")).isDisplayed());
	}
	public void selectItemDropdownListCustom(String parentXpath, String childXpath, String expectedItem) {
		// Step 1: Click vao 1 the cha de show ra het cac Item trong dropdown do
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentXpath)));
		driver.findElement(By.xpath(parentXpath)).click();;
		sleepInSecond(2);
		
		//Step 2: Cho cho tat ca cac Item co trong DOM duoc load ra het
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Step 3: Lay tat ca Item dua vao 1 list Element
		List<WebElement> allItem = driver.findElements(By.xpath(childXpath));
		
		// Tong so luong trong list Element bang bao nhieu?
		System.out.println("Size of allItem: " + allItem.size());
		
		// Step 4: Duyet qua tung cai Item co trong List Item
		for (WebElement actualItem : allItem) {
			// Step 5: Moi lan duyet Item kiem tra xem text cua Item do co bang voi expect hay khong
			String textItem = actualItem.getText().trim();
			System.out.println(textItem);
			
			// Neu nhu bang thi click vao va thoat khoi vong lap, khong duyet nua
			// Neu nhu khong bang thi tiep tuc duyet list Item cho den khi het tat ca Item
			if(textItem.equals(expectedItem)) {
				// Truoc khi click thi phai scroll den Item do truoc
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				sleepInSecond(2);
				
				actualItem.click();
				break;
			}
			
		}
		
	}
	public void sleepInSecond(long Second) {
		try {
			Thread.sleep(Second*1000);
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
