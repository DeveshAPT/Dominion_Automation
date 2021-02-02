package Listeners;

import com.relevantcodes.extentreports.LogStatus;


import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import utils.Log;
import utils.PropertyReader;
import utils.driver;
import utils.maiUtility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import Reporter.ExtentManager;
import Reporter.ExtentTestManager;

public class TestListener extends driver implements ITestListener {


    String Filename;
    PropertyReader pr = new PropertyReader();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Before starting all tests, below method runs.
    public void onStart(ITestContext iTestContext) {
        Log.info("I am on Start Test " + iTestContext.getName());
        System.out.println("I am on Start Test " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.getWebDriver());


        System.out.println("Driver instance in Listemer" + this.getWebDriver());
    }

    //After ending all tests, below method runs.
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am on Finish method " + iTestContext.getName());
        System.out.println("I am on Finish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!


        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
        System.out.println("In the end");

    }

    public void onTestStart(ITestResult iTestResult) {
        Log.info("I am on TestStart method " + getTestMethodName(iTestResult) + " start");
        System.out.println("I am on TestStart method " + getTestMethodName(iTestResult) + " start");
        //Start operation for extentreports.
        //ExtentTestManager.  TestName
        Log.info("I am in onStart method " + iTestResult.getTestContext().getAttribute("testName"));
        System.out.println("I am on TestStart method " + getTestMethodName(iTestResult) + " start");
        Log.info("I am in onStart method " + TestName.get().toString());
        System.out.println("I am in onStart method " + TestName.get().toString());
        DateFormat df = new SimpleDateFormat("yyyyMMdd-HHmm");
        Filename = iTestResult.getName() + "-" + df.format(new Date());
        //  Filename=iTestResult.getTestContext().getAttribute("testName").toString();
        System.out.println("Test name from Exfel" + Filename);

        /*ExtentTestManager.startTest(iTestResult.getTestContext().getSuite().getXmlSuite().getName().toString() +
                "-" + iTestResult.getTestContext().getCurrentXmlTest().getName().toString() +
                "-" + iTestResult.getTestContext().getAttribute("testName").toString() +
                "-" + TestName.get().toString(), " THIs is Demo Test");

        ExtentTestManager.startTest(iTestResult.getTestContext().getSuite().getXmlSuite().getName().toString() +
                                    " - " + iTestResult.getTestContext().getCurrentXmlTest().getName().toString() +
                                    " - "+ iTestResult.getMethod().getMethodName().toString(),"");*/

        ExtentTestManager.startTest(iTestResult.getTestContext().getCurrentXmlTest().getName().toString() +
                " - "+ iTestResult.getMethod().getMethodName().toString(),"");

        //"-"+ iTestResult.getMethod().getMethodName().toString()
    }

    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("I am on TestSuccess method " + getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, getTestMethodName(iTestResult) + " : Test Method has been passed");
        //ExtentTestManager.endTest();


        ExtentManager.getReporter().flush();
    }


    public void onTestFailure(ITestResult iTestResult) {
        // Log.info("I am on TestFailure method " +  getTestMethodName(iTestResult) + " failed");
        //iTestResult.setStatus(0);
        //Get driver from BaseTest and assign to local webdriver variable.
        ExtentTestManager.getTest().log(LogStatus.FAIL, getTestMethodName(iTestResult) + " : Test Method has been Failed");
        Object testClass = iTestResult.getInstance();
        //iTestResult.setStatus(arg0);
        WebDriver webDriver = ((driver) testClass).getWebDriver();

        //Take base64Screenshot screenshot.
        String base64Screenshot = "";
        String Message = "";
        try
        {
            base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
                    getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            base64Screenshot = "";
        }
        try
        {
            Message = iTestResult.getThrowable().getMessage() != null ? iTestResult.getThrowable().getMessage().toString() :
                    iTestResult.getThrowable().getCause().toString();
        }
        catch (Exception e)
        {
            Message = "Intenational Failed";
        }

        System.out.println("Result Messages" + Message);
        //Extentreports log and screenshot operations for failed tests.
        if (base64Screenshot.contains("data:image/png;base64,"))
        {
            ExtentTestManager.getTest().log(LogStatus.FAIL, Message +
                    ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        }
        else
            {
            ExtentTestManager.getTest().log(LogStatus.FAIL, Message);
        }
        if(webDriver instanceof WebDriver)
        {
            System.out.println("Screenshot capture for Test Cases : "+  getTestMethodName(iTestResult));
            SaveFailureScreenShots(webDriver);
        }
        SaveTextLog(getTestMethodName(iTestResult)+ " failed and screenshot captured");
        ExtentManager.getReporter().flush();
    }

    public void onTestError(ITestResult iTestResult)
    {
        Log.info("I am on TestSuccess method " + getTestMethodName(iTestResult) + " errored");
        //Extentreports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.ERROR, getTestMethodName(iTestResult) + " : Test Method has been errored");
        Object testClass = iTestResult.getInstance();
        //iTestResult.setStatus(arg0);
        WebDriver webDriver = ((driver) testClass).getWebDriver();

        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) getWebDriver()).
                getScreenshotAs(OutputType.BASE64);


        ExtentManager.getReporter().flush();
        //Extentreports log and screenshot operations for failed tests.
        //  ExtentTestManager.getTest().log(LogStatus.ERROR,"Test Errored",
        //         ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }

    public void onTestSkipped(ITestResult iTestResult) {
        Log.info("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");

        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");


        ExtentManager.getReporter().flush();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));


        ExtentManager.getReporter().flush();
    }

    @Attachment
    public byte[] SaveFailureScreenShots(WebDriver webDriver)
    {
        byte[] screenshot=((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        //iTestResult(screenshot, "image/png");
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String SaveTextLog(String message) {
        return message;
    }
}