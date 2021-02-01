package testScripts;

import org.testng.annotations.Test;

import junit.framework.Assert;
import utils.driver;

public class Google_Search_Test extends driver {
	
	@Test(groups = { "web" })
	public void FirstTest() throws Exception
	{
		
		Thread.sleep(30000);
		Assert.fail();
	}
}
