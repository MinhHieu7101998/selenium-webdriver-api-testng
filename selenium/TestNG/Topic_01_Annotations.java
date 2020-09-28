package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotations {
  @Test
  public void TC_01() {
	  System.out.println("Run testcase 01");
  }
  @Test
  public void TC_02() {
	  System.out.println("Run testcase 02");
  }
  @Test
  public void TC_03() {
	  System.out.println("Run testcase 03");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Run before Method");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Run after Method");
  }


  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run before Class");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Run after Class");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Run before Test");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Run after Test");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Run before Suite");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Run after Suite");
  }

}
