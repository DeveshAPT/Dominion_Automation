package pageHelper.web;

import java.io.IOException;
import java.util.List;

import io.qameta.allure.Step;
import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

import Reporter.ExtentTestManager;
import core.baseDriverHelper;
import core.webHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.PropertyReader;
import utils.xmlreader;

public class DominionLoginHelper {

    xmlreader loginLoc = new xmlreader("src\\test\\resources\\locators\\DominionLogin.xml");
    xmlreader menuLoc = new xmlreader("src\\test\\resources\\locators\\Menu.xml");
    PropertyReader propertyreader = new PropertyReader();
    int tabCount=0;
    public webHelper webDriver;

    public DominionLoginHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    @Step("Open Application and Verify Login Page Content")
    public void OpenDominion() throws Exception {
        //Thread.sleep(5000);
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        System.out.println("");
        System.out.println(propertyreader.readproperty("Dominion"));
        webDriver.OpenURL(propertyreader.readproperty("Dominion"));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, " Application Opened");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/UserName")), "'User Name' Field is not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'User Name' Field is displayed");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/Password")), "'Password' Field is not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'Password' Field is displayed");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/ForgotPasswordLink")), "'Forgot Password?' Link is not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'Forgot Password?' Link is displayed");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/AdditionalHelp")), "'Need Additional Help?' Link is not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'Need Additional Help?' Link displayed");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/LoginGo")), "'GO' Button is not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'GO' Button is displayed");

        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Login Screen");
    }

    @Step("Click on Need Additional Help? Link")
    public void ClickAdditionalHelp() throws Exception {
        tabCount=webDriver.TabSize();
        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/AdditionalHelp")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Need Additional Help?'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Click on Forgot Password")
    public void ClickForgotPassword() throws Exception {
        tabCount=webDriver.TabSize();
        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/ForgotPasswordLink")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Forgot Password?'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Verify Forgot Password Screen and Click On GO after Entering Email-ID")
    public void VerifyForgotPasswordScreen() throws InterruptedException, DocumentException
    {
        WebElement h3=webDriver.getwebelement(loginLoc.getlocator("//locators/ForgotPasswordLabel"));
        WebElement label=webDriver.getwebelement(loginLoc.getlocator("//locators/ForgotPasswordMessage"));
        String heading=h3.getText();
        String message=label.getText();
        Assert.assertTrue(heading.equalsIgnoreCase("Forgot Password"),"'FORGOT PASSWORD' heading is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'FORGOT PASSWORD' heading is displayed");

        Assert.assertTrue(message.equalsIgnoreCase("Please enter the email address you use to login. We will send you a link to reset your password."),"'Please enter the email address you use to login. We will send you a link to reset your password.' message is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'Please enter th email address you use to login. We will send you a link to reset your password.' message is displayed");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/LoginGo")), "'GO' Button is not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'GO' Button is displayed");
    }

    @Step("Verify Forgot Password Screen after Go Click")
    public void ForgotPasswordValidation() throws Exception {
        webDriver.SendKeys(webDriver.getwebelement(loginLoc.getlocator("//locators/ForgoPasswordEmail")), "Kumar.devesh89@yahoo.com" + Keys.TAB);
        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/LoginGo")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test Email is entered and Click on Go");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        WebElement h3=webDriver.getwebelement(loginLoc.getlocator("//locators/ForgotPasswordLabel"));
        WebElement label=webDriver.getwebelement(loginLoc.getlocator("//locators/ForgotPasswordMessage"));
        String heading=h3.getText();
        String message=label.getText();
        Assert.assertTrue(heading.equalsIgnoreCase("Thanks"),"'Thanks' heading is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'Thanks' heading is displayed");

        Assert.assertTrue(message.equalsIgnoreCase("An email has been sent with instructions on how to reset your password."),"'An email has been sent with instructions on how to reset your password.' message is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'An email has been sent with instructions on how to reset your password.' message is displayed");

    }

    @Step("Verify Need Additional Help? Page and Content")
    public void VerifyAdditionalHelpPage() throws Exception {
        int newCount=webDriver.TabSize();
        Assert.assertTrue(newCount-tabCount==1,"Browser New Tab is not open after 'Need  Additional Help ?' Click action");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : New Tab is open after 'Need  Additional Help ?' Click action");
        webDriver.SwitchToLastTab();
        String newurl=webDriver.CurrentURL();
        System.out.println();
        System.out.println(newurl);
        Assert.assertTrue(newurl.equalsIgnoreCase("https://www.drivedominion.com/about-us/contact/"),"URL: https://www.drivedominion.com/about-us/contact/ not open after 'Need  Additional Help ?' Click action");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : URL: https://www.drivedominion.com/about-us/contact/ is open after 'Need  Additional Help ?' Click action");

        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/AcceptCookie")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/CallUs")), "'Call Us at ' Message is not display in Need Additional Help? Page");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'Call Us at' Field displayed");

        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/MailUs")), "'Send an email to' is not display in Need Additional Help? Page");
        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/MailLink")), "'Send an email to' link is not display in Need Additional Help? Page");
        WebElement email=webDriver.getwebelement(loginLoc.getlocator("//locators/MailUsEmail"));
        String textMail=email.getText();
        System.out.println(textMail);
        Assert.assertTrue(textMail.equalsIgnoreCase("dealersuccess@drivedominion.com"),"Send ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'Send an email to' link with email id "+ textMail+ " is displayed");
        webDriver.switchtodefault();
        webDriver.CloseAllTabs();
    }

    @Step("Login into Application")
    public void DominionLogin() throws Exception {
        webDriver.SendKeys(webDriver.getwebelement(loginLoc.getlocator("//locators/UserName")), propertyreader.readproperty("UserName") + Keys.TAB);

        webDriver.SendKeys(webDriver.getwebelement(loginLoc.getlocator("//locators/Password")), propertyreader.readproperty("UserPassword") + Keys.TAB);
        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/LoginGo")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        ExtentTestManager.getTest().log(LogStatus.PASS, " Login Completed ");

        String locator = loginLoc.getlocator("//locators/DisplayUserName");
        Assert.assertTrue(webDriver.IsPresent(locator), "Verified : User Name displayed");
        WebElement ele = webDriver.getwebelement(locator);
        String user = ele.getText();
        System.out.println("");
        System.out.println(user);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Displayed User Name '" + user + "' after Login");

    }

    @Step("Verify User Name and Logout")
    public void Logout() throws Exception {
        webDriver.WaitforPageToBeReady();
        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/UserNameButton")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        webDriver.Clickon(webDriver.getwebelement(loginLoc.getlocator("//locators/Logout")));

        //webDriver.WaitForpageload();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
        Thread.sleep(5000);


        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/UserName")), "'User Name' Field Not found");
        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/Password")), "'Password' Field Not found");
        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/ForgotPasswordLink")), "'Forgot Password?' Link Not found");
        Assert.assertTrue(webDriver.IsPresent(loginLoc.getlocator("//locators/AdditionalHelp")), "'Need Additional Help?' Link Not found");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : User is moved to SSO Login Screen after Logout");
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
    @Step("Click on Menu Item : {0} sub item {1}")
    public void ClickOnMenuSubMenu(String menuItem, String SubMenu) throws Exception {
        ClickOnMenu("Dealer Overview");
        WebElement main = webDriver.getwebelement(menuLoc.getlocator("//locators/Preferences"));
        webDriver.ScrollIntoView(main);
        webDriver.Moveon(main);
        List<WebElement> sublist = webDriver.getwebelements(menuLoc.getlocator("//locators/SubMenu"));
        Assert.assertTrue(sublist.size()>0,menuItem + " hover menu not displayed");
        ExtentTestManager.getTest().log(LogStatus.PASS,  "Verified : "+menuItem+" hover menu displayed");
        int subindex = -1;
        int s = 0;
        String currentSubMenu;
        WebElement ele1 = null;
        for (int i = 0; i < sublist.size(); i++) {
            ele1 = sublist.get(i);
            currentSubMenu = ele1.getText();
            String Temp = ele1.getAttribute("innerHTML");
            System.out.println("");
            System.out.println(currentSubMenu);
            System.out.println(Temp);
            if (currentSubMenu.equalsIgnoreCase(SubMenu) || Temp.contains(SubMenu)) {
                subindex++;
                s = i;
                break;
            }
        }
        if (subindex > -1) {
            //main =  webDriver.getwebelement(menuLoc.getlocator("//locators/Preferences"));
            //webDriver.JavaScriptMouseHover(main);
            //WebElement sub=sublist.get(s);
            String temploc = menuLoc.getlocator("//locators/Settings");
            temploc = temploc.replace("SubText", SubMenu);
            webDriver.ScrollIntoView(webDriver.getwebelement(menuLoc.getlocator("//locators/Preferences")));
            webDriver.MoveonAndClick(menuLoc.getlocator("//locators/Preferences"), temploc);
            // main.click();
            // sub.click();
            ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Sub Menu : " + SubMenu + " Under Main Menu " + menuItem);
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
            webDriver.PageRefresh();
            webDriver.WaitForpageload();
            webDriver.WaitforPageToBeReady();
        } else
            Assert.fail("SubMenu Item : " + SubMenu + " not found");

    }

    @Step("Verify Page Content on Navigation to : {0}")
    public void VerifyMenuNavigation(String menuItem) throws Exception {
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        String pageTitle;
        System.out.println("");
        if (menuItem.equalsIgnoreCase("Dealer Overview"))
        {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title : " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/InventoryChart")),"Days in Inventory Chart not displayed, Dashboard not loaded Properly");
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/GridCard")),"Card Grid not displayed, Dashboard not loaded Properly");
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/DashboardAttention")),"Call To Action Dashboard Attention Box not displayed, Dashboard not loaded Properly");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Dealer Overview --> Dashboard displayed");
        } else if (menuItem.equalsIgnoreCase("Inventory"))
        {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/InventoryGrid")),"Inventory Grid not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Inventory >> Inventory Grid displayed");
        } else if (menuItem.equalsIgnoreCase("Appraisals")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/AppraisalGrid")),"Appraisals Grid not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Appraisals >> Appraisals Grid displayed");
        }
        else if (menuItem.equalsIgnoreCase("Sourcing")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/SourcingGrid")),"Sourcing Grid not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Sourcing >> Sourcing Grid displayed");
        }
        else if (menuItem.equalsIgnoreCase("Reports")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/ReportBuilderForm")),"Reports Builder >> Form not displayed");
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/ReportType")),"Reports Builder >> Report Type Dropdown not displayed");
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/ReportName")),"Reports Builder >> Report Name Input box not displayed");
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/ReportFields")),"Reports Builder >> Report Fields not displayed");
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/SelectedFields")),"Reports Builder >> Report Selected Fields not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Reports >> Reports Builder Screen displayed");
        } else if (menuItem.equalsIgnoreCase("Users"))
        {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/UsersGrid")),"Users >> Users Grid not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Users >> Users Grid displayed");
        } else if (menuItem.equalsIgnoreCase("Settings"))
        {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/MySetting")),"System Preferences >> Settings >> My Settings not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Settings >> My Settings is displayed");
            List<WebElement> settingmenu=webDriver.getwebelements(menuLoc.getlocator("//locators/MySettingTopMenu"));
            List<WebElement> settingsubmenu=webDriver.getwebelements(menuLoc.getlocator("//locators/MySettingMidMenu"));
            Assert.assertTrue(settingmenu.size()>0&& settingsubmenu.size()>0,"System Preferences >> Settings >> Menu not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Settings >> settings menu is displayed");
        } else if (menuItem.equalsIgnoreCase("Applications")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            List<WebElement> settingmenu=webDriver.getwebelements(menuLoc.getlocator("//locators/MySettingTopMenu"));
            Assert.assertTrue(settingmenu.size()>0,"System Preferences >> Applications >> Menu not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Applications >> Applications menu is displayed");
        }
        else if (menuItem.equalsIgnoreCase("Credentials"))
        {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            List<WebElement> settingmenu=webDriver.getwebelements(menuLoc.getlocator("//locators/MySettingTopMenu"));
            Assert.assertTrue(settingmenu.size()>0,"System Preferences >> Credentials >> Menu not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Credentials >> Credentials menu is displayed");
        } else if (menuItem.equalsIgnoreCase("Rules")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            List<WebElement> settingmenu=webDriver.getwebelements(menuLoc.getlocator("//locators/MySettingTopMenu"));
            Assert.assertTrue(settingmenu.size()>0,"System Preferences >> Rules >> Menu not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Rules >> Rules menu is displayed");
        } else if (menuItem.equalsIgnoreCase("Warranties")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            Assert.assertTrue(webDriver.IsPresent(menuLoc.getlocator("//locators/Warranties")),"System Preferences >> Warranties >> Warranties Grid is not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Warranties >> Warranties Grid is displayed");
        } else if (menuItem.equalsIgnoreCase("Support"))
        {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            List<WebElement> settingmenu=webDriver.getwebelements(menuLoc.getlocator("//locators/MySettingTopMenu"));
            Assert.assertTrue(settingmenu.size()>0,"System Preferences >> Support >> Menu not displayed");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : System Preferences >> Support >> Support menu is displayed");

        } else if (menuItem.equalsIgnoreCase("Help")) {
            pageTitle = webDriver.GetTitle();
            System.out.println(pageTitle);
            Assert.assertTrue(pageTitle.equalsIgnoreCase("VinMotion | IVM5"),"Help >> Help Page not loaded as the Page title is not Changed to VinMotion | IVM5");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Page Title >> " + pageTitle);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Help >> Page Title is "+ pageTitle);
        }
    }
}
