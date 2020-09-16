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
	String[] months = {"February","July","November"};
	String[] newMonths = {"January","March","August","October","December"};
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		expliciWait = new WebDriverWait(driver,30);
		
		jsExecutor = (JavascriptExecutor) driver;
	}
	public void TC_01_Dropdown_List_Custom_jQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemDropdownListCustom("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[text()='19']")).isDisplayed());
	}
	

	public void TC_02_Dropdown_List_Custom_ReactJs() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemDropdownListCustom("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']//div", "Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui fluid selection dropdown']//div[text()='Stevie Feliciano']")).isDisplayed());
	}
	
	public void TC_03_Dropdown_List_Custom_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemDropdownListCustom("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a", "Second Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Second Option')]")).isDisplayed());
	}
	
	public void TC_04_Dropdown_List_Custom_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemDropdownListCustom("//ejs-dropdownlist[@id='games']/span/span", "//ul[@id='games_options']/li", "Football");
		sleepInSecond(2);
		System.out.println(getHiddenText("select[id='games_hidden']>option"));
		
		Assert.assertEquals(getHiddenText("select[id='games_hidden']>option"), "Football");
		
		
		
		
		
	}
	
	public void TC_05_Dropdown_List_Custom_Editable_Car() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		selectItemDropdownListEditable("//div[@id='default-place']/input", "//div[@id='default-place']//li", "BMW");
		sleepInSecond(2);
		
		Assert.assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"),"BMW");
		
		selectItemDropdownListEditable("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Audi");
		sleepInSecond(2);
		
		Assert.assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"),"Audi");
		
		selectItemDropdownListEditable("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Land Rover");
		sleepInSecond(2);
		
		Assert.assertEquals(getHiddenText("div[id='default-place'] li[class='es-visible']"),"Land Rover");
		
		
	}
	
	public void TC_05_Dropdown_List_Custom_Editable_Flag() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemDropdownListEditable("//input[@class='search']", "//div[@class='visible menu transition']//span", "Algeria");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Algeria" );
		
		
	}
	
	@Test
	public void TC_06_Dropdown_List_Custom_Multiple() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		selectMultipleDropdownListCustom("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", months);
		Assert.assertTrue(areItemSlected(months));
		sleepInSecond(2);
		
		driver.navigate().refresh();
		
		selectMultipleDropdownListCustom("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//span", newMonths);
		Assert.assertTrue(areItemSlected(newMonths));
		sleepInSecond(2);
		
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
	
	public void selectItemDropdownListEditable(String parentXpath, String childXpath, String expectedItem) {
		// Clear du lieu
		driver.findElement(By.xpath(parentXpath)).clear();
		sleepInSecond(1);
		
		// SendKey Item expect
		
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		
		
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
	
	public void selectMultipleDropdownListCustom(String parentXpath, String childXpath, String[] expectItem) {
		// Step 1: click vao dropdown cho no xo het cac gia tri ra
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		// Step 2: cho cho tat ca cac Item trong dropdown duoc load ra het
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		
		// Step 3: Duyet qua cac phan tu co trong allItems cho den khi thoa man dieu kien
		for (WebElement actualItem : allItems) {
			// "February","July","November" => kiem tra trong allItems co 3 item nay k
			for (String item : expectItem) {
				if(actualItem.getText().equals(item)) {
					// scroll den item can chon (neu item co the nhin thay thi khong can scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);",actualItem);
					// click vao item
					actualItem.click();
					sleepInSecond(1);
					
					List<WebElement> itemAreSelected = driver.findElements(By.xpath("//li[@class='selected']//span"));
					System.out.println("Size item is selected: " + itemAreSelected.size());
					if(expectItem.length==itemAreSelected.size()) {
						break;
					}
					
				}else {
					
				}
				
			}
			
		}
		
	}
	
	public boolean areItemSlected(String[] itemSelectedText) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//span"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText();
		
		System.out.println("Text has choose: "+ allItemSelectedText);
		sleepInSecond(1);
		
		if(numberItemSelected<=3 && numberItemSelected >0) {
			for (String item : itemSelectedText) {
				if(allItemSelectedText.contains(item)) {
					break;
				}
				
			}
			return true;
		}else {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[contains(text(),'of 12 selected')]")).isDisplayed();
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
	public String getHiddenText(String cssLocator) {
		return (String)jsExecutor.executeScript("return document.querySelector(\"" + cssLocator +"\").textContent");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
