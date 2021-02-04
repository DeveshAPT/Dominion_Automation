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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryHelper {
    xmlreader inventoryLoc = new xmlreader("src\\test\\resources\\locators\\Inventory.xml");
    public webHelper webDriver;


    public InventoryHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }

    public String GetVinNumber() throws InterruptedException, DocumentException {
        List<WebElement> vinlist = webDriver.getwebelements(inventoryLoc.getlocator("//locators/VinNumber"));
        WebElement ele = vinlist.get(0);
        String html = ele.getAttribute("innerHTML");
        System.out.println("%n" + html);
        String[] values = html.split(">");
        html = values[2];
        html = html.replaceAll("\\s", "");
        System.out.println("%n" + html);
        return html;
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
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/AddVehiclePopUp")), "Add Vehicle Popup is not appeared");

        WebElement ele = webDriver.getwebelement(inventoryLoc.getlocator("//locators/Passenger"));
        String isChecked = ele.getAttribute("checked");

        System.out.println("/n" + isChecked);
        Assert.assertTrue(isChecked.equalsIgnoreCase("true"), "By default Passenger radio button is not Checked");

        ele = webDriver.getwebelement(inventoryLoc.getlocator("//locators/Used"));
        isChecked = ele.getAttribute("checked");

        System.out.println("/n" + isChecked);
        Assert.assertTrue(isChecked.equalsIgnoreCase("true"), "By default Used radio button is not Checked");

        ele = webDriver.getwebelement(inventoryLoc.getlocator("//locators/Average"));
        isChecked = ele.getAttribute("checked");

        System.out.println("/n" + isChecked);
        Assert.assertTrue(isChecked.equalsIgnoreCase("true"), "By default Average radio button is not Checked");

        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : 'Add Vehicle Pop is displayed and , Passenger, Used and Average radio buttons are checked by default'");

    }

    public void CheckExistingVinNumberAlert(String Vin) throws InterruptedException, DocumentException, IOException {

        String year = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearSpan")));
        System.out.println(year);

        String stock = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")));
        System.out.println(stock);

        String maketext = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/MakeSpan")));
        System.out.println(maketext);

        String modeltext = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/ModelSpan")));
        System.out.println(modeltext);

        Assert.assertTrue(year.contains("Year"), "Year is not blank for empty VIN number ");
        Assert.assertTrue(stock.isEmpty(), "Stock is not blank for empty VIN number ");
        Assert.assertTrue(maketext.contains("Make"), "Make is not blank for empty VIN number ");
        Assert.assertTrue(modeltext.contains("Model"), "Model is not blank for empty VIN number ");

        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/VinInput")), Vin + Keys.ENTER);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Vin :'" + Vin + "' and Press Enter");
        Thread.sleep(1000);
        String text = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AlertMessage")));
        System.out.println(text);
        SoftAssert sft = new SoftAssert();
        if (text.contains("THIS VIN ALREADY EXISTS.")) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Verified :'THIS VIN ALREADY EXISTS.' Message displayed for Vin Number : " + Vin);
        } else {
            sft.fail("Alert Message : 'THIS VIN ALREADY EXISTS.' is not populate for VIN : " + Vin);
            sft.assertFalse(true, "Alert Message : 'THIS VIN ALREADY EXISTS.' is not populate for VIN : " + Vin);
        }

        /*Boolean yr=webDriver.IsPresent(inventoryLoc.getlocator("//locators/YearSpan"));
        System.out.println(yr);

        Boolean mke=webDriver.IsPresent(inventoryLoc.getlocator("//locators/MakeSpan"));
        System.out.println(yr);

        Boolean mdl=webDriver.IsPresent(inventoryLoc.getlocator("//locators/ModelSpan"));
        System.out.println(yr);

        stock=webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")));
        System.out.println(stock);

        Assert.assertFalse(yr,"Year is showing blank for VIN ("+Vin+") number ");
        Assert.assertFalse(stock.isEmpty(),"Stock is showing blank for VIN ("+Vin+") number ");
        Assert.assertFalse(mke,"Make is Showing blank for VIN ("+Vin+") number ");
        Assert.assertFalse(mdl,"Model is Showing blank for  VIN ("+Vin+") number ");

        Select yeardropdown=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearDropdown")));
        List<WebElement> selectedYear=yeardropdown.getAllSelectedOptions();
        String temp1=selectedYear.get(0).getText();
        System.out.println(temp1);

        Select make=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Make")));
        List<WebElement> selectedMake=make.getAllSelectedOptions();
        String temp2=selectedMake.get(0).getText();
        System.out.println(temp2);

        Select model=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Model")));
        List<WebElement> selectedModel=model.getAllSelectedOptions();
        String temp3=selectedModel.get(0).getText();
        System.out.println(temp3);

        Assert.assertTrue(year.contains("Year")&& stock!=""&&selectedYear.size()>0&&selectedMake.size()>0&&selectedModel.size()>0
                ,"Year, Make, Model and Stock are not showing value for empty VIN number ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Year("+temp1+"),Make("+temp2+"),Model("+temp3+") and Stock("+stock+") displayed for Vin Number : "+ Vin);*/

    }

    public void MandatoryFieldVerification() throws Exception {
        List<String> errorList = new ArrayList<String>();
        errorList.add("Please provide a valid Vin.");
        errorList.add("Please provide a valid Stock.");
        errorList.add("Please provide a valid Year.");
        errorList.add("Please provide a valid Model.");
        errorList.add("Please provide a valid Make.");
        errorList.add("Please provide a valid Engine.");
        errorList.add("Please provide a valid Transmission.");
        errorList.add("Please provide a valid Trim.");
        errorList.add("Please provide a valid Style.");

        String text = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/MandatoryMessage")));
        System.out.println(text);
        Assert.assertTrue(text.contains("All fields are required unless noted as optional."), "Mandatory Field Message : 'All fields are required unless noted as optional.' is not displayed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified :'All fields are required unless noted as optional.' is displayed ");

        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/VinInput")), "" + Keys.ENTER);

        // ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Vin :'"+Vin+"' and Press Enter");
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddBtn")));
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        List<WebElement> error = webDriver.getwebelements(inventoryLoc.getlocator("//locators/ErrorMessage"));
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < error.size(); i++) {
            WebElement ele = error.get(i);
            text = ele.getText();
            if (errorList.indexOf(text) > -1) {
                str.append(text + "<br>");
                errorList.remove(text);
            } else
                Assert.fail("Mandatory Field Message : '" + text + " not matched");

        }
        int k = errorList.size();
        Assert.assertTrue(k == 0, "All Mandatory Field Error message not  verified ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified :'" + str.toString() + "'");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : non mandatory fields 'Mileage','Fuel Type','Segment' and 'Segment Size'");
    }

    public void MileageValidation() throws InterruptedException, DocumentException, IOException {
        WebElement mileage = webDriver.getwebelement(inventoryLoc.getlocator("//locators/MileageInput"));
        webDriver.ClearAndSendKeys(mileage, "ABCd");
        mileage = webDriver.getwebelement(inventoryLoc.getlocator("//locators/MileageInput"));
        String temp = mileage.getAttribute("value");
        Assert.assertTrue(temp.isEmpty(), "Failed: Mileage Field Can accept alphabetic values");

        webDriver.ClearAndSendKeys(mileage, "%$#");
        mileage = webDriver.getwebelement(inventoryLoc.getlocator("//locators/MileageInput"));
        temp = mileage.getAttribute("value");
        Assert.assertTrue(temp.isEmpty(), "Failed: Mileage Field Can Special Characters also");

        webDriver.ClearAndSendKeys(mileage, "1234567890");
        mileage = webDriver.getwebelement(inventoryLoc.getlocator("//locators/MileageInput"));
        temp = mileage.getAttribute("value");
        Assert.assertFalse(temp.length() > 9, "Failed: Mileage Field Can accept more then 9 Characters");

        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Mileage accept only numeric value and max length upto 9");
    }

    public void EnterMileage(String mileageIN) throws IOException, InterruptedException, DocumentException {
        WebElement mileage = webDriver.getwebelement(inventoryLoc.getlocator("//locators/MileageInput"));
        webDriver.ClearAndSendKeys(mileage, mileageIN);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter : Mileage as " + mileageIN);
    }

    public void BlankFuelSegmentSize() throws InterruptedException, DocumentException {
        Select fuel = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/FuelType")));
        List<WebElement> opt = fuel.getOptions();
        Assert.assertTrue(opt.isEmpty(), "Fuel type populate without for blank VIN number");

        Select segment = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Segment")));
        opt = segment.getOptions();
        Assert.assertTrue(opt.isEmpty(), "Segment type populate without for blank VIN number");

        Select size = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/SegmentSize")));
        opt = size.getOptions();
        Assert.assertTrue(opt.isEmpty(), "Size type populate without for blank VIN number");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Fuel Type, Segment and Segment Size are not populate for empty VIN");
    }

    public String GenerateRandom(int size) {
        String[] alp = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random rnd = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0 || i % 3 == 0) {
                str.append(Integer.toString(rnd.nextInt(9)));
            } else if (i % 3 == 0 || i % 5 == 0) {
                str.append(alp[rnd.nextInt(26)]);
            } else
                str.append(alp[rnd.nextInt(26)]);
        }
        return str.toString();
    }

    public void AddVehicle() throws Exception {
        String newVIN = "VIN" + GenerateRandom(14);
        System.out.println(newVIN);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/VinInput")), newVIN + Keys.TAB);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Entered : VIN number as : " + newVIN);

        String stk = GenerateRandom(26);
        System.out.println(stk);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")), stk + Keys.TAB);
        WebElement ele = webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock"));
        String temp = ele.getAttribute("value");
        Assert.assertTrue(temp.length() == 25, "Failed : Stock can accept more then 25 characters");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : Stock can accept max 25 Character : ");
        stk = "NUM" + GenerateRandom(7);
        System.out.println(stk);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")), stk + Keys.TAB);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Entered  : Stock as : " + stk);
        YearRangeVerification();
        SelectYear("2018");
        SelectMake("Load All Makes...");
        SelectMake("Audi");
        SelectModel("Other");
        CustomMakeAddAndValidation();
        BlankFieldsForCustomeModel();
        SelectMake("Ford");
        SelectModel("Flex");
        SelectTrims("SEL");
        SelectStyle("SEL 4dr Crossover");
        AutoSelectedFieldsForExistingModel();
        EnterMileage("20");
        AddAndVerifyVehicleScreen();
    }

    public void YearRangeVerification() throws Exception {
        Select yearsDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearDropdown")));
        List<WebElement> years = yearsDrop.getOptions();
        for (WebElement ele : years) {
            String temp = ele.getText();
            if (!temp.equalsIgnoreCase("Select")) {
                int yr = Integer.parseInt(temp);
                Assert.assertTrue(yr >= 1900 && yr <= 2022, "Failed : Year Option : " + Integer.toString(yr) + " out of range 1900 to 2022 ");
            }
        }
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : Year option are within range 1900 to 2022");

    }

    public void SelectYear(String year) throws Exception {
        Select yearsDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearDropdown")));
        List<WebElement> years = yearsDrop.getOptions();
        yearsDrop.selectByValue(year);
    }

    public void SelectMake(String make) throws Exception {
        Select makeDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Make")));

        makeDrop.selectByVisibleText(make);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Make As " + make);
    }

    public void SelectModel(String model) throws Exception {
        Select modelDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Model")));
        modelDrop.selectByVisibleText(model);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Model As " + model);
    }

    public void SelectTrims(String trims) throws InterruptedException, DocumentException {
        Select trimsDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Trims")));
        trimsDrop.selectByVisibleText(trims);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Trims As " + trimsDrop);
    }

    public void SelectStyle(String styles) throws InterruptedException, DocumentException {
        Select styleDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Styles")));
        styleDrop.selectByVisibleText(styles);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Trims As " + styles);
    }

    public void CustomMakeAddAndValidation() throws Exception {
        Random rnd = new Random();
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/CustomModelSpan")), "Failed : Custom Model is not display for other Model");
        String text = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/CustomModelSpan")));
        Assert.assertTrue(text.equalsIgnoreCase("Other"), "Failed : Other is not selected for Custom Model");

        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/CustomTrimsSpan")), "Failed : Custom Trims is not display for other Model");
        text = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/CustomTrimsSpan")));
        Assert.assertTrue(text.equalsIgnoreCase("Other"), "Failed : Other is not selected for Custom Trims");

        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/CustomStyleSpan")), "Failed : Custom Style is not display for other Model");
        text = webDriver.GetText(webDriver.getwebelement(inventoryLoc.getlocator("//locators/CustomStyleSpan")));
        Assert.assertTrue(text.equalsIgnoreCase("Other"), "Failed : Other is not selected for Custom Trims");

        String custModel = "TestModel" + Integer.toString(rnd.nextInt(9999));
        String custTrims = "TestTrim" + Integer.toString(rnd.nextInt(9999));
        String custStyle = "TestStyle" + Integer.toString(rnd.nextInt(9999));

        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/CustomModelInput")), custModel);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/CustomTrimsInput")), custTrims);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/CustomStyleInput")), custStyle);

        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter  : Model(" + custModel + "), Trims(" + custTrims + ") and Style(" + custStyle + ") for Other Model");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : User Can enter Other Model also");
    }

    public void BlankFieldsForCustomeModel() throws InterruptedException, DocumentException {
        Select engine = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Engine")));
        List<WebElement> opt = engine.getAllSelectedOptions();
        Assert.assertTrue(opt.isEmpty(), "Fuel type is auto-selected for Other Model");

        Select tran = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Transmission")));
        opt = tran.getAllSelectedOptions();
        Assert.assertTrue(opt.size() == 1, "Transmission type is  auto-selected for Other Model");

        Select fuel = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/FuelType")));
        opt = fuel.getAllSelectedOptions();
        Assert.assertTrue(opt.size() == 1, "Fuel type is  auto-selected for Other Model");

        Select segment = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Segment")));
        opt = segment.getAllSelectedOptions();
        Assert.assertTrue(opt.size() == 1, "Segment type is  auto-selected for Other Model");

        Select size = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/SegmentSize")));
        opt = size.getAllSelectedOptions();
        Assert.assertTrue(opt.size() == 1, "Size type is not auto-selected for Other Model");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Engine Type,Transmission Type, Fuel Type, Segment and Segment Size are not auto selected  for Other Model");
    }

    public void AutoSelectedFieldsForExistingModel() throws InterruptedException, DocumentException {
        Select engine = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Engine")));
        String selText = engine.getFirstSelectedOption().getText();
        List<WebElement> opt = engine.getOptions();
        boolean found = false;
        for (WebElement ele : opt) {
            String temp = ele.getText();
            if (temp.equalsIgnoreCase(selText)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Fuel type is auto-selected for Existing Model");

        Select tran = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Transmission")));
        opt = tran.getOptions();
        selText = tran.getFirstSelectedOption().getText();
        found = false;
        for (WebElement ele : opt) {
            String temp = ele.getText();
            if (temp.equalsIgnoreCase(selText)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Transmission type is auto-selected for Existing Model");

        Select fuel = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/FuelType")));
        opt = fuel.getOptions();
        selText = fuel.getFirstSelectedOption().getText();
        found = false;
        for (WebElement ele : opt) {
            String temp = ele.getText();
            if (temp.equalsIgnoreCase(selText)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Fuel type not auto-selected for Existing Model");

        Select segment = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Segment")));
        opt = segment.getOptions();
        selText = segment.getFirstSelectedOption().getText();
        found = false;
        for (WebElement ele : opt) {
            String temp = ele.getText();
            if (temp.equalsIgnoreCase(selText)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Segment type is not auto-selected for Existing Model");

        Select size = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/SegmentSize")));
        opt = size.getOptions();
        selText = size.getFirstSelectedOption().getText();
        found = false;
        for (WebElement ele : opt) {
            String temp = ele.getText();
            if (temp.equalsIgnoreCase(selText)) {
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Size type is not auto-selected for Existing Model");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Engine Type,Transmission Type, Fuel Type, Segment and Segment Size are auto selected for Existing Model");
    }

    public void AddAndVerifyVehicleScreen() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddButton")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on : Add Vehicle Add Button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        webDriver.WaitforControlClickable(inventoryLoc.getlocator("//locators/vehicleDetail"));

        boolean f1=webDriver.IsPresent(inventoryLoc.getlocator("//locators/vehicleDetail"));
        boolean f2=webDriver.IsPresent(inventoryLoc.getlocator("//locators/VehicleTabs"));
        Assert.assertTrue(f1&&f2, "Verified : Vehicle Detail Screen Loaded and Vehicle Added Successfully");

        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Save")));
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

    }

    public void AddNewVehicleAndValidation() throws Exception {

        String vinNumber = GetVinNumber();
        ClickAddVehicle();
        SelectSingleVehicle();
        PopupWithDefaultValues();
        CheckExistingVinNumberAlert(vinNumber);
        MandatoryFieldVerification();
        MileageValidation();
        BlankFuelSegmentSize();
        AddVehicle();
    }
}
