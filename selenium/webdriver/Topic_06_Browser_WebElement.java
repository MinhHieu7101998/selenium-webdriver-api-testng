package webdriver;
	
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Browser_WebElement {
	WebDriver driver;
	By emailTextboxBy = By.xpath("//input[@id='mail']");
	By educationTextboxBy = By.xpath("//textarea[@id='edu']");
	By ageUnderRadioBy = By.xpath("//input[@id='under_18']");
	By jobRole01DropdownBy = By.xpath("//select[@id='job1']");
	By jobRole02DropdownBy = By.xpath("//select[@id='job2']");
	By developmentCheckboxBy = By.xpath("//input[@id='development']");
	By slider01By = By.xpath("//input[@id='slider-1']");
	By passowrdTextboxBy = By.xpath("//input[@name='user_pass']");
	By disabelAgeRadioButtonBy = By.xpath("//input[@id='radio-disabled']");
	By biographyTextAreaBy = By.xpath("//textarea[@id='bio']");
	By jobRole03DropdownBy = By.xpath("//select[@id='job3']");
	By interestsDisabledCheckboxBy = By.xpath("//input[@id='check-disbaled']");
	By slider02By = By.xpath("//input[@id='slider-2']");
	By javaCheckboxBy = By.xpath("//input[@id='java']");
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//WebElement emailTextbox = driver.findElement(emailTextboxBy);
	}

	@Test
	public void TC_01_isDisplayed() throws InterruptedException {
//		if(driver.findElement(emailTextboxBy).isDisplayed()) {
//			System.out.println("Element is displayed with: " + emailTextboxBy);
//			driver.findElement(emailTextboxBy).sendKeys("Automation Testing");
//		}else {
//			System.out.println("Element is not displayed with: " + emailTextboxBy);
//		}
//		
//		if(driver.findElement(educationTextboxBy).isDisplayed()) {
//			System.out.println("Element is displayed with: " + educationTextboxBy);
//			driver.findElement(educationTextboxBy).sendKeys("Automation Testing");
//		}else {
//			System.out.println("Element is not displayed with: " + educationTextboxBy);
//		}
//		
//		if(driver.findElement(ageUnderRadioBy).isDisplayed()) {
//			System.out.println("Element is displayed with: " + ageUnderRadioBy);
//			driver.findElement(ageUnderRadioBy).click();
//		}else {
//			System.out.println("Element is not displayed with: " + ageUnderRadioBy);
//		}
		if(_isElementDisplayed(emailTextboxBy)) {
			_senKeyToElement(emailTextboxBy, "Automation testing");
		}
		if(_isElementDisplayed(educationTextboxBy)) {
			_senKeyToElement(educationTextboxBy, "Automation testing");
		}
		if(_isElementDisplayed(ageUnderRadioBy)){
				_clickElement(ageUnderRadioBy);
			}
		Thread.sleep(5000);
	}

	@Test
	public void TC_02_isEnabled() {
		driver.navigate().refresh();
//		if(driver.findElement(emailTextboxBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + emailTextboxBy);
//		}else {
//			System.out.println("Element is disabled with: " + emailTextboxBy);
//		}
//		if(driver.findElement(educationTextboxBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + educationTextboxBy);
//		}else {
//			System.out.println("Element is disabled with: " + educationTextboxBy);
//		}
//		if(driver.findElement(ageUnderRadioBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + ageUnderRadioBy);
//		}else {
//			System.out.println("Element is disabled with: " + ageUnderRadioBy);
//		}
//		if(driver.findElement(jobRole01DropdownBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + jobRole01DropdownBy);
//				}else {
//			System.out.println("Element is disabled with: " + jobRole01DropdownBy);
//		}
//		if(driver.findElement(jobRole02DropdownBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + jobRole02DropdownBy);
//		}else {
//			System.out.println("Element is disabled with: " + jobRole02DropdownBy);
//		}
//		if(driver.findElement(developmentCheckboxBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + developmentCheckboxBy);
//		}else {
//			System.out.println("Element is disabled with: " + developmentCheckboxBy);
//		}
//		if(driver.findElement(slider01By).isEnabled()) {
//			System.out.println("Element is enabled with: " + slider01By);
//		}else {
//			System.out.println("Element is disabled with: " + slider01By);
//		}
		if(_isEnabled(emailTextboxBy));
		if(_isEnabled(ageUnderRadioBy));
		if(_isEnabled(educationTextboxBy));
		if(_isEnabled(jobRole01DropdownBy));
		if(_isEnabled(jobRole02DropdownBy));
		if(_isEnabled(developmentCheckboxBy));
		if(_isEnabled(slider01By));
		if(_isEnabled(passowrdTextboxBy));
		if(_isEnabled(disabelAgeRadioButtonBy));
		if(_isEnabled(biographyTextAreaBy));
		if(_isEnabled(jobRole03DropdownBy));
		if(_isEnabled(interestsDisabledCheckboxBy));
		if(_isEnabled(slider02By));
		
//		driver.navigate().refresh();
//		
//		if(driver.findElement(passowrdTextboxBy).isEnabled()) {
//			System.out.println("Element is enabled with: " + passowrdTextboxBy);
//		}else {
//			System.out.println("Element is disabled with: " + passowrdTextboxBy);
//		}
//		if(driver.findElement(disabelAgeRadioButtonBy).isEnabled()) {
//			System.out.println("Element is enabled with: " +disabelAgeRadioButtonBy);
//		}else {
//			System.out.println("Element is disabled with: " + disabelAgeRadioButtonBy);
//		}
//		if(driver.findElement(biographyTextAreaBy).isEnabled()) {
//			System.out.println("Element is enabled with: " +biographyTextAreaBy);
//		}else {
//			System.out.println("Element is disabled with: " + biographyTextAreaBy);
//		}
//		if(driver.findElement(jobRole03DropdownBy).isEnabled()) {
//			System.out.println("Element is enabled with: " +jobRole03DropdownBy);
//		}else {
//			System.out.println("Element is disabled with: " + jobRole03DropdownBy);
//		}
//		if(driver.findElement(interestsDisabledCheckboxBy).isEnabled()) {
//			System.out.println("Element is enabled with: " +interestsDisabledCheckboxBy);
//		}else {
//			System.out.println("Element is disabled with: " + interestsDisabledCheckboxBy);
//		}
//		if(driver.findElement(slider02By).isEnabled()) {
//			System.out.println("Element is enabled with: " +slider02By);
//		}else {
//			System.out.println("Element is disabled with: " + slider02By);
//		}
		
	}

	@Test
	public void TC_03_isSelected() throws InterruptedException {
		driver.navigate().refresh();
//		driver.findElement(ageUnderRadioBy).click();
//		driver.findElement(javaCheckboxBy).click();
//		if(driver.findElement(ageUnderRadioBy).isSelected()) {
//			System.out.println("Element is selected with: " + ageUnderRadioBy);
//		}else {
//			System.out.println("Element is de-selected with: " + ageUnderRadioBy);
//		}
//		if(driver.findElement(javaCheckboxBy).isSelected()) {
//			System.out.println("Element is selected with: " + javaCheckboxBy);
//		}else {
//			System.out.println("Element is de-selected with: " + javaCheckboxBy);
//		}
//		driver.findElement(ageUnderRadioBy).click();
//		driver.findElement(javaCheckboxBy).click();
//		
//		if(driver.findElement(javaCheckboxBy).isSelected()) {
//			System.out.println("Element is selected with: " + javaCheckboxBy);
//		}else {
//			System.out.println("Element is de-selected with: " + javaCheckboxBy);
//		}
//		Thread.sleep(10000);
		_clickElement(ageUnderRadioBy);
		_clickElement(javaCheckboxBy);
		if(_isSelected(ageUnderRadioBy));
		if(_isSelected(javaCheckboxBy));
		_clickElement(ageUnderRadioBy);
		_clickElement(javaCheckboxBy);
		if(_isSelected(ageUnderRadioBy));
		if(_isSelected(javaCheckboxBy));
		Thread.sleep(5000);
		
	}
	public boolean _isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println("Element is displayed with: " + by);
			return true;
		}else {
			System.out.println("Element is not displayed");
			return false;
		}
	}
	public void _senKeyToElement(By by,String valueText) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(valueText);
	}
	public void _clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	public boolean _isEnabled(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element is enabled with: " + by);
			return true;
		}else {
			System.out.println("Element is disabled");
			return false;
		}
	}
	public boolean _isSelected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element is selected with: " + by);
			return true;
		}else {
			System.out.println("Element is de-selected");
			return false;
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
