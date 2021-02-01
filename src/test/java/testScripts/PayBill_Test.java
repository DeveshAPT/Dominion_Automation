package testScripts;

import org.testng.annotations.Test;

import junit.framework.Assert;
import utils.driver;


public class PayBill_Test extends driver
{
	@Test(groups = { "web" })
	public void FirstTest() throws Exception
	{
		//PayBill.get().SouthernWaterPayBill();
		//Meter.get().SouthernWaterSumbitMeterReading();
		//Southern.get().SouthernWaterPayBillDetail();
		Thread.sleep(30000);
		Assert.fail();
	}

}
