package TestNG;

import org.testng.annotations.Test;

public class Topic_02_Groups {
	@Test(groups="mobile")
	  public void TC_01() {
		  System.out.println("Run testcase 01");
	  }
	  @Test
	  public void TC_02() {
		  System.out.println("Run testcase 02");
	  }
	  @Test(groups="mobile")
	  public void TC_03() {
		  System.out.println("Run testcase 03");
	  }
}
