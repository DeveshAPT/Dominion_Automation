package pageFunctions.web;

import org.dom4j.DocumentException;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import core.webHelper;
import utils.PropertyReader;
import utils.xmlreader;

public class AssertionExceptionManager {
	public webHelper ExceptionDriver;

	public AssertionExceptionManager(webHelper dr) {
		System.out.println("I am in Exception Page");
		ExceptionDriver = dr;
	}

	public void IsTrue(boolean condition, String exMessage) {
		try {
			Assert.assertTrue(condition, exMessage);
		} catch (AssertionError e) {
			Reporter.addStepLog(exMessage);
			Reporter.addStepLog("Below is the Technical Exception");
			// Assert.fail();
			throw e;
		}
	}

	public void IsTrue(boolean condition, String exMessage, String passMessage) {
		try {
			Assert.assertTrue(condition, exMessage);
			Reporter.addStepLog(passMessage);
		} catch (AssertionError e) {
			Reporter.addStepLog(exMessage);
			Reporter.addStepLog("Below is the Technical Exception");
			// Assert.fail();
			throw e;
		}
	}

	public void AddPassStep(String passMessage) {
		Reporter.addStepLog(passMessage);
	}
}
