package Amazon;

import org.testng.annotations.Test;

public class DependonMethod {
	
	@Test(priority=1)
	public void test1() {
		System.out.println("Test1");
		int n=9/0;
	}
	
	@Test(priority= 2, dependsOnMethods="test1")
	public void test2() {
		System.out.println("Test2");
	}
	
	@Test(priority=3,dependsOnMethods="test2")
	public void test3() {
		System.out.println("Test3");
	}

}
