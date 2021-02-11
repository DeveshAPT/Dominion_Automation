package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import io.qameta.allure.Step;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.xmlreader;

import java.util.List;

public class ReportHelper
{
    CommonFunctions commn = new CommonFunctions();
    public webHelper webDriver;
    xmlreader dealerLoc = new xmlreader("src\\test\\resources\\locators\\DealerOverView.xml");
    xmlreader rptLoc = new xmlreader("src\\test\\resources\\locators\\Report.xml");

    public ReportHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    // Main Functions
    public void CreatingReport_Scheduler() throws Exception {
        ClickOnTab("Scheduler");
        AddNewReportSchedule();
        ReportType("Comments");
        SelectSortBy("Make");
        SelectFilterBy("Age: 1 - 20");
        SelectAllDealer();
        SelectCondition("Certified");
        SelectStatus("Active");
        SelectFrequency("Daily");
        SelectTime("01:00");
        SelectRecipients();
        SaveClick();

    }
    public boolean OptionPresent(List<WebElement> lst, String inpText)
    {
        boolean found = false;
        for (WebElement el : lst) {
            String text = commn.RemoveAllSpace(el.getText());
            if (commn.RemoveAllSpace(inpText).equalsIgnoreCase(text)) {
                found = true;
                break;
            }
        }
        return found;
    }

    //Sub Functions

    @Step("Open Reports Tab {0}")
    public void ClickOnTab(String tabName) throws Exception
    {
        List<WebElement> tabs = webDriver.getwebelements(dealerLoc.getlocator("//locators/Tabs"));
        Assert.assertTrue(tabs.size() > 0, "Report  Tabs "+tabName+" are not Loaded/Display ");
        boolean found = false;
        for (WebElement el : tabs) {
            String text = commn.RemoveAllSpace(el.getText());
            if (commn.RemoveAllSpace(tabName).equalsIgnoreCase(text)) {
                found = true;
                webDriver.Clickon(el);
                Thread.sleep(5000);
                webDriver.WaitForpageload();
                webDriver.WaitforPageToBeReady();
                break;
            }
        }
        Assert.assertTrue(found, "Reports : " + tabName + " not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Report Tab  : '" + tabName + "'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Click on Plus Green Button for New Report Schedule")
    public void AddNewReportSchedule() throws Exception {
        Thread.sleep(5000);
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/AddReport")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Green Plus button for New Report Schedule");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Select Report Type as {0}")
    public void ReportType(String rptType) throws InterruptedException, DocumentException {
        Select reportType=new Select(webDriver.getwebelement(rptLoc.getlocator("//locators/ReportType")));
        List<WebElement> options=reportType.getOptions();
        Assert.assertTrue(OptionPresent(options,rptType),"Report Type "+ rptType+ " not available" );
        reportType.selectByVisibleText(rptType);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Report Type : "+rptType);
    }

    @Step("Select Sort Type As {0}")
    public void SelectSortBy(String sort) throws Exception
    {
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/SortyBy")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        List<WebElement> sortsOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/SortLi"));
        Assert.assertTrue(OptionPresent(sortsOptions,sort),"Sort Type "+ sort+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Sort Type : "+sort);
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/Container")));
    }

    @Step("Select Filter Type As {0}")
    public void SelectFilterBy(String filter) throws Exception
    {
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/Filter"));
        Assert.assertTrue(OptionPresent(filterOptions,filter),"Filter Type "+ filter+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Filter  Type : "+filter);
    }

    @Step("Select All Type Dealers")
    public void SelectAllDealer() throws Exception
    {
       WebElement ele=webDriver.getwebelement(rptLoc.getlocator("//locators/AllDealers"));
       String classname=ele.getAttribute("class");
       if(!commn.RemoveAllSpace(classname).equalsIgnoreCase(commn.RemoveAllSpace("dirty")))
       {
           webDriver.Clickon(ele);
           ExtentTestManager.getTest().log(LogStatus.PASS, "Select All Dealers");
       }
    }

    @Step("Select Condition Type As {0}")
    public void SelectCondition(String cndtn) throws Exception
    {
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/VehicleCondition"));
        Assert.assertTrue(OptionPresent(filterOptions,cndtn),"Condition Type "+ cndtn+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Condition  Type : "+cndtn);
    }

    @Step("Select Status Type As {0}")
    public void SelectStatus(String status) throws Exception
    {
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/VehicleState"));
        Assert.assertTrue(OptionPresent(filterOptions,status),"Condition Type "+ status+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Status  Type : "+status);
    }

    @Step("Select Condition Type As {0}")
    public void SelectFrequency(String frq) throws Exception
    {
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/Frequency"));
        Assert.assertTrue(OptionPresent(filterOptions,frq),"Frequency Type "+ frq+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Frequency Status  Type : "+frq);
    }

    @Step("Select Time of Day Type As {0}")
    public void SelectTime(String time) throws Exception
    {
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/TimeOfDay"));
        Assert.assertTrue(OptionPresent(filterOptions,time),"Time of Day "+ time+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Time of Day : "+time);
    }

    @Step("Select Recipient")
    public void SelectRecipients() throws Exception
    {
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/Recipients"));
       for(WebElement el: filterOptions)
       {
           String className= el.getAttribute("class");
           if(!className.equalsIgnoreCase("dirty"))
           {
               webDriver.Clickon(el);
               ExtentTestManager.getTest().log(LogStatus.PASS, "Select Recipients : "+el.getText() );
               break;
           }

       }
    }

    @Step("Click on Save")
    public void SaveClick() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/SaveBtn")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Save Button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

}
