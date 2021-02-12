package testScripts;

import com.relevantcodes.extentreports.LogStatus;
import io.qameta.allure.*;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import utils.driver;

public class DominionScript extends driver {

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("VinMotion - Log in, Log Off")
    @Epic("VinMotion")
    @Feature("Feature Login")
    @Story("MCITEST-236")
    @Severity(SeverityLevel.BLOCKER)
    public void MCITEST_236(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().Logout();

    }


    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Log in screen > Forgot Password")
    @Epic("Log in screen")
    @Feature("Forgot Password")
    @Story("MCITEST-240")
    @Severity(SeverityLevel.BLOCKER)
    public void MCITEST_240(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().ClickForgotPassword();
        domLogin.get().VerifyForgotPasswordScreen();
        domLogin.get().ForgotPasswordValidation();
    }


    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Log in screen > Need additional help?")
    @Epic("Log in screen")
    @Feature("Need additional help?")
    @Story("MCITEST-243")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_243(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().ClickAdditionalHelp();
        domLogin.get().VerifyAdditionalHelpPage();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("VinMotion- Left Screen Navigation Menu Functionality")
    @Epic("VinMotion")
    @Feature("Menu Navigation")
    @Story("MCITEST-239")
    @Severity(SeverityLevel.CRITICAL)
    public void MCITEST_239(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Dealer Overview");
        domLogin.get().VerifyMenuNavigation("Dealer Overview");
        domLogin.get().ClickOnMenu("Inventory");
        domLogin.get().VerifyMenuNavigation("Inventory");
        domLogin.get().ClickOnMenu("Appraisals");
        domLogin.get().VerifyMenuNavigation("Appraisals");
        domLogin.get().ClickOnMenu("Sourcing");
        domLogin.get().VerifyMenuNavigation("Sourcing");
        domLogin.get().ClickOnMenu("Reports");
        domLogin.get().VerifyMenuNavigation("Reports");
        domLogin.get().ClickOnMenu("Users");
        domLogin.get().VerifyMenuNavigation("Users");

        domLogin.get().ClickOnMenuSubMenu("System Preferences", "Settings");
        domLogin.get().VerifyMenuNavigation("Settings");
        domLogin.get().ClickOnMenuSubMenu("System Preferences", "Applications");
        domLogin.get().VerifyMenuNavigation("Applications");
        domLogin.get().ClickOnMenuSubMenu("System Preferences", "Credentials");
        domLogin.get().VerifyMenuNavigation("Credentials");
        domLogin.get().ClickOnMenuSubMenu("System Preferences", "Rules");
        domLogin.get().VerifyMenuNavigation("Rules");
        domLogin.get().ClickOnMenuSubMenu("System Preferences", "Support");
        domLogin.get().VerifyMenuNavigation("Support");
        domLogin.get().ClickOnMenuSubMenu("System Preferences", "Warranties");
        domLogin.get().VerifyMenuNavigation("Warranties");
        domLogin.get().ClickOnMenu("Help");
        domLogin.get().VerifyMenuNavigation("Help");

    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("VinMotion- Franchise and Dealer Selector")
    @Epic("VinMotion")
    @Feature("Default Dealer")
    @Story("MICTEST-241")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_241(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        dealer.get().VerifyDefaultDealerChanges();
        dealer.get().EnterTestStep("< ------------------------- Step 1 Completed ------------------------- >");
        dealer.get().ChangeDealerVerification();
        dealer.get().EnterTestStep("< ------------------------- Step 2 Completed ------------------------- >");
        dealer.get().DealerNotChangeCancel();
        dealer.get().EnterTestStep("< ------------------------- Step 3 Completed ------------------------- >");
        dealer.get().ClickOutSideDealerSelectionBox();
        dealer.get().EnterTestStep("< ------------------------- Step 4 Completed ------------------------- >");
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Add Passenger Vehicle")
    @Epic("Inventory Grid")
    @Feature("Add Vehicle")
    @Story("MCITEST-308")
    @Severity(SeverityLevel.CRITICAL)
    public void MCITEST_308(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Inventory");
        invent.get().AddNewVehicleAndValidation();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area ")
    @Epic("VinMotion")
    @Feature("Access User Permissions")
    @Story("MCITEST-277")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_277(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().OpenPermission();
        //invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : Inventory")
    @Epic("VinMotion")
    @Feature("Add Permission Inventory")
    @Story("MCITEST-278")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_278(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("Inventory");
        //invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : Appraisal")
    @Epic("VinMotion")
    @Feature("Add Permission Appraisal")
    @Story("MCITEST-279")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_279(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("Appraisal");
        //invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : CoreData")
    @Epic("VinMotion")
    @Feature("Add Permission CoreData")
    @Story("MCITEST-280")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_280(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("CoreData");
        //invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : Setting")
    @Epic("VinMotion")
    @Feature("Add Permission Setting")
    @Story("MCITEST-281")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_281(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("Setting");
        //invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : Sourcing")
    @Epic("VinMotion")
    @Feature("Add Permission Sourcing")
    @Story("MCITEST-282")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_282(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("Sourcing");
        //invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : Dashboard")
    @Epic("VinMotion")
    @Feature("Add Permission Dashboard")
    @Story("MCITEST-283")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_283(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("Dashboard");
       // invent.get().ClickOnFooterSave();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Assign User Permission Area : Reports")
    @Epic("VinMotion")
    @Feature("Add Permission Reports")
    @Story("MCITEST-283")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_286(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Users");
        user.get().AddPermission("Reports");
        //invent.get().ClickOnFooterSave();
    }

    //VinMotion-
    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Left Screen Navigation Menu>Dealer Overview>Days Supply")
    @Epic("VinMotion")
    @Feature("Dealer Overview>Days Supply")
    @Story("MCITEST-258")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_258(String Browser) throws Throwable
    {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Dealer Overview");
        dealer.get().ClickDifferentRangeAndVerify();
        dealer.get().ColouredButtonToggleValidation();
        dealer.get().AttentionMenuValueVerification();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Left Screen Navigation Menu>Dealer Overview>Market Radius")
    @Epic("VinMotion")
    @Feature("Dealer Overview>Market Radius")
    @Story("MCITEST-260")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_260(String Browser) throws Throwable
    {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Dealer Overview");
        dealer.get().MarketRadiusChangeVerification();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Left Screen Navigation Menu>Dealer Overview>Market Radius")
    @Epic("VinMotion")
    @Feature("Dealer Overview>Market Radius")
    @Story("MCITEST-261")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_261(String Browser) throws Throwable
    {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Dealer Overview");
        dealer.get().MonthPickerVerification();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Left Screen Navigation Menu>Reports>Scheduler")
    @Epic("VinMotion")
    @Feature("Reports>Scheduler")
    @Story("MCITEST-266")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_266(String Browser) throws Throwable
    {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Reports");
        report.get().CreatingReport_Scheduler();
        report.get().EditExistingReportAndDisable();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Left Screen Navigation Menu>Reports>Scheduler")
    @Epic("VinMotion")
    @Feature("Reports>Scheduler")
    @Story("MCITEST-267")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_267(String Browser) throws Throwable
    {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Reports");
        report.get().CreatingReport_Scheduler();
        report.get().EditExistingReportAndDisable();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Left Screen Navigation Menu>Reports>Scheduler")
    @Epic("VinMotion")
    @Feature("Reports>Scheduler")
    @Story("MCITEST-268")
    @Severity(SeverityLevel.NORMAL)
    public void MCITEST_268(String Browser) throws Throwable
    {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Reports");
        report.get().CreatingReport_Scheduler();
        report.get().EditExistingReportAndDisable();
    }
}
