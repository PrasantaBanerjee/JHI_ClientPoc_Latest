package TestCases;

import org.testng.annotations.Test;

public class Scenario2 extends DriverScript.Base {
	@Test(priority=2)
	public void verifyAppUrl() {
		System.out.println("Running TC2");
		System.out.println(getDriver().getCurrentUrl());
	}

}
