package TestCases;

import org.testng.annotations.Test;

public class Scenario1 extends DriverScript.Base {
	@Test(priority=1)
	public void verifyAppTitle() {
		System.out.println("Running TC1");
		System.out.println(getDriver().getTitle());
	}

}
