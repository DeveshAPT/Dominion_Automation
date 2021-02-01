package testScripts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utils.driver;

public class Accessibility extends driver 
{

	@Parameters("Browser")
	@Test(groups = { "web" })
	public void Dominion_Accessibility(String Browser) throws Throwable {
		// PayBill

			domLogin.get().OpenDominion();
			Run_AccessibilityTest();

			domLogin.get().DominionLogin();
			Run_AccessibilityTest();


	}


}


//Run_AccessibilityTest();