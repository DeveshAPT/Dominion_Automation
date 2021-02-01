
package pageHelper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageFunctions.web.ExcelWork;
import utils.Log;
import utils.PropertyReader;
import utils.pageController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


import com.cucumber.listener.Reporter;

import core.apiDriver;
import core.baseDriver;
import core.webDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class bddDriver
{
	public static int  itr;
	public static ThreadLocal<String> TestName= new InheritableThreadLocal<>();
	public static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<RequestSpecification> API_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<Response> API_RESPONCE_THREAD_LOCAL = new InheritableThreadLocal<>();
	public static final ThreadLocal<String> Message = new InheritableThreadLocal<>();
	PropertyReader prpertyreader = new PropertyReader();
	String Browser;
	ExcelWork excel = new ExcelWork();
	
	@Before("@API")
	public void APIsetup(Scenario s)
	{
		apiDriver ApiDriver=new baseDriver();
		//API_DRIVER_THREAD_LOCAL.set(ApiDriver.apiinit("https://gorest.co.in/public-api/users/123/posts"));
		//API_DRIVER_THREAD_LOCAL.set(ApiDriver.apiinit("https://rapidapi.p.rapidapi.com/exchange?from=SGD&to=MYR&q=1.0"));
		API_DRIVER_THREAD_LOCAL.set(ApiDriver.apiinit("https://rapidapi.p.rapidapi.com/"));
		
		API_RESPONCE_THREAD_LOCAL.set(null);
		
	}
	
	
	@Before("@SIT")
	public void Websetup(Scenario s) throws Exception
	{
		//String currbrowser=Browser;
		Map<String, HashMap<String, String>> DataSet=excel.ReadTestData("Config");
		System.out.println("");
		System.out.println(DataSet.size());
		HashMap<String, String>map=DataSet.get("Config");
		System.out.println(map.get("Browser"));
		System.out.println(map.get("BaseUrl"));
		webDriver webDriver=new baseDriver();
		WEB_DRIVER_THREAD_LOCAL.set(webDriver.webinit(prpertyreader.readproperty("browser"), "", false,false));
		//WEB_DRIVER_THREAD_LOCAL.set(webDriver.webinit(map.get("Browser"), map.get("BaseUrl"), false,false));
		//WEB_DRIVER_THREAD_LOCAL.set(webDriver.webinit(currbrowser, "https://sit.southernwater.co.uk", false));
		
	}

	@After("@SIT")
	public void TearDown(Scenario s) throws IOException
	{
	
		 if (s.isFailed()) {
			 String sourcePath = "data:image/png;base64,"+((TakesScreenshot)WEB_DRIVER_THREAD_LOCAL.get()).
		                getScreenshotAs(OutputType.BASE64);;
		                Reporter.addScreenCaptureFromPath(sourcePath);
			 
		 }
		WEB_DRIVER_THREAD_LOCAL.get().quit();
		//Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		//Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe /T");
		//Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
	}
	
	
	@org.testng.annotations.BeforeSuite
	public void BeforeSuite(){
	itr=0;
	DOMConfigurator.configure("log4j.xml");
	}
	
	public static WebDriver getWebDriver(){
		return WEB_DRIVER_THREAD_LOCAL.get();
	}
	
	public RequestSpecification getApiDriver(){
		return API_DRIVER_THREAD_LOCAL.get();
	}
}
