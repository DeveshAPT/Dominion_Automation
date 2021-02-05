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

    public void MCITEST_308(String Browser) throws Throwable {
        domLogin.get().OpenDominion();
        domLogin.get().DominionLogin();
        domLogin.get().ClickOnMenu("Inventory");
        invent.get().AddNewVehicleAndValidation();
    }
}
