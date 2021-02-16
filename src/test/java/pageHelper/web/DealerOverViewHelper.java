package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import io.qameta.allure.Step;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.xmlreader;

import java.util.List;

public class DealerOverViewHelper {
    xmlreader dealerLoc = new xmlreader("src\\test\\resources\\locators\\DealerOverView.xml");
    xmlreader menuLoc = new xmlreader("src\\test\\resources\\locators\\Menu.xml");
    xmlreader inventoryLoc = new xmlreader("src\\test\\resources\\locators\\Inventory.xml");

    CommonFunctions commn = new CommonFunctions();

    public webHelper webDriver;
    String displaydealer1, displaydealer2, franchise;

    public DealerOverViewHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public String DisplayDealer() throws Exception {
        String temp = null;
        WebElement ele;
        if (webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerName"))) {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerName"));
            temp = ele.getAttribute("value");
        } else if (webDriver.IsPresent(dealerLoc.getlocator("//locators/DisplayDealerNameSpan"))) {
            ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/DisplayDealerNameSpan"));
            temp = ele.getText();
        }
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '" + temp + "'");
        return temp;
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

    public int GetColIndex(String colName) throws Exception {
        webDriver.WaitforElementVisible(webDriver.getwebelement(dealerLoc.getlocator("//locators/DaysSupplyHeading")));
        webDriver.WaitforElementClickable(webDriver.getwebelement(dealerLoc.getlocator("//locators/DaysSupplyHeading")));

        List<WebElement> headings = webDriver.getwebelements(dealerLoc.getlocator("//locators/DaysSupplyHeading"));
        Assert.assertTrue(headings.size() > 0, "Days Supply does not contain bar for : " + colName);
        boolean found = false;
        int k = 0;
        for (WebElement el : headings) {
            k++;
            String temp1 = commn.RemoveAllSpace(el.getText());
            String temp2 = commn.RemoveAllSpace(colName);
            System.out.println(temp1);
            System.out.println(temp2);
            if (temp1.equalsIgnoreCase(temp2)) {
                found = true;
                System.out.println(Integer.toString(k));
                break;
            }

        }
        return k;
    }

    public void ClickDifferentRangeDaysInventoryAndVerify() throws Exception {
        ClickOnTab("Days In Inventory");
        int daysindex = GetColIndex("Days In Inventory");
        int countindex = GetColIndex("Count");
        String countValue;
        int k = 0;
        List<WebElement> rows = webDriver.getwebelements(dealerLoc.getlocator("//locators/DaysInInventoryRows"));
        Assert.assertTrue(rows.size() > 0, "Days In Inventory Grid is not loaded/displayed properly");
        for (int i = 0; i < rows.size(); i++) {
            if (i != 0)
                ClickOnTab("Days In Inventory");
            k = i + 1;
            String loc1 = dealerLoc.getlocator("//locators/DaysInInventoryColumn");
            String loc2 = loc1.replace("rowind", Integer.toString(k)).replace("colind", Integer.toString(countindex));
            System.out.println(loc2);
            WebElement ele = webDriver.getwebelement(loc2);
            countValue = ele.getText();
            System.out.println("");
            System.out.println(countValue);
            loc2 = loc1.replace("rowind", Integer.toString(k)).replace("colind", Integer.toString(daysindex));
            System.out.println(loc2);
            ele = webDriver.getwebelement(loc2);
            String range = ele.getText();
            String temp = countValue == null || countValue == "" ? "0" : countValue;
            String temp2 = "Get Vehicle Count  :" + temp + " for range " + range;
            System.out.println(temp2);
            ExtentTestManager.getTest().log(LogStatus.PASS, temp2);
            if (!range.equalsIgnoreCase("TOTAL")) {
                BarClick(range, loc2);
                //Verify The Count
                VerifyInventoryVehicleCount(countValue, range);
                ClickOnMenu("Dealer Overview");
            }
        }
    }

    public void ClickDifferentRangeDaysSupplyAndVerify() throws Exception {
        ClickOnTab("Days Supply");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        int daysindex = GetColIndex("Days Supply");
        int countindex = GetColIndex("Count");
        String countValue;
        int k = 0;
        List<WebElement> rows = webDriver.getwebelements(dealerLoc.getlocator("//locators/DaysSupplyRows"));
        Assert.assertTrue(rows.size() > 0, "Days Supply Grid is not loaded/displayed properly");
        for (int i = 0; i < rows.size(); i++) {
            if (i != 0)
                ClickOnTab("Days Supply");
            k = i + 1;
            String loc1 = dealerLoc.getlocator("//locators/DaysSupplyColumn");
            String loc2 = loc1.replace("rowind", Integer.toString(k)).replace("colind", Integer.toString(countindex));
            System.out.println(loc2);
            WebElement ele = webDriver.getwebelement(loc2);
            countValue = ele.getText();
            System.out.println("");
            System.out.println(countValue);
            loc2 = loc1.replace("rowind", Integer.toString(k)).replace("colind", Integer.toString(daysindex));
            System.out.println(loc2);
            ele = webDriver.getwebelement(loc2);
            String range = ele.getText();
            String temp = countValue == null || countValue == "" ? "0" : countValue;
            String temp2 = "Get Vehicle Count  :" + temp + " for range " + range;
            System.out.println(temp2);
            ExtentTestManager.getTest().log(LogStatus.PASS, temp2);
            if (!range.equalsIgnoreCase("TOTAL")) {
                BarClick(range, loc2);
                //Verify The Count
                VerifyInventoryVehicleCount(countValue, range);
                ClickOnMenu("Dealer Overview");
                webDriver.WaitloadingComplete();
                webDriver.WaitForpageload();
                webDriver.WaitforPageToBeReady();
            }


        }
    }

    public void ColouredButtonToggleValidation(String TabName) throws Exception {
       // ClickOnTab("Days Supply");
        ClickOnTab(TabName);
        String fillColour = null;
        ExtentTestManager.getTest().log(LogStatus.PASS, "<<------- Start Working For Too High ------->>");
        ColourButtonClick("Too High");
        ExtentTestManager.getTest().log(LogStatus.PASS, "************** End of Too High **************");
        ExtentTestManager.getTest().log(LogStatus.PASS, "<<------- Start Working For High ------->>");
        ColourButtonClick("High");
        ExtentTestManager.getTest().log(LogStatus.PASS, "************** End of High **************");
        ExtentTestManager.getTest().log(LogStatus.PASS, "<<------- Start Working For Optimal ------->>");
        ColourButtonClick("Optimal");
        ExtentTestManager.getTest().log(LogStatus.PASS, "************** End of Optimal **************");
        ExtentTestManager.getTest().log(LogStatus.PASS, "<<------- Start Working For Low ------->>");
        ColourButtonClick("Low");
        ExtentTestManager.getTest().log(LogStatus.PASS, "**************End of  Low **************");
        ExtentTestManager.getTest().log(LogStatus.PASS, "<<------- Start Working For Too Low ------->>");
        ColourButtonClick("Too Low");
        ExtentTestManager.getTest().log(LogStatus.PASS, "**************End of  Too Low **************");
        ExtentTestManager.getTest().log(LogStatus.PASS, "<<------- Start Working For N/A ------->>");
        ColourButtonClick("N/A");
        ExtentTestManager.getTest().log(LogStatus.PASS, "**************End of  N/A **************");

    }

    public void EnterTestStep(String TestMessage) {
        ExtentTestManager.getTest().log(LogStatus.PASS, TestMessage);
    }

    public void AttentionMenuValueVerification() throws Exception {
        String genericLoc = dealerLoc.getlocator("//locators/AttentionLoc");
        String labelLoc = genericLoc.replace("-10", "1");
        String valueLoc = genericLoc.replace("-10", "2");
        System.out.println(labelLoc);
        System.out.println(valueLoc);
        List<WebElement> labels = webDriver.getwebelements(labelLoc);
        List<WebElement> values = webDriver.getwebelements(valueLoc);
        Assert.assertTrue(labels.size() > 0, "Dealers Overview Attention Call to Action Menu not display/loaded ");
        for (int i = 0; i < labels.size(); i++) {
            WebElement label = labels.get(i);
            String labelName = label.getText();
            WebElement valueEle = values.get(i);
            String labelvalue = valueEle.getText();
            ExtentTestManager.getTest().log(LogStatus.PASS, "Get : Label Name " + labelName + "  and Values  " + labelvalue);
            webDriver.Clickon(label);
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click On label : " + labelName);
            VerifyActiveTab("Call To Action");
            AttentionLabelAndValueVerification(labelName, labelvalue);
        }
    }

    public void MarketRadiusChangeVerification() throws DocumentException, InterruptedException {
        Thread.sleep(5000);
        String currentRadius = GetRadiusValue();
        Thread.sleep(5000);
        String radius = ChangeRadiusValue(currentRadius);
        Thread.sleep(5000);
        VerifymarketRadiusUpdated(radius);
        Thread.sleep(5000);
    }

    public void DealersOverviewDropdown(String values) throws Exception {
        webDriver.WaitforPageToBeReady();
        List<WebElement> options = webDriver.getwebelements(dealerLoc.getlocator("//locators/DashBoardSelectionItems"));
        boolean found = false;
        String temp = null;
        for (int i = 0; i < options.size(); i++) {
            temp = options.get(i).getText();
            if (commn.RemoveAllSpace(temp).equalsIgnoreCase(commn.RemoveAllSpace(values))) {
                webDriver.Clickon(options.get(i));
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Failed Due to Values " + values + " not found in Dropdown");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Selecting  Dropdown Value : " + values);
    }

    public void MonthPickerVerification() throws Exception {
        SelectCARScore();
        VerifyCARScoreOpen();
        SelectMonth();
        ClickCancel();
    }

    @Step("Open Month Picker and Selecting Month")
    public  void SelectMonth() throws Exception
    {
        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/MonthPicker")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Month Picker Control");
        Thread.sleep(2500);
        List<WebElement> months = webDriver.getwebelements(dealerLoc.getlocator("//locators/ActiveMonths"));
        if (months.size() == 0 || months.isEmpty())
        {
            webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/NextBtn")));
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Next in Month Picker");
            Thread.sleep(2500);
            months = webDriver.getwebelements(dealerLoc.getlocator("//locators/ActiveMonths"));
        }
        webDriver.Clickon(months.get(1));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Month Picked : "+months.get(1).getText());
    }

    @Step("Click On Cancel")
    public  void ClickCancel() throws Exception
    {
        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/MonthPickerCancel")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Month Picker Cancel");
        Thread.sleep(2500);
    }

    @Step("Selecting C.A.R Score in Dealers Overview")
    public void SelectCARScore() throws Exception
    {
        if(webDriver.IsPresent(dealerLoc.getlocator("//locators/InventorySelection"))) {
            webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/InventorySelection")));
            Thread.sleep(4000);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Dealers Overview Dropdown");
            DealersOverviewDropdown("C.A.R.Score Dashboard");
        }

    }

    @Step("Selecting Inventory in Dealers Overview")
    public void SelectInventory() throws Exception {
        if(webDriver.IsPresent(dealerLoc.getlocator("//locators/DashBoardSelection")))
        {
            webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/DashBoardSelection")));
            Thread.sleep(4000);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Dealers Overview Dropdown");
            DealersOverviewDropdown("Inventory Dashboard");
        }

    }

    @Step("Verifying C.A.R.Score board is open")
    public void VerifyCARScoreOpen() throws InterruptedException, DocumentException {
        WebElement ele=webDriver.getwebelement(dealerLoc.getlocator("//locators/DashBoardSelection"));
        String temp=ele.getText();
        System.out.println(temp);
        Assert.assertTrue(commn.RemoveAllSpace("C.A.R.Score").equalsIgnoreCase(commn.RemoveAllSpace(temp)),"C.A.R.Score is not selected");
        ele=webDriver.getwebelement(dealerLoc.getlocator("//locators/CarScoreHeading"));
        temp=ele.getText();
        System.out.println(temp);
        Assert.assertTrue(commn.RemoveAllSpace("C.A.R.Score Details").equalsIgnoreCase(commn.RemoveAllSpace(temp)),"C.A.R.Score Detail Section is not open");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : C.A.R.Score is selected and C.A.R.Score details section loaded ");
    }

    @Step("Reading Selected Values of Market Radius ")
    public String GetRadiusValue() throws InterruptedException, DocumentException {
        Select radius = new Select(webDriver.getwebelement(dealerLoc.getlocator("//locators/MarketRadius")));
        String selected = radius.getFirstSelectedOption().getText();
        System.out.println(selected);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Currently Selected Market Radius is : "+selected);
        return selected;
    }

    @Step("Changing market Radius to Other then {0}")
    public String ChangeRadiusValue( String currentValues) throws InterruptedException, DocumentException {
        Select radius = new Select(webDriver.getwebelement(dealerLoc.getlocator("//locators/MarketRadius")));
        List<WebElement> options=radius.getOptions();
        String temp=null;
        for(WebElement el: options)
        {
         temp=el.getText();
         if(!commn.RemoveAllSpace(temp).equalsIgnoreCase(commn.RemoveAllSpace(currentValues)))
         {
             radius.selectByVisibleText(temp);
             break;
         }
        }
        ExtentTestManager.getTest().log(LogStatus.PASS, "Change market Radius To : "+temp);
        return  temp;
    }

    @Step("Verify Market Radius Change to {0} ")
    public void VerifymarketRadiusUpdated(String newValue) throws InterruptedException, DocumentException {
        Select radius = new Select(webDriver.getwebelement(dealerLoc.getlocator("//locators/MarketRadius")));
        String selected = radius.getFirstSelectedOption().getText();
        System.out.println(selected);
        Assert.assertTrue(commn.RemoveAllSpace(newValue).equalsIgnoreCase(commn.RemoveAllSpace(selected)));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify Market Radius is Updated to  : "+selected);

    }

    @Step("Verify Call to Action for Label {0} and Values {1} ")
    public void AttentionLabelAndValueVerification(String label, String values) throws DocumentException, InterruptedException {
        Thread.sleep(5000);
        WebElement ele = webDriver.getwebelement(dealerLoc.getlocator("//locators/CallToActionCount"));
        String displayText = ele.getText();
        Assert.assertTrue(displayText.equalsIgnoreCase(values), "Attention Label : " + label + " not matched Call To Action count : " + displayText);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify  : Label Name " + label + "  and Values  " + values + " matched ");

    }

    @Step("Verify Dealers Overview Tab is Active {0}")
    public void VerifyActiveTab(String tabName) throws InterruptedException, DocumentException {

        List<WebElement> tabs = webDriver.getwebelements(dealerLoc.getlocator("//locators/Tabs"));
        Assert.assertTrue(tabs.size() > 0, "Dealers Overview Tabs are not Loaded/Display ");
        boolean found = false;
        String className = null;
        for (WebElement el : tabs) {
            String text = commn.RemoveAllSpace(el.getText());
            className = el.getAttribute("class");
            if (commn.RemoveAllSpace(tabName).equalsIgnoreCase(text)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Dealers Overview Tab : " + tabName + " not found");
        Assert.assertTrue(className.contains("active"), "Tab Name : " + tabName + " is not Open");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify Tab Name  : '" + tabName + "' is Open");

    }

    @Step("Open Dealers Overview Tab {0}")
    public void ClickOnTab(String tabName) throws Exception {
        List<WebElement> tabs = webDriver.getwebelements(dealerLoc.getlocator("//locators/Tabs"));
        Assert.assertTrue(tabs.size() > 0, "Dealers Overview Tabs are not Loaded/Display ");
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
        Assert.assertTrue(found, "Dealers Overview Tab : " + tabName + " not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Dealers Overview Tab  : '" + tabName + "'");
    }

    @Step("Click on Colour Button {0} and verify Toggle State")
    public void ColourButtonClick(String btnName) throws Exception {

        String fillcolour = "NA";
        String newcolour = "NA";
        String locForBar = "NA";
        List<WebElement> barList;
        List<WebElement> newbarList;
        WebElement ele;
        List<WebElement> btnlist;
        String currentBtnLoc = null;
        switch (btnName) {
            case "Too High":
                currentBtnLoc = dealerLoc.getlocator("//locators/TooHighBtn");
                break;
            case "High":
                currentBtnLoc = dealerLoc.getlocator("//locators/HighBtn");
                break;
            case "Optimal":
                currentBtnLoc = dealerLoc.getlocator("//locators/OptimalBtn");
                break;
            case "Low":
                currentBtnLoc = dealerLoc.getlocator("//locators/LowBtn");
                break;
            case "Too Low":
                currentBtnLoc = dealerLoc.getlocator("//locators/TooLowBtn");
                break;
            case "N/A":
                currentBtnLoc = dealerLoc.getlocator("//locators/NABtn");
                break;
            default:
                System.out.println("Button(" + btnName + ")  Not found");
                break;

        }
        btnlist = webDriver.getwebelements(currentBtnLoc);
        ele = btnlist.get(0);
        fillcolour = ele.getAttribute("fill");

        //making Active
        if (fillcolour.equalsIgnoreCase("#cccccc")) {
            Thread.sleep(4000);
            webDriver.Moveon(btnlist.get(0));
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
            btnlist = webDriver.getwebelements(currentBtnLoc);
            ele = btnlist.get(0);
            fillcolour = ele.getAttribute("fill");
        }

        locForBar = dealerLoc.getlocator("//locators/GraphBar");
        locForBar = locForBar.replace("MyColour", fillcolour);
        System.out.println(locForBar);
        barList = webDriver.getwebelements(locForBar);
        Thread.sleep(4000);
        webDriver.Moveon(btnlist.get(0));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        //webDriver.PageRefresh();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on '" + btnName + "' Button ");
        ele = webDriver.getwebelement(currentBtnLoc);
        newcolour = ele.getAttribute("fill");
        newbarList = webDriver.getwebelements(locForBar);
        Assert.assertFalse(newcolour.equalsIgnoreCase(fillcolour), "Button :" + btnName + " not change the colour");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify Button " + btnName + " Change the Colour on Click");
        if (barList.size() > 0) {
            Assert.assertTrue(newbarList.size() == 0 || newbarList.isEmpty(), "Button :" + btnName + " not change the colour");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verify Graph Bar toggle on  " + btnName + " Click");
        }
        //Change Make Active again
        if (newcolour.equalsIgnoreCase("#cccccc")) {
            Thread.sleep(4000);
            webDriver.Moveon(btnlist.get(0));
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();

        } else {
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
        }

    }

    @Step("Verify Vehicle Count with Chart")
    public void VerifyInventoryVehicleCount(String count, String range) throws InterruptedException, DocumentException {
        WebElement ele = webDriver.getwebelement(inventoryLoc.getlocator("//locators/VehicleCount"));
        String text = ele.getText();
        System.out.println(text);
        String[] vals = text.split("\\s+");
        System.out.println(vals[0]);
        if (count == "" || count.isEmpty() || count == null) {
            Assert.assertTrue(vals[0].equalsIgnoreCase("0"), "Vehicle Count not matched");
        } else {
            Assert.assertTrue(commn.RemoveAllSpace(vals[0]).equalsIgnoreCase(commn.RemoveAllSpace(count)), "Vehicle Count not matched");
            String temp = count == null || count == "" ? "0" : count;
            String temp2 = "Get Vehicle Count  :" + temp + " for range " + range;
            ExtentTestManager.getTest().log(LogStatus.PASS, temp2);
        }
    }

    @Step("Click on Menu Item : {0}")
    public void ClickOnMenu(String menuItem) throws Exception {
        List<WebElement> menuList = webDriver.getwebelements(menuLoc.getlocator("//locators/MainMenu"));
        int index = -1;
        String currentMenu;
        WebElement ele = null;
        for (int i = 0; i < menuList.size(); i++) {
            ele = menuList.get(i);
            currentMenu = ele.getText();
            System.out.println("");
            System.out.println(currentMenu);
            if (currentMenu.equalsIgnoreCase(menuItem)) {
                index++;
                break;
            }
        }
        if (index > -1) {
            webDriver.Clickon(ele);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Menu Item : " + menuItem);
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
        } else {
            Assert.fail("Menu Item : " + menuItem + " not found");
        }

    }

    @Step("Click on {0}")
    public void BarClick(String barName, String loc) throws Exception {
        webDriver.Clickon(webDriver.getwebelement(loc));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Range : '" + barName + "'");
        webDriver.WaitloadingComplete();
        webDriver.WaitforPageToBeReady();
        webDriver.WaitForpageload();

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

    @Step("Verify Changed Dealer Should reflect on Screen ")
    public void ChangeDealerVerification() throws Exception {
        WebElement ele;

        displaydealer1 = DisplayDealer();
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '" + displaydealer1 + "'");

        ClickFranchise();
        ChangeFranchise();
        SaveClick();

        displaydealer2 = DisplayDealer();
        System.out.println(displaydealer2);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name Change: '" + displaydealer2 + "'");

        Assert.assertFalse(displaydealer1.equalsIgnoreCase(displaydealer2), "Default Dealer not Change on Franchise Changes");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Change display on screen");
    }

    @Step("Verify Dealer Should not Change on Cancel Click ")
    public void DealerNotChangeCancel() throws Exception {
        WebElement ele;
        displaydealer1 = DisplayDealer();
        System.out.println(displaydealer1);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Display Dealer Name : '" + displaydealer1 + "'");

        ClickFranchise();
        ChangeFranchise();
        ChangeDealer();
        CancelClick();

        displaydealer2 = DisplayDealer();
        Assert.assertTrue(displaydealer1.equalsIgnoreCase(displaydealer2), "Dealer Change after Cancel Click");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer not Changed after Cancel Click");

        Assert.assertTrue(webDriver.IsPresent(dealerLoc.getlocator("//locators/FranchisePopup")), "Franchise Pop up is not disappeared");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Franchise Pop up is disappeared");
    }

    @Step("Verify Dealer List disappeared on Clicking outside the box")
    public void ClickOutSideDealerSelectionBox() throws Exception {
        ClickFranchise();

        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerNames")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : On Dealers List");

        List<WebElement> dealers = webDriver.getwebelements(dealerLoc.getlocator("//locators/DealerPopup"));

        Assert.assertTrue(dealers.size() > 0, "Dealer Pop is not pop");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Pop is open");

        webDriver.Clickon(webDriver.getwebelement(dealerLoc.getlocator("//locators/DealerPopupDiv")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click : Outside the Div");

        List<WebElement> dealers2 = webDriver.getwebelements(dealerLoc.getlocator("//locators/DealerPopup"));

        Assert.assertFalse(dealers2.size() > 0, "Dealer Popup is still visible");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Popup is disappeared");
    }

}
