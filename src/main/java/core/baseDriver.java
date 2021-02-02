package core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;

import java.util.logging.Level;

import org.apache.http.params.CoreConnectionPNames;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.zaproxy.clientapi.core.ClientApi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class baseDriver implements apiDriver, webDriver,desktopDriver,mobileDriver
{

	public static ClientApi ZapScanner;

	public static Process p;
	
	public WebDriver webinit(String browser, String BaseURL, Boolean Grid, Boolean proxyRequired) throws Exception {
		WebDriver dr = null;
		System.out.println("In Web Initiator");
		if(!Grid==true) {
		if(browser.equalsIgnoreCase("chrome"))
		{

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			if(proxyRequired==true) {
				Proxy proxy = new Proxy();
				proxy.setAutodetect(false);
				proxy.setHttpProxy("localhost:8777");
				proxy.setSslProxy("localhost:8777");
//			static Logger log = Logger.getLogger(ZapLoginTest.class.getName());
				final String ZAP_PROXYHOST = "localhost";
				final int ZAP_PROXYPORT = 8777;
				//final String ZAP_APIKEY = null;

//
				final String[] policyNames = {"directory-browsing", "cross-site-scripting", "sql-injection",
						"path-traversal", "remote-file-inclusion", "server-side-include", "script-active-scan-rules",
						"server-side-code-injection", "external-redirect", "crlf-injection"};
				int currentScanID;
				ZapScanner = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT, null);
				System.out.println("Zap Scanner" + ZapScanner);
				options.setCapability(CapabilityType.PROXY, proxy);
				options.addArguments("--ignore-certificate-errors");
			}
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.popups", 1);
			prefs.put("download.default_directory", System.getProperty("user.dir")+"/src/Data/Downloads");

			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-popup-blocking");


			//HeadLess
			options.addArguments("--headless");
			options.addArguments("--window-size=1920,1080");




			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			LoggingPreferences logs = new LoggingPreferences(); 
		    logs.enable(LogType.DRIVER, Level.ALL); 
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver","./lib/chromedriver.exe");
			dr= new ChromeDriver(capabilities);
			dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
		}
		else if (browser.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver","lib\\msedgedriver.exe");
			dr= new EdgeDriver();	
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
			dr.manage().window().maximize();
			System.out.println("For IE Browser");
		}
		else if (browser.equalsIgnoreCase("ie"))
		{
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("nativeEvents",false);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability("requireWindowFocus", true);
			System.setProperty("webdriver.ie.driver","lib\\IEDriverServer.exe");
			dr = new InternetExplorerDriver(caps);
			
			//dr= new InternetExplorerDriver();
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
			dr.manage().window().maximize();
			System.out.println("For IE Browser");
		}

		else if (browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
			//System.setProperty("webdriver.gecko.driver","./lib/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			//capabilities.setCapability("marionette", true);
			dr = new FirefoxDriver(capabilities);
			//dr=new marionetteDriver();
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
			dr.manage().window().maximize();
			System.out.println("For FF Browser");
			//dr.get(BaseURL);
		}

		}
		
	  return dr;
	}
	
	/*
	public WebDriver webinit(String browser, String BaseURL, Boolean Grid) throws Exception 
	{
		WebDriver dr = null;
		System.out.println("In Web Initiator");
		if(!Grid==true) {
		if(browser.equalsIgnoreCase("chrome"))
		{ 
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.popups", 1);
			prefs.put("download.default_directory", System.getProperty("user.dir")+"\\src\\Data\\Downloads");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-popup-blocking");
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			LoggingPreferences logs = new LoggingPreferences(); 
		    logs.enable(LogType.DRIVER, Level.ALL); 
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
			dr= new ChromeDriver(capabilities);
			
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
		}
		else if (browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver","lib\\msedgedriver.exe");
			dr= new EdgeDriver();	
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
			dr.manage().window().maximize();
			System.out.println("For IE Browser");
		}
		
		else if (browser.equalsIgnoreCase("ie"))
		{
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability("nativeEvents",false);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability("requireWindowFocus", true);
			System.setProperty("webdriver.ie.driver","lib\\IEDriverServer.exe");
			dr = new InternetExplorerDriver(caps);
			
			//dr= new InternetExplorerDriver();
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
			dr.manage().window().maximize();
			System.out.println("For IE Browser");
			
			
			//Old Comments Start
			/*DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.popups", 1);
			prefs.put("download.default_directory", System.getProperty("user.dir")+"\\src\\Data\\Downloads");
			FirefoxOptions options = new FirefoxOptions();
			//options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-popup-blocking");
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			LoggingPreferences logs = new LoggingPreferences(); 
		    logs.enable(LogType.DRIVER, Level.ALL); 
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver","lib\\geckodriver.exe");
			dr= new ChromeDriver(capabilities);
			dr.manage().getCookies();
			//Old Comments End
			
		}
		
		else if (browser.equalsIgnoreCase("Firefox"))
		{
			
			System.setProperty("webdriver.gecko.driver","lib\\geckodriver.exe");
			dr= new FirefoxDriver();
			dr.manage().deleteAllCookies();
			dr.get(BaseURL);
			dr.manage().window().maximize();
			System.out.println("For FireFox Browser");
			
			
		}

		}
		
	  return dr;
	}*/
	
	public WebDriver webinit(String browser, Boolean Grid) throws Exception {
		WebDriver dr = null;
		System.out.println("In Web Initiator");
		if(!Grid==true) {
		if(browser.equalsIgnoreCase("chrome"))
		{ 
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("profile.default_content_setting_values.popups", 1);
			prefs.put("download.default_directory", System.getProperty("user.dir")+"\\src\\Data\\Downloads");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-popup-blocking");
			capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
			LoggingPreferences logs = new LoggingPreferences(); 
		    logs.enable(LogType.DRIVER, Level.ALL); 
			capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver","lib\\chromedriver.exe");
			dr= new ChromeDriver(capabilities);
			dr.manage().getCookies();
			//dr.manage().deleteAllCookies();
			//dr.get(BaseURL);
		}
		else if (browser.equals("ie"))
		{
			System.out.println("For IE Browser");
		}
		
		else if (browser.equals("Firefox"))
		{
			
		}

		}
		
	  return dr;
	}

	public WindowsDriver desktopinit(String Application) throws Exception {
		String command="C:/Program Files (x86)/Windows Application Driver/WinAppDriver.exe";

		ProcessBuilder processBuilder = new ProcessBuilder(command).inheritIO();
		p=processBuilder.start();

		WindowsDriver desktopSession = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", Application);
		capabilities.setCapability("platformName","Windows");
		capabilities.setCapability("deviceName", "WindowsPC");
		desktopSession = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
		desktopSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return desktopSession;
	}
	
	public AppiumDriver mobileinit(String Device, String appPackage,String AppActivity,String Platform,String Application) throws Exception {
//		String command="C:/Program Files (x86)/Windows Application Driver/WinAppDriver.exe";
//
//		ProcessBuilder processBuilder = new ProcessBuilder(command).inheritIO();
//		p=processBuilder.start();
//		DesiredCapabilities Appiumcap = null;
//		AppiumDriverLocalService service;
//		AppiumServiceBuilder builder;
//		Appiumcap = new DesiredCapabilities();
//		Appiumcap.setCapability("noReset", "false");
//
//		//Build the Appium service
//		builder = new AppiumServiceBuilder();
//		builder.withIPAddress("127.0.0.1");
//		builder.usingPort(4783);
//		builder.withCapabilities(Appiumcap);
//		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
//
//		//Start the server with the builder
//		service = AppiumDriverLocalService.buildService(builder);
//		service.start();
		AppiumDriver mobileDriver;

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME,Platform);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Device);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		cap.setCapability(MobileCapabilityType.NO_RESET, "true");
		if(!Application.equalsIgnoreCase("")) {
			cap.setCapability(MobileCapabilityType.APP, Application);
		}
		else
		{
			cap.setCapability("appPackage", appPackage);
			cap.setCapability("appActivity", AppActivity);
		}
		mobileDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4783/wd/hub"),cap);
		return mobileDriver;
	}
	
	
	public RequestSpecification apiinit(String baseurl) {
		// TODO Auto-generated method stub
		RequestSpecification httpRequest;
		System.out.println("In API Initiator");
		RestAssured.baseURI = baseurl;
		@SuppressWarnings("deprecation")
		RestAssuredConfig config = RestAssured.config()
		        .httpClient(HttpClientConfig.httpClientConfig()
		                .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 100000)
		                .setParam(CoreConnectionPNames.SO_TIMEOUT, 100000));
		//System.out.println(RestAssured.baseURI);
		httpRequest = RestAssured.given().config(config);
		return httpRequest;
	}

	public ClientApi ReturnZapScanner() {
		System.out.println("Zap Scanner Return method"+ZapScanner);
		return ZapScanner;
	}

	public Process ReturncurrentProcess() {
		System.out.println("Zap Scanner Return method"+ZapScanner);
		return p;

	}
	
}
