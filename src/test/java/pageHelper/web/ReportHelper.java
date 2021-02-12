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
        SelectAllRecipient();
        SelectOneRecipients();
        SaveClick();

    }

    public void EditExistingReportAndDisable() throws Exception {
        OpenExistingSchedule();
        EditExistingReport();
        SaveClick();
        DisableScheduleReport();
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
    {   webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/SortyBy")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        List<WebElement> sortsOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/SortLi"));

        boolean found = false;
        for (WebElement el : sortsOptions) {
            String text = commn.RemoveAllSpace(el.getText());
            System.out.println(text);
            text=text.replace("|","");
            System.out.println(text);
            if (commn.RemoveAllSpace(sort).equalsIgnoreCase(text)) {
                found = true;
                break;
            }
        }

        Assert.assertTrue(found,"Sort Type "+ sort+ " not available" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Sort Type : "+sort);
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/Container")));

    }

    @Step("Select Filter Type As {0}")
    public void SelectFilterBy(String filter) throws Exception
    {
        Select filters=new Select(webDriver.getwebelement(rptLoc.getlocator("//locators/Filter")));
        List<WebElement> filterOptions=filters.getOptions();
        Assert.assertTrue(OptionPresent(filterOptions,filter),"Filter Type "+ filter+ " not available" );
        filters.selectByVisibleText(filter);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Filter  Type : "+filter);
    }

    @Step("Select All Type Dealers")
    public void SelectAllDealer() throws Exception
    {
       WebElement ele=webDriver.getwebelement(rptLoc.getlocator("//locators/AllDealers"));
       String classname=ele.getAttribute("class");
        String classname1=ele.getAttribute("className");
       if(!(commn.RemoveAllSpace(classname).equalsIgnoreCase("dirty")||
               commn.RemoveAllSpace(classname1).equalsIgnoreCase("dirty")))
       {
           webDriver.Clickon(ele);
           ExtentTestManager.getTest().log(LogStatus.PASS, "Select All Dealers");
       }
        webDriver.Clickon(ele);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select All Dealers");
    }

    @Step("Select Condition Type As {0}")
    public void SelectCondition(String cndtn) throws Exception
    {
        Select conditions=new Select(webDriver.getwebelement(rptLoc.getlocator("//locators/VehicleCondition")));
        List<WebElement> filterOptions=conditions.getOptions();
        Assert.assertTrue(OptionPresent(filterOptions,cndtn),"Condition Type "+ cndtn+ " not available" );
        conditions.selectByVisibleText(cndtn);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Condition  Type : "+cndtn);
    }

    @Step("Select Status Type As {0}")
    public void SelectStatus(String status) throws Exception
    {
        Select statusList=new Select(webDriver.getwebelement(rptLoc.getlocator("//locators/VehicleState")));
        List<WebElement> filterOptions=statusList.getOptions();
        Assert.assertTrue(OptionPresent(filterOptions,status),"Condition Type "+ status+ " not available" );
        statusList.selectByVisibleText(status);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select Status  Type : "+status);
    }

    @Step("Select Condition Type As {0}")
    public void SelectFrequency(String frq) throws Exception
    {
        Select freqList=new Select(webDriver.getwebelement(rptLoc.getlocator("//locators/Frequency")));
        List<WebElement> filterOptions=freqList.getOptions();
        Assert.assertTrue(OptionPresent(filterOptions,frq),"Frequency Type "+ frq+ " not available" );
        freqList.selectByVisibleText(frq);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Frequency Status  Type : "+frq);
    }

    @Step("Select Time of Day Type As {0}")
    public void SelectTime(String time) throws Exception
    {
        Select times=new Select(webDriver.getwebelement(rptLoc.getlocator("//locators/TimeOfDay")));
        List<WebElement> filterOptions=times.getOptions();
        Assert.assertTrue(OptionPresent(filterOptions,time),"Time of Day "+ time+ " not available" );
        times.selectByVisibleText(time);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Time of Day : "+time);
    }

    @Step("Select All Recipient")
    public void SelectAllRecipient() throws Exception
    {
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/AllRecipients")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select All Recipient");
    }

    @Step("Select One Recipient")
    public void SelectOneRecipients() throws Exception
    {
        WebElement all=webDriver.getwebelement(rptLoc.getlocator("//locators/AllRecipients"));
        String classVal=all.getAttribute("class");
        String classVal1=all.getAttribute("className");
        System.out.println(classVal);
        System.out.println(classVal1);
        if(classVal.equalsIgnoreCase("dirty")||classVal1.equalsIgnoreCase("dirty"))
        {   webDriver.Clickon(all);
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
        }
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/Recipients"));
       for(WebElement el: filterOptions)
       {

           classVal1=el.getAttribute("className");
           String className= el.getAttribute("class");
           if(!(className.equalsIgnoreCase("dirty")||classVal1.equalsIgnoreCase("dirty")))
           {
               webDriver.Clickon(el);
               ExtentTestManager.getTest().log(LogStatus.PASS, "Select Recipients : "+el.getText() );
               break;
           }

       }
    }

    @Step("Adding More Then One Recipient")
    public void MoreThenRecipients() throws Exception
    {
        WebElement all=webDriver.getwebelement(rptLoc.getlocator("//locators/AllRecipients"));
        String classVal=all.getAttribute("class");
        String classVal1=all.getAttribute("className");
        if(classVal.equalsIgnoreCase("dirty")||classVal1.equalsIgnoreCase("dirty"))
        {   webDriver.Clickon(all);
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
        }
        List<WebElement> filterOptions=webDriver.getwebelements(rptLoc.getlocator("//locators/Recipients"));
        int count=0;
        for(WebElement el: filterOptions)
        {
            classVal1=el.getAttribute("className");
            String className= el.getAttribute("class");
            if(!(className.equalsIgnoreCase("dirty")||classVal1.equalsIgnoreCase("dirty")))
            {
                count++;
                webDriver.Clickon(el);
                ExtentTestManager.getTest().log(LogStatus.PASS, "Select Recipients : "+el.getText() );

            }
            if(count>1)
                break;

        }
    }

    @Step("Click on Save")
    public void SaveClick() throws Exception
    {
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/SaveBtn")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Save Button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Open Existing Report Schedule")
    public void OpenExistingSchedule() throws Exception {
        List<WebElement> rows=webDriver.getwebelements(rptLoc.getlocator("//locators/ExistingReports"));
        Assert.assertTrue(rows.size()>0,"No Existing Report Schedule is Present");
        webDriver.Clickon(rows.get(0));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Open the Existing Report");
    }

    @Step("Open Existing Report Schedule")
    public void EditExistingReport() throws Exception {
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        MoreThenRecipients();
    }

    @Step("Click on Disable to Disable the Report Scheduler")
    public void DisableScheduleReport() throws Exception {

        OpenExistingSchedule();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        webDriver.Clickon(webDriver.getwebelement(rptLoc.getlocator("//locators/DisableBtn")));
        webDriver.AcceptJavaScriptMethod();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Disable Button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }
}
