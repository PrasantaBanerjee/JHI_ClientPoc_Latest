package Scenarios;

import org.testng.annotations.Test;

public class Scenario3 extends DriverScript.Base {
	@Test(priority=3)
	public void verifyAppSource() {
		System.out.println("Running TC3");
		System.out.println(getDriver().getPageSource());
	}

}
