package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.xmlreader;

public class InventoryHelper
{
    xmlreader inventoryLoc = new xmlreader("src\\test\\resources\\locators\\Inventory.xml");
    public webHelper webDriver;


    public InventoryHelper(WebDriver driver)
    {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public void ClickAddVehicle() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Addvehicle")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Green + Icon (Add Vehicle Plus)'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    public void SelectSingleVehicle() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/SingleVehicle")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select : 'Single Vehicle'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    public void PopupWithDefaultValues() throws InterruptedException, DocumentException {
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/SingleVehicle")),"Add Vehicle Popup is not appeared");

        WebElement ele=webDriver.getwebelement(inventoryLoc.getlocator("//locators/Passenger"));
        String isChecked=ele.getAttribute("checked");

        System.out.println("/n"+isChecked);
        Assert.assertTrue(isChecked.equalsIgnoreCase("true"),"By default Passenger radio button is not Checked");

        ele=webDriver.getwebelement(inventoryLoc.getlocator("//locators/Used"));
        isChecked=ele.getAttribute("checked");

        System.out.println("/n"+isChecked);
        Assert.assertTrue(isChecked.equalsIgnoreCase("true"),"By default Used radio button is not Checked");

        ele=webDriver.getwebelement(inventoryLoc.getlocator("//locators/Average"));
        isChecked=ele.getAttribute("checked");

        System.out.println("/n"+isChecked);
        Assert.assertTrue(isChecked.equalsIgnoreCase("true"),"By default Average radio button is not Checked");

        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'Add Vehicle Pop is displayed and <\br> , Passenger, Used and Average radio buttons are checked by default'");

    }
    public void AddVehicle() throws Exception {
        ClickAddVehicle();
        SelectSingleVehicle();
        PopupWithDefaultValues();
    }
}
