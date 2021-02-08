package bddRunner;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.testng.AbstractTestNGCucumberTests;


import com.cucumber.listener.ExtentCucumberFormatter;
import utils.PropertyReader;
import utils.maiUtility;

@CucumberOptions(

features = {"src/test/resources/FeatureFile/"},glue = { "pageHelper" },
plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
tags= "@SIT",
dryRun=false
)

public class BDDRunner extends AbstractTestNGCucumberTests {
public static String message;
	
	@AfterSuite
	 public static void writeExtentReport() throws IOException {
	 PropertyReader prpertyreader = new PropertyReader();
	 System.out.println(System.getProperty("user.dir") +"\\"+prpertyreader.readproperty("reportConfigPath"));
	 Reporter.loadXMLConfig(new File(System.getProperty("user.dir") +"\\"+prpertyreader.readproperty("reportConfigPath")));
	 Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
     Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
     Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
     Reporter.setSystemInfo("Selenium", "3.7.0");
     Reporter.setSystemInfo("Maven", "3.5.2");
     Reporter.setSystemInfo("Java Version", "1.8.0_151");
      //Sent the Email
     System.out.println(prpertyreader.readproperty("to"));
     maiUtility ml=new maiUtility();
     message="<p>Hi All,</p><p>A Test Execution build was triggered and the execution has been completed.</p><p>For the detailed Information’s, please refer the attached html report.</p><p>Thanks,<br/>QA Team</p>";
     ml.SendEmail(message,"target//cucumber-reports//report.html");
     
	}
}
