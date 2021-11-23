package Amazon;

import org.testng.annotations.Test;

public class InvocationCount {
	
	@Test(invocationCount=10)
	public void test1() {
		System.out.println("User created");
		
	}
	

}
