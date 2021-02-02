package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.xmlreader;

import java.io.IOException;
import java.util.List;

public class InventoryHelper
{
    xmlreader inventoryLoc = new xmlreader("src\\test\\resources\\locators\\Inventory.xml");
    public webHelper webDriver;


    public InventoryHelper(WebDriver driver)
    {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public String GetVinNumber() throws InterruptedException, DocumentException {
        List<WebElement> vinlist=webDriver.getwebelements(inventoryLoc.getlocator("//locators/VinNumber"));
        WebElement ele=vinlist.get(0);
        String html=ele.getAttribute("innerHTML");
        System.out.println("%n"+html);
        String [] values=html.split(">");
        html=values[2];
        html=html.replaceAll("\\s", "");
        System.out.println("%n"+html);
        return  html;
    }

    public void ClickAddVehicle() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddVehicle")));
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
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/AddVehiclePopUp")),"Add Vehicle Popup is not appeared");

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

        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'Add Vehicle Pop is displayed and , Passenger, Used and Average radio buttons are checked by default'");

    }
    public void CheckExistingVinNumberAlert(String Vin) throws InterruptedException, DocumentException, IOException {
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/VinInput")),Vin+ Keys.ENTER);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Vin :'"+Vin+"' and Press Enter");
        Thread.sleep(1000);
        String text=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AlertMessage")));
        System.out.println(text);
        Assert.assertTrue(text.contains("THIS VIN ALREADY EXISTS."),"Alert Message : 'THIS VIN ALREADY EXISTS.' is not populate for VIN : "+ Vin);

    }

    public void AddVehicle() throws Exception {
        String vinNumber=GetVinNumber();
        ClickAddVehicle();
        SelectSingleVehicle();
        PopupWithDefaultValues();
        CheckExistingVinNumberAlert(vinNumber);

    }
}
