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

public class UserHelper {
    xmlreader userLoc = new xmlreader("src\\test\\resources\\locators\\Users.xml");
    xmlreader inventoryLoc = new xmlreader("src\\test\\resources\\locators\\Inventory.xml");
    public webHelper webDriver;

    public UserHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public int ColumnIndex(String ColName) throws InterruptedException, DocumentException {
        List<WebElement> th = webDriver.getwebelements(userLoc.getlocator("//locators/UserTableHeading"));
        Assert.assertFalse(th.size() == 0 || th.isEmpty(), "No Heading found for Users Grid");
        boolean found = false;
        int k = 0;
        for (int i = 0; i < th.size(); i++) {
            WebElement el = th.get(i);
            System.out.println("");
            String temp = el.getText().replaceAll("\\s", "");
            ColName=ColName.replaceAll("\\s", "");
            System.out.println(temp);
            System.out.println(ColName);
            if (temp.equalsIgnoreCase(ColName)) {
                k = i;
                found = true;
                break;
            }
        }
        String colind = Integer.toString(k);
        Assert.assertTrue(found, "Column Name : " + ColName + " does not exist in Users Grid");
        System.out.println(colind);
        return k;
    }

    @Step("Click on All Users")
    public void ClickAllUsers() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(userLoc.getlocator("//locators/AllBtn")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'All' Users");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Click on Active Users")
    public void ClickActiveUsers() throws Exception {
       //List<WebElement> btns=webDriver.getwebelements(userLoc.getlocator("//locators/ActiveBtn"));
       // webDriver.Moveon(btns.get(0));
        webDriver.SafeJavaScriptClick(webDriver.getwebelement(userLoc.getlocator("//locators/AllBtn")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'All' Users");
        //webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Click on InActive Users")
    public void ClickInActiveUsers() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(userLoc.getlocator("//locators/InactiveBtn")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'InActive' Users");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    public String getUserNameAdnClick() throws Exception {
        int index = ColumnIndex("First Name");
        String loc = userLoc.getlocator("//locators/UserColumnVal");
        loc = loc.replace("trindex", "2").replace("tdindex", Integer.toString(index+1));
        WebElement ele = webDriver.getwebelement(loc);
        String val = ele.getText();
        System.out.println(val);
        webDriver.Clickon(ele);
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : '" + val + "' Users");
        //webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        return val;
    }

    @Step("Open User Info Screen for User : {0} ")
    public void verifyUserInfoScreen(String user) throws InterruptedException, DocumentException {
        WebElement ele = webDriver.getwebelement(userLoc.getlocator("//locators/FirstName"));
        String val = ele.getAttribute("value");
        Assert.assertTrue(user.equalsIgnoreCase(val), "User Screen not matched with user Name : " + user);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify  : '" + val + "' Users info Screen is open");
    }

    @Step("Expand Permission Section")
    public void OpenPermission() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(userLoc.getlocator("//locators/Permission")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Permission' Expand button");
        //webDriver.WaitloadingComplete();
        //webDriver.WaitForpageload();
       //webDriver.WaitforPageToBeReady();
    }

    @Step("Select Area as {0} from dropdown option ")
    public void SelectArea(String option) throws Exception {
        Select drop = new Select(webDriver.getwebelement(userLoc.getlocator("//locators/PermissionArea")));
        drop.selectByVisibleText(option);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : '" + option + "' as Area ");
        //webDriver.WaitloadingComplete();
        //webDriver.WaitForpageload();
        //webDriver.WaitforPageToBeReady();
    }



    public void AddPermission(String permission) throws Exception {
        ClickActiveUsers();
        String user = getUserNameAdnClick();
        verifyUserInfoScreen(user);
        OpenPermission();
        SelectArea(permission);

    }
}
