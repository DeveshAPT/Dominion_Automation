package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
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

        String year=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearSpan")));
        System.out.println(year);
        String stock=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")));
        System.out.println(stock);
        Select yeardropdown=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearDropdown")));
        List<WebElement> selectedYear=yeardropdown.getAllSelectedOptions();

        Select make=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Make")));
        List<WebElement> selectedMake=make.getAllSelectedOptions();

        Select model=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Model")));
        List<WebElement> selectedModel=model.getAllSelectedOptions();

        Assert.assertTrue(year.contains("Year"),"Year is not blank for empty VIN number ");
        Assert.assertTrue(stock.isEmpty(),"Stock is not blank for empty VIN number ");
        Assert.assertTrue(selectedYear.size()==1,"Year is not blank for empty VIN number ");
        Assert.assertTrue(selectedMake.size()==0,"Make is not blank for empty VIN number");
        Assert.assertTrue(selectedModel.size()==0,"Model is not blank for empty VIN number ");
        //Assert.assertTrue(year.contains("Year")&& stock==""&&selectedYear.size()==1&&selectedMake.size()==0&&selectedModel.size()==0,"Year, Make, Model and Stock are showing not blank for empty VIN number ");

        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/VinInput")),Vin+ Keys.ENTER);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Vin :'"+Vin+"' and Press Enter");
        Thread.sleep(1000);
        String text=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AlertMessage")));
        System.out.println(text);
        SoftAssert sft=new SoftAssert();
        if(text.contains("THIS VIN ALREADY EXISTS."))
        {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified :'THIS VIN ALREADY EXISTS.' Message displayed for Vin Number : "+ Vin);
        }
        else {
            sft.fail("Alert Message : 'THIS VIN ALREADY EXISTS.' is not populate for VIN : " + Vin);
        }

        Boolean yr=webDriver.IsPresent(inventoryLoc.getlocator("//locators/YearSpan"));
        System.out.println(yr);

        stock=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")));
        System.out.println(stock);

        yeardropdown=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearDropdown")));
        selectedYear=yeardropdown.getAllSelectedOptions();
        String temp1=selectedYear.get(0).getText();
        System.out.println(temp1);

        make=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Make")));
        selectedMake=make.getAllSelectedOptions();
        String temp2=selectedMake.get(0).getText();
        System.out.println(temp2);

        model=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Model")));
        selectedModel=model.getAllSelectedOptions();
        String temp3=selectedModel.get(0).getText();
        System.out.println(temp3);

        Assert.assertTrue(year.contains("Year")&& stock!=""&&selectedYear.size()>0&&selectedMake.size()>0&&selectedModel.size()>0
                ,"Year, Make, Model and Stock are not showing value for empty VIN number ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Year("+temp1+"),Make("+temp2+"),Model("+temp3+") and Stock("+stock+") displayed for Vin Number : "+ Vin);

    }
    public void MandatoryFieldVerification() throws InterruptedException, DocumentException {
        String text=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/MandatoryMessage")));
        System.out.println(text);
        Assert.assertTrue(text.contains("All fields are required unless noted as optional."),"Mandatory Field Message : 'All fields are required unless noted as optional.' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified :'All fields are required unless noted as optional.' is displayed ");

    }

    public void AddVehicle() throws Exception {
        String vinNumber=GetVinNumber();
        ClickAddVehicle();
        SelectSingleVehicle();
        PopupWithDefaultValues();
        CheckExistingVinNumberAlert(vinNumber);
        MandatoryFieldVerification();
    }
}
