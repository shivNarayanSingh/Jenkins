package Amazon;

import org.testng.annotations.Test;

public class ExceptedException {
	
	@Test(priority=1, expectedExceptions=ArithmeticException.class)
	public void test1() {
		System.out.println("Test1");
		int n=9/0;
	}
	

}
