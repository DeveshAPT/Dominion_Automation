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
    @Description("Verify Login Logout Functionality")
    @Epic("Automation")
    @Feature("Feature Login")
    @Story("MICTEST-236")
    @Step("Verify Login")
    public void MCITEST_236(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().Logout();

    }


    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Verify Forgot Password Functionality")
    @Epic("Automation")
    @Feature("Forgot Password")
    @Story("MICTEST-240")
    @Step("Verify Forgot Password")
    public void MICTEST_240(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().ClickForgotPassword();
        domLogin.get().VerifyForgotPasswordScreen();
        domLogin.get().ForgotPasswordValidation();
    }


    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Verify Additional Help Functionality")
    @Epic("Automation")
    @Feature("Additional Help Link")
    @Story("MICTEST-243")
    @Step("Verify Additional Help")
    public void MICTEST_243(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().ClickAdditionalHelp();
        domLogin.get().VerifyAdditionalHelpPage();
    }

    @Parameters("Browser")
    @Test(groups = {"web"})
    @Description("Verify Menu Navigation Functionality")
    @Epic("Automation")
    @Feature("Menu Navigation")
    @Story("MICTEST-239")
    @Step("Verify Menu Navigation")
    public void MICTEST_239(String Browser) throws Throwable {
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
    @Description("Verify Dealer Change on Franchise")
    @Epic("Automation")
    @Feature("Default Dealer")
    @Story("MICTEST-241")
    @Step("Verify Default Dealer Change")
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
    @Description("Verify Adding new Car Functionality")
    @Epic("Automation")
    @Feature("Add Car")
    @Story("MICTEST-308")
    @Step("Verify Adding new Car")
    public void MCITEST_308(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Inventory");
        invent.get().AddVehicle();
    }
}