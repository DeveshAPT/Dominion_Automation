package pageHelper.web;
import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.xmlreader;
import java.util.List;

public class DealerOverViewHelper {
    xmlreader dealerLoc = new xmlreader("src\\test\\resources\\locators\\DealerOverView.xml");
    public webHelper webDriver;
    String displaydealer1, displaydealer2, franchise;

    public DealerOverViewHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public String DisplayDealer() throws Exception
    {
        String temp=null;
        WebElement ele ;
        if (webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerName"))) {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerName"));
            temp = ele.getAttribute("value");
        } else if (webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerNameSpan"))) {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerNameSpan"));
            temp = ele.getText();
        }
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '" + temp + "'");
        return  temp;
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

        WebElement ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames"));
        displaydealer1 = ele.getAttribute("value");
        System.out.println(displaydealer1);

        Select drop = new Select(webDriver.getwebelement(dealerLoc.getlocator("//locators/FranchiseDropdown")));
        WebElement option = drop.getFirstSelectedOption();
        franchise = option.getText();
        ExtentTestManager.getTest().log(LogStatus.PASS, " Selected Franchise  : '" + franchise + "' and Selected  Dealer '" + displaydealer1 + "'");

        List<WebElement> op = drop.getOptions();
        int size = op.size();
        for (int i = 0; i < size; i++) {
            String options = op.get(i).getText();
            if (!franchise.equalsIgnoreCase(options)) {
                ExtentTestManager.getTest().log(LogStatus.PASS, "Franchise Changes to : ' " + options + " '");
                drop.selectByIndex(i);
                webDriver.WaitForpageload();
                webDriver.WaitforPageToBeReady();
                break;
            }
        }
        ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames"));
        displaydealer2 = ele.getAttribute("value");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Default Dealer Change to : '" + displaydealer2 + "'");
        System.out.println(displaydealer1);
        System.out.println(displaydealer2);
        Assert.assertFalse(displaydealer1.equalsIgnoreCase(displaydealer2), "Default Dealer not Change on Franchise Changes");
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

    public void ChangeDealer() throws Exception {

        Select drop = new Select(webDriver.getwebelement(dealerLoc.getlocator("//locators/FranchiseDropdown")));
        WebElement option = drop.getFirstSelectedOption();
        franchise = option.getText();

        WebElement ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames"));
        displaydealer2 = ele.getAttribute("value");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Default Dealer Change to : '" + displaydealer2 + "'");

        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : On Dealers List");

        List<WebElement> dealers = webDriver.getwebelements(dealerLoc.getlocator("//locators/DealerPopup"));
        if (dealers.size() > 1) {
            for (int i = 0; i < dealers.size(); i++) {
                WebElement btn = dealers.get(i);
                String temp = btn.getText();
                if (temp.equalsIgnoreCase(displaydealer2)) {
                    webDriver.Clickon(btn);
                    webDriver.WaitForpageload();
                    webDriver.WaitforPageToBeReady();
                    break;
                }
            }
        } else
            ExtentTestManager.getTest().log(LogStatus.PASS, "Only one Dealer('" + displaydealer2 + "') is Present for Franchise('" + franchise + "') ");

    }

    @Step("Verify Default Dealer Populate on Change in Franchise")
    public void VerifyDefaultDealerChanges() throws Exception {
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        DisplayDealer();
        ClickFranchise();
        ChangeFranchise();
        CancelClick();
    }

    public void EnterTestStep(String TestMessage)
    {
        ExtentTestManager.getTest().log(LogStatus.PASS, TestMessage);
    }

    @Step("Verify Changed Dealer Should reflect on Screen ")
    public void ChangeDealerVerification() throws Exception {
        WebElement ele ;

        displaydealer1= DisplayDealer();
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '" + displaydealer1 + "'");

        ClickFranchise();
        ChangeFranchise();
        SaveClick();

        displaydealer2= DisplayDealer();
        System.out.println(displaydealer2);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name Change: '" + displaydealer2 + "'");

        Assert.assertFalse(displaydealer1.equalsIgnoreCase(displaydealer2), "Default Dealer not Change on Franchise Changes");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Change display on screen");
    }

    @Step("Verify Dealer Should not Change on Cancel Click ")
    public void DealerNotChangeCancel() throws Exception {
        WebElement ele ;
        displaydealer1= DisplayDealer();
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '" + displaydealer1 + "'");

        ClickFranchise();
        ChangeFranchise();
        ChangeDealer();
        CancelClick();

        displaydealer2= DisplayDealer();
        Assert.assertTrue(displaydealer1.equalsIgnoreCase(displaydealer2), "Dealer Change after Cancel Click");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer not Changed after Cancel Click");

        Assert.assertTrue(webDriver.IsPresent(dealerLoc.getlocator("//locators/FranchisePopup")), "Franchise Pop up is not disappeared");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Franchise Pop up is disappeared");
    }

    @Step("Verify Dealer List disappeared on Clicking outside the box")
    public void ClickOutSideDealerSelectionBox() throws Exception
    {
        ClickFranchise();

        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : On Dealers List");

        List<WebElement> dealers = webDriver.getwebelements(dealerLoc.getlocator("//locators/DealerPopup"));

        Assert.assertTrue(dealers.size()>0, "Dealer Pop is not pop");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Pop is open");

        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerPopupDiv")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : Outside the Div");

        List<WebElement> dealers2 = webDriver.getwebelements(dealerLoc.getlocator("//locators/DealerPopup"));

        Assert.assertFalse(dealers2.size()>0, "Dealer Popup is still visible");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Popup is disappeared");
    }


}
