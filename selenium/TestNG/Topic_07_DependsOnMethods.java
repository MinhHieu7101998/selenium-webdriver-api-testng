package TestNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_DependsOnMethods {
  @Test
  public void Register() {
	  System.out.println("Dang ky thanh cong");
	  Assert.assertTrue(false);
  }
  @Test(dependsOnMethods = "Register")
  public void Login() {
	  System.out.println("Dang nhap thanh cong");
  }
}
// TC Login phu thuoc vao TC Register, neu TC Register run thanh cong thi TC Login moi duoc chay