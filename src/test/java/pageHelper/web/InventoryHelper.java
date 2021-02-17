package pageHelper.web;

import Reporter.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import core.baseDriverHelper;
import core.webHelper;
import io.qameta.allure.Step;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InventoryHelper {
    xmlreader inventoryLoc = new xmlreader("src\\test\\resources\\locators\\Inventory.xml");
    xmlreader dealerLoc = new xmlreader("src\\test\\resources\\locators\\DealerOverView.xml");
    public webHelper webDriver;
    CommonFunctions comm=new CommonFunctions();

    public InventoryHelper(WebDriver driver) {
        webDriver = new baseDriverHelper(driver);
        System.out.println("First Constructor");
    }
    @Step("Get Existing VIN number")
    public String GetVinNumber() throws Exception {
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

    @Step("Click on Add Vehicle Plus Icon")
    public void ClickAddVehicle() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddVehicle")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Green + Icon (Add Vehicle Plus)'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Select Single Vehicle")
    public void SelectSingleVehicle() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/SingleVehicle")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select : 'Single Vehicle'");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Verify the default selected fields for add vehicle screen")
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

    @Step("Enter Existing VIN({0}) number and Verify Error message and Blank Fields")
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

    }

    @Step("Verify Mandatory Fields are Highlighted")
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

    @Step("Verify Mileage Fields max input length and accept only numeric inputs")
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

    @Step("Enter Mileage as {0}")
    public void EnterMileage(String mileageIN) throws IOException, InterruptedException, DocumentException {
        WebElement mileage = webDriver.getwebelement(inventoryLoc.getlocator("//locators/MileageInput"));
        webDriver.ClearAndSendKeys(mileage, mileageIN);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter : Mileage as " + mileageIN);
    }

    @Step("Verify Blank field for Fuel, Segment and Size")
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

    @Step("Enter Vin Number : {0}")
    public void EnterVinNumber(String newVIN) throws Exception {
        System.out.println(newVIN);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/VinInput")), newVIN + Keys.TAB);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Entered : VIN number as : " + newVIN);
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Verify Max Length input for Stock")
    public void VerifyStockMaxLength(String stk) throws Exception {
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")), stk + Keys.TAB);
        WebElement ele = webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock"));
        String temp = ele.getAttribute("value");
        Assert.assertTrue(temp.length() == 25, "Failed : Stock can accept more then 25 characters");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified  : Stock can accept max 25 Character : ");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Enter Stock Value as {0}")
    public void EnterStockValue(String stk) throws Exception {
        System.out.println(stk);
        webDriver.ClearAndSendKeys(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Stock")), stk + Keys.TAB);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Entered  : Stock as : " + stk);
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Verify Year Range available i.e 1900 to 2022")
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

    @Step("Select Year as { 0 }")
    public void SelectYear(String year) throws Exception {
        Select yearsDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/YearDropdown")));
        List<WebElement> years = yearsDrop.getOptions();
        yearsDrop.selectByValue(year);
    }

    @Step("Select Make as { 0 }")
    public void SelectMake(String make) throws Exception {
        Select makeDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Make")));

        makeDrop.selectByVisibleText(make);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Make As " + make);
    }

    @Step("Select Model as { 0 }")
    public void SelectModel(String model) throws Exception {
        Select modelDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Model")));
        modelDrop.selectByVisibleText(model);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Model As " + model);
    }

    @Step("Select Trim as { 0 }")
    public void SelectTrims(String trims) throws InterruptedException, DocumentException {
        Select trimsDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Trims")));
        trimsDrop.selectByVisibleText(trims);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Trims As " + trimsDrop);
    }

    @Step("Select Style as { 0 }")
    public void SelectStyle(String styles) throws InterruptedException, DocumentException {
        Select styleDrop = new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Styles")));
        styleDrop.selectByVisibleText(styles);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select  : Trims As " + styles);
    }

    @Step("Verify User Can Enter Other Make , Model and Style ")
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

    @Step("Verify Blank Fields for Custom Model")
    public void BlankFieldsForCustomModel() throws InterruptedException, DocumentException {
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

    @Step("Verify Auto Populated Fields for Existing Model")
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



    @Step("Click on Add Button")
    public void ClickAddNewVehicle() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddButton")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on : Add Vehicle Add Button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

    }

    @Step("Click on Add Button and Verify Vehicle detail screen should display")
    public void VerifyAddVehicleScreen() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddButton")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on : Add Vehicle Add Button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        webDriver.WaitforControlClickable(inventoryLoc.getlocator("//locators/vehicleDetail"));

        boolean f1=webDriver.IsPresent(inventoryLoc.getlocator("//locators/vehicleDetail"));
        boolean f2=webDriver.IsPresent(inventoryLoc.getlocator("//locators/VehicleTabs"));
        Assert.assertTrue(f1&&f2, "Verified : Vehicle Detail Screen Loaded and Vehicle Added Successfully");



    }

    @Step("Click on Save")
    public void ClickOnFooterSave() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/Save")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "ClickOn : 'Save'  button");
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Click on Action")
    public void ClickActionButton() throws Exception {
        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/ActionButton")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on : Action Button");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Verify 'Add' under Single Vehicle Action")
    public void VerifyAddUnderSingleAction() throws InterruptedException, DocumentException {
        List<WebElement> lst=webDriver.getwebelements(inventoryLoc.getlocator("//locators/ActionSingleVehicle"));
        Assert.assertTrue(lst.size()>0,"'Add' Action not found under Single Vehicle Action");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : 'Add' under Single Vehicle Action ");
    }

    @Step("Click On Add and Verify Add Vehicle Pop Up")
    public void SelectAddVerifyPopUp() throws Exception {
        Select actions=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/SelectAction")));
        actions.selectByVisibleText("Add");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select : 'Add' under Single vehicle ");
        //webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

        List<WebElement> lst=webDriver.getwebelements(inventoryLoc.getlocator("//locators/AddVehiclePopUp"));
        Assert.assertTrue(lst.size()>0,"Add Vehicle pop-up is not open for 'Add' Selection under Single vehicle ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : Add Vehicle pop-up is opened for 'Add' Selection under Single vehicle ");

        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/ActionButton")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on : Action Button");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

    }

    @Step("Click On Add and Verify Add Vehicle Pop Up")
    public void CloseAddVehiclePopUpByX() throws Exception {

        webDriver.Clickon(webDriver.getwebelement(inventoryLoc.getlocator("//locators/AddVehicleClose")));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on : Add Vehicle Close Button");
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();

    }

    @Step("Verify Action Option under Batch Vehicles")
    public void BatchVehicleActionOptions() throws Exception {

        List<String> actionOption =new ArrayList<>();
        actionOption.add("Add Batch Photos");
        actionOption.add("Add Batch Vehicles");
        actionOption.add("Apply Photo Overlay");
        actionOption.add("Create Batch Stickers");
        actionOption.add("Delete All Photos");
        actionOption.add("Delete Photo Overlays");
        actionOption.add("Download All Photos");
        actionOption.add("Mark Condition As New");
        actionOption.add("Mark Condition As Used");
        actionOption.add("Mark Status As Active");
        actionOption.add("Mark Status As Pending");
        actionOption.add("Mark Status As Sold");
        actionOption.add("Mark Sale Type As Retail");
        actionOption.add("Reconditioning step up");
        actionOption.add("Mark Sale Type As Wholesale");
        actionOption.add("Reconditioning Step down");
        actionOption.add("Reconditioning Change step");
        actionOption.add("Rebuild Story");
        actionOption.add("Release Photo control");
        actionOption.add("Request C.A.R Inspection/Re-Inspection");
        actionOption.add("Syndicate C.A.R Score Report");

        List<WebElement> lst = webDriver.getwebelements(inventoryLoc.getlocator("//locators/ActionBatchVehicle"));
        Assert.assertTrue(lst.size() > 0, "Batch Vehicle Action Options are Blank");
        int count = actionOption.size();
        List<String> newOption = comm.RemoveSpaceOfAllItems(actionOption);
        for (WebElement el : lst) {
            String opt = el.getText();
            opt = comm.RemoveAllSpace(opt);
            int index = newOption.indexOf(opt);
            if (index >= 0) {
                newOption.remove(index);
            }
        }
        if(newOption.size()>0)
        {
            for(int i=0;i<newOption.size();i++)
            {
                ExtentTestManager.getTest().log(LogStatus.PASS, newOption.get(i).toString());
            }
        }
        Assert.assertFalse(newOption.size()>0,"Not all option visible in Batch Vehicle Action ");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified : All Action option under Batch Vehicle Action");

    }

    @Step("Select Vehicle Type as {0}")
    public void SelectVehicleType(String type) throws Exception {
        String locatme=null;
        if(type.equalsIgnoreCase("Passenger"))
        {
            locatme=inventoryLoc.getlocator("//locators/Passenger");
        }
        else if(type.equalsIgnoreCase("Motorcycle"))
        {
            locatme=inventoryLoc.getlocator("//locators/MotorCycle");
        }
        else if(type.equalsIgnoreCase("RV"))
        {
            locatme=inventoryLoc.getlocator("//locators/RV");
        }
        else
        {
            System.out.println("Specify the Correct Vehicle Type");
            Assert.fail("Please Specify the Correct Vehicle Type");
        }
        webDriver.Clickon(webDriver.getwebelement(locatme));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click >> Vehicle Type as : "+type );
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Select Vehicle Condition as {0}")
    public void SelectVehicleCondition(String cndtn) throws Exception {
        String locatme=null;
        if(cndtn.equalsIgnoreCase("New"))
        {
            locatme=inventoryLoc.getlocator("//locators/New");
        }
        else if(cndtn.equalsIgnoreCase("Used"))
        {
            locatme=inventoryLoc.getlocator("//locators/Used");
        }
        else if(cndtn.equalsIgnoreCase("Certified"))
        {
            locatme=inventoryLoc.getlocator("//locators/Certified");
        }
        else
        {
            System.out.println("Specify the Correct Vehicle Condition Type");
            Assert.fail("Please Specify the Correct Vehicle Condition Type");
        }
        webDriver.Clickon(webDriver.getwebelement(locatme));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click >> Vehicle Condition Type as : "+cndtn );
        webDriver.WaitforPageToBeReady();
    }

    @Step("Select Vehicle Classification as {0}")
    public void SelectVehicleClassification(String vClass) throws Exception {
        String locatme=null;
        if(vClass.equalsIgnoreCase("Rough"))
        {
            locatme=inventoryLoc.getlocator("//locators/Rough");
        }
        else if(vClass.equalsIgnoreCase("Average"))
        {
            locatme=inventoryLoc.getlocator("//locators/Average");
        }
        else if(vClass.equalsIgnoreCase("Clean"))
        {
            locatme=inventoryLoc.getlocator("//locators/Clean");
        }
        else if(vClass.equalsIgnoreCase("Extra Clean"))
        {
            locatme=inventoryLoc.getlocator("//locators/ExtraClean");
        }
        else
        {
            System.out.println("Specify the Correct Vehicle Class Type");
            Assert.fail("Please Specify the Correct Vehicle Class Type");
        }
        webDriver.Clickon(webDriver.getwebelement(locatme));
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click >> Vehicle Class Type as : "+vClass );
        webDriver.WaitforPageToBeReady();
    }

    @Step("Select Inventory Layout  as {0}")
    public void ChangeInventoryLayOut(String opt) throws Exception {
        Select layout=new Select(webDriver.getwebelement(inventoryLoc.getlocator("//locators/GridLayOut")));
        layout.selectByVisibleText(opt);
        ExtentTestManager.getTest().log(LogStatus.PASS, "Select >> Inventory layout as  : "+opt );
        webDriver.WaitloadingComplete();
        webDriver.WaitForpageload();
        webDriver.WaitforPageToBeReady();
    }

    @Step("Verify VinMotion Columns")
    public void VerifyVinMotionColumns() throws InterruptedException, DocumentException
    {
        List<WebElement> rows=webDriver.getwebelements(inventoryLoc.getlocator("//locators/VinGrid"));
        Assert.assertTrue(rows.size()>1,"No Data Present in Vin Motion Grid");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/LastChange")),"Last Change column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/DaysSupply")),"Days Supply column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Age")),"Age column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Rank")),"Rank column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Price")),"Price column is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified 'Last Change', 'Days Supply', 'Age', 'Rank ' and 'Price' is displayed" );
    }

    @Step("Verify Comments Columns")
    public void VerifyCommentsColumns() throws InterruptedException, DocumentException
    {
        List<WebElement> rows=webDriver.getwebelements(inventoryLoc.getlocator("//locators/VinGrid"));
        Assert.assertTrue(rows.size()>1,"No Data Present in Vin Motion Grid");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/VehicleYearName")),"Vehicle Name Year Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/ColVin")),"VIN Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/ColStock")),"Stock Column  is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/CommentTxt")),"Comment Input Box is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified 'Last Change', 'Days Supply', 'Age', 'Rank ' and 'Price' is displayed" );
    }

    @Step("Verify Photo And Video Columns")
    public void VerifyPhotoColumns() throws InterruptedException, DocumentException
    {
        List<WebElement> rows=webDriver.getwebelements(inventoryLoc.getlocator("//locators/VinGrid"));
        Assert.assertTrue(rows.size()>1,"No Data Present in Vin Motion Grid");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/PhotoDate")),"Photo Date Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Photo")),"Photo # Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Type")),"Type Column  is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/SuperSize")),"Super Size Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Age")),"Age Column is not present");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified 'Last Change', 'Days Supply', 'Age', 'Rank ' and 'Price' is displayed" );
    }

    @Step("Verify Pricing Columns")
    public void VerifyPricingColumns() throws InterruptedException, DocumentException
    {
        List<WebElement> rows=webDriver.getwebelements(inventoryLoc.getlocator("//locators/VinGrid"));
        Assert.assertTrue(rows.size()>1,"No Data Present in Vin Motion Grid");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/RetailPrize")),"Retail Price Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/InputPrice")),"Input Retail Price is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/CompPrize")),"Comp Price Column  is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/InputCost")),"Input Comp Prize is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/ACV")),"ACV Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/InputAcv")),"Input ACV is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/Profit")),"Profit Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/InputProfit")),"Input Profit is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/RecPrice")),"Rec Price Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/InputPercent")),"Input Rec Price  is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/ToMarket")),"To Market Column is not present");
        Assert.assertTrue(webDriver.IsPresent(inventoryLoc.getlocator("//locators/MarketRank")),"Market Rank Column is not present");

        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified 'Retail Price', 'Comp Price', 'ACV', 'Profit','Rec Price','To Market'  and 'Market Rank ' is displayed" );
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verified Input Text Box for 'Retail Price', 'Comp Price', 'ACV', 'Profit',and 'Rec Price' is displayed" );
    }

    public void VerifyDifferentLayoutAndColumns() throws Exception {
        ChangeInventoryLayOut("VinMotion");
        VerifyVinMotionColumns();
        ChangeInventoryLayOut("Comments");
        VerifyCommentsColumns();
        ChangeInventoryLayOut("Photos & Videos");
        VerifyPhotoColumns();
        ChangeInventoryLayOut("Pricing");
        VerifyPricingColumns();

    }

    public void AddRV() throws Exception
    {
        String vinNumber = GetVinNumber();
        ClickAddVehicle();
        SelectSingleVehicle();
        SelectVehicleType("RV");
        String newVIN = "VIN" + GenerateRandom(14);
        EnterVinNumber(newVIN);
        String stk = GenerateRandom(26);
        System.out.println(stk);
        VerifyStockMaxLength(stk);
        stk = "NUM" + GenerateRandom(7);
        EnterStockValue(stk);
        Thread.sleep(5000);
        SelectYear("2017");
        Thread.sleep(5000);
        SelectMake("Alfa");
        Thread.sleep(5000);
        SelectModel("Ideal 29 RL");
        Thread.sleep(5000);
        EnterMileage("15");
        SelectVehicleCondition("New");
        SelectVehicleCondition("Used");
        SelectVehicleCondition("Certified");
        SelectVehicleCondition("Used");
        SelectVehicleClassification("Rough");
        SelectVehicleClassification("Average");
        SelectVehicleClassification("Clean");
        SelectVehicleClassification("Extra Clean");
        SelectVehicleClassification("Average");
        ClickAddNewVehicle();
        ClickOnFooterSave();


    }

    public void AddNewVehicleAndValidation() throws Exception
    {

        String vinNumber = GetVinNumber();
        ClickAddVehicle();
        SelectSingleVehicle();
        PopupWithDefaultValues();
        CheckExistingVinNumberAlert(vinNumber);
        MandatoryFieldVerification();
        MileageValidation();
        BlankFuelSegmentSize();
        String newVIN = "VIN" + GenerateRandom(14);
        EnterVinNumber(newVIN);
        String stk = GenerateRandom(26);
        System.out.println(stk);
        VerifyStockMaxLength(stk);
        stk = "NUM" + GenerateRandom(7);
        EnterStockValue(stk);
        YearRangeVerification();
        SelectYear("2018");
        SelectMake("Load All Makes...");
        SelectMake("Audi");
        SelectModel("Other");
        CustomMakeAddAndValidation();
        BlankFieldsForCustomModel();
        SelectMake("Ford");
        SelectModel("Flex");
        SelectTrims("SEL");
        SelectStyle("SEL 4dr Crossover");
        AutoSelectedFieldsForExistingModel();
        EnterMileage("20");
        ClickAddNewVehicle();
        VerifyAddVehicleScreen();
        ClickOnFooterSave();
    }
}
