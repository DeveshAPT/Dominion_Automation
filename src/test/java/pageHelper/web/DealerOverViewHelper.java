package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.PropertyReader;
import utils.xmlreader;

import java.util.List;

public class DealerOverViewHelper
{
    xmlreader dealerLoc = new xmlreader("src\\test\\resources\\locators\\DealerOverView.xml");
    PropertyReader propertyreader = new PropertyReader();
    int tabCount=0;
    public webHelper webDriver;

    String displaydealer1,displaydealer2, franchise;

    public DealerOverViewHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public void DisplayDealer() throws Exception
    {
        WebElement ele=null;
        if(webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerName")))
        {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerName"));
            displaydealer1=ele.getAttribute("value");
        }
        else if(webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerNameSpan")))
        {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerNameSpan"));
            displaydealer1=ele.getText();
        }
        System.out.println("");
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '"+displaydealer1+"'");
    }

    public void ClickFranchise() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/Franchise")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Franchise'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    public void ChangeFranchise() throws Exception {

        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/Franchise")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Franchise'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        WebElement ele=webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames"));
        displaydealer1= ele.getAttribute("value");
        System.out.println(displaydealer1);

        Select drop=new Select(webDriver.getwebelement(dealerLoc.getlocator("//locators/FranchiseDropdown")));
        WebElement option =drop.getFirstSelectedOption();
        franchise=option.getText();
        ExtentTestManager.getTest().log(LogStatus.PASS, " Selected Franchise  : '"+franchise+"' and Selected  Dealer '"+displaydealer1+"'");

        List<WebElement> op = drop.getOptions();
        int size = op.size();
        for(int i =0; i<size ; i++)
        {
            String options = op.get(i).getText();
            if(!franchise.equalsIgnoreCase(options))
            {
                ExtentTestManager.getTest().log(LogStatus.PASS, "Franchise Changes to : ' "+options+" '");
                drop.selectByIndex(i);
                webDriver.WaitForpageload();
                webDriver.WaitforPageToBeReady();
                break;
            }
        }
        ele=webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames"));
        displaydealer2= ele.getAttribute("value");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Default Dealer Change to : '"+displaydealer2+"'");
        System.out.println(displaydealer1);
        System.out.println(displaydealer2);
        Assert.assertTrue(!displaydealer1.equalsIgnoreCase(displaydealer2),"Default Dealer not Change on Franchise Changes");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Default Dealer Changes on Change in Franchise");


    }

    public void CancelClick() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/Cancel")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : On Cancel");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    public void SaveClick() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/Save")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : On Save");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    public void VerifyDefaultDealerChanges() throws Exception {
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        DisplayDealer();
        ClickFranchise();
        ChangeFranchise();
        CancelClick();
    }

    public  void ChangeDealerVerification() throws Exception
    {
        WebElement ele=null;
        if(webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerName")))
        {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerName"));
            displaydealer1=ele.getAttribute("value");
        }
        else if(webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerNameSpan")))
        {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerNameSpan"));
            displaydealer1=ele.getText();
        }
        System.out.println("");
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '"+displaydealer1+"'");

        ClickFranchise();
        ChangeFranchise();
        SaveClick();

        ele=webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerName"));
        displaydealer2=ele.getAttribute("value");
        System.out.println("");
        System.out.println(displaydealer2);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name Change: '"+displaydealer2+"'");

        Assert.assertTrue(!displaydealer1.equalsIgnoreCase(displaydealer2),"Default Dealer not Change on Franchise Changes");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Change display on screen");
    }
}
