package TestNG;

import org.testng.annotations.Test;

public class Topic_06_runLoop {
  @Test(invocationCount =10)
  public void f() {
	  System.out.println("Run Loop 10 lan");
  }
}
