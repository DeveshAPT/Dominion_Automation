package core;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.JavascriptExecutor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cucumber.listener.Reporter;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import Reporter.ExtentTestManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class baseDriverHelper implements apiHelper, webHelper {

    WebDriver driver;
    RequestSpecification Request;
    public Wait<WebDriver> wait;
    WebElement el = null;
    List<WebElement> ellist;
    public static Response Resultrespoence;
    public static String Payload = "";
    public final static String Contenttype = "application/xml";
    public static String WindDirectionDegree;
    public static Properties propinhelper;
    String Root = "src\\test\\resources\\apiResourceTemplate\\";

    public baseDriverHelper(WebDriver dr) {
        driver = dr;
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)

        ;
    }

    public baseDriverHelper(RequestSpecification dr, Response respoence) {

        Request = dr;
        Resultrespoence = respoence;
        propinhelper = new Properties();
    }

    /*	Start of the Web Driver Helper area which contains all the base methods related to the Web Driver

     *------------------------------------------------------------------------------------------
     *
     */
    public void javascriptexecutor(WebElement el) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", el);
        js.executeScript("window.scrollTo(0, 0)");
        //window.scrollTo(0, 0);
    }
    @Override
    public void JavaScriptMouseHover(WebElement el)  {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].onmouseover();", el);
        //js.executeScript("window.scrollTo(0, 0)");
        //window.scrollTo(0, 0);
    }

    @Override
    public boolean IsPresent(String locator) {
        try {
            driver.findElement(By.xpath(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean IsNotPresent(String locator) {
        try {
            driver.findElement(By.xpath(locator));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Override
    public List<WebElement> getwebelements(String locator) {
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        return elements;
    }

    public void javascriptexecutor2(String st) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document." + st + ".submit();");
        //js.executeScript("window.scrollTo(0, 0)");
        //window.scrollTo(0, 0);
    }

    public void ProxyLogout() throws Exception {
        OpenURLV2(Getkeyvalue("CPQ_URL") + "///Logout.jsp?proxy_//Logout=true&_bm_trail_refresh_=true");
        ////Log.info("Performing Proxy //Logout");
        System.out.println("Proxy //Logout is called");
        Thread.sleep(8000);
    }


    public boolean checkOptions(String[] expected, WebElement el) {
        List<WebElement> options = el.findElements(By.xpath(".//option"));
        int k = 0;
        for (WebElement opt : options) {
            if (!opt.getText().equals(expected[k])) {
                return false;
            }
            k = k + 1;
        }
        return true;
    }

    public void javascriptexecutor2(WebElement el) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", el);
        //js.executeScript("window.scrollTo(0, 0)");
        //window.scrollTo(0, 0);
    }

    public void SendKeyswithAction(WebElement el, String key) throws Exception {

        Clickon(el);
        Actions action = new Actions(driver);

        action.sendKeys(key).build().perform();
        action.sendKeys(Keys.ENTER).build().perform();

    }

    public void ClickswithAction(String el) throws InterruptedException {


        Actions action = new Actions(driver);

        action.click(driver.findElement(By.xpath(el))).build().perform();
        //action.sendKeys(Keys.TAB).build().perform();

    }

    public void WaitforElementtobeclickable(String locator) throws InterruptedException {
        System.out.println("In Wait for Element Clickable method for - " + locator);
        //waitForpageload();
        if (locator.startsWith("//") || locator.startsWith("(")) {

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active" + locator);
            //Thread.sleep(2000);
        } else if (locator.startsWith("name")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.name(locator.split("=")[1])));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active" + locator);
            //Thread.sleep(2000);

        } else if (locator.startsWith("id")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id(locator.split("=")[1])));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active" + locator);
            //Thread.sleep(2000);

        }
    }




    public void Switchtotabandsignthequote() throws Exception {
        String parentWinHandle = driver.getWindowHandle();
        Set<String> totalopenwindow = driver.getWindowHandles();
        for (String handle : totalopenwindow) {
            if (!handle.equals(parentWinHandle)) {
                driver.switchTo().window(handle);
                Thread.sleep(12000);
                try {
                    safeJavaScriptClick(getwebelement("//*[@id='disclosureAccepted']"));
                } catch (Exception e) {
                    Clickon(getwebelement("//*[text()='Required']"));
                }
                Clickon(getwebelement("//button[text()='Continue']"));
                Clickon(getwebelement("//button[@data-qa='SignHere']"));
                Clickon(getwebelement("//div[@class='page-tabs']"));
                //create object 'action' of Actions class
                //Dragedrop(getwebelement("//button[@data-qa='SignHere']"),getwebelement("//div[@class='page-tabs']"));
                Thread.sleep(10000);
                Clickon(getwebelement("//button[text()='Adopt and Sign']"));
//    		Thread.sleep(10000);
//    		Clickon(getwebelement("//button[text()='Ok']"));
                Thread.sleep(10000);
                WaitforElementtobeclickable("//button[text()='Finish']");
                Clickon(getwebelement("//button[text()='Finish']"));
                WaitforElementtobeclickable("(//button[text()='Continue'])[2]");
                Clickon(getwebelement("(//button[text()='Continue'])[2]"));
                Thread.sleep(10000);
            }
        }
        driver.close();
        driver.switchTo().window(parentWinHandle);
    }


    public void Getmaploaded(String framlocator, String messagelocator) throws InterruptedException {
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id(framlocator.split("=")[1])));
        //try {
        System.out.println("Code for Map Loading Start");
        //Thread.sleep(3000);
        String[] finalval = framlocator.split("=");
        //Thread.sleep(3000);
        WaitforElementtobeclickable(framlocator);

        driver.switchTo().frame(driver.findElement(By.id(finalval[1])));
        System.out.println("Switched to Iframe");

        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(messagelocator)));
        System.out.println("Element found and Waiting");
        System.out.println(driver.findElement(By.xpath(messagelocator)).getText().toString());
        driver.switchTo().defaultContent();
        //ExpectedConditions.elementToBeClickable(locator)
        //Thread.sleep(2000);
        System.out.println("Code for Map Loading End ");
        Thread.sleep(2000);
        //}
        //}
//		catch(Exception e)
//		{
//			System.out.println("In catch"+e.getMessage());
//			Thread.sleep(1000);
//			driver.switchTo().defaultContent();
//			System.out.println("Switched to Default Content");
//			Getmaploaded(framlocator,messagelocator);
//			
//		}

    }

    public void WaitforFiletobeDownloaded(String Filename) throws InterruptedException {
        String str = System.getProperty("user.dir") + "\\src\\Data\\Downloads\\" + Filename;
        File file = new File(str);
        wait.until((driver) -> file.exists());
    }

    public WebElement getwebelement(String locator) throws InterruptedException {
        //WaitforElementtobeclickable(locator);
        System.out.print("In Get element method for - " + locator);
        String[] finalval;
        try {
            if (locator.startsWith("name")) {
                finalval = locator.split("=");
                //Log.info(finalval[1]);
                //Log.info("Indriverhelper"+driver);
                //wait.until();
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        el = driver.findElement(By.id(finalval[1]));
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(el));
                        } catch (Exception e) {
                            wait.until(ExpectedConditions.visibilityOf(el));
                        }
                        //wait.until(el.isEnabled());
                        return el;
                    }
                });
                //wait.until(ExpectedConditions.stalenessOf(element))
            } else if (locator.startsWith("id")) {
                finalval = locator.split("=");
                //Log.info(finalval[1]);
                //Log.info("Indriverhelper"+driver);
                el = driver.findElement(By.id(finalval[1]));
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        el = driver.findElement(By.id(finalval[1]));
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(el));
                        } catch (Exception e) {
                            wait.until(ExpectedConditions.visibilityOf(el));
                        }
                        //wait.until(el.isEnabled());
                        return el;
                    }
                });

            } else if (locator.startsWith("//") || locator.startsWith("(//") || locator.startsWith("(")) {
                //el=driver.findElement(By.xpath(locator));
                wait.until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        el = driver.findElement(By.xpath(locator));
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(el));
                        } catch (Exception e) {
                            wait.until(ExpectedConditions.visibilityOf(el));
                        }
                        return el;
                    }
                });

            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
            //getwebelement(locator);
        }
        //Thread.sleep(1000);
//	try {
//		System.out.println("Is the element is enabled-"+el.isEnabled());
//	}
//	catch(Exception e)
//	{
//		System.out.println(e.getMessage().toString());
//	}
        return el;
    }


    public WebElement ReturnElement(String locator) {

        System.out.print("In Get element method for - " + locator);
        String[] finalval;
        try {
            if (locator.startsWith("name")) {
                finalval = locator.split("=");
                ////Log.info(finalval[1]);
                ////Log.info("Indriverhelper"+driver);
                //wait.until();
                el = driver.findElement(By.name(finalval[1]));


                //wait.until(ExpectedConditions.stalenessOf(element))
            } else if (locator.startsWith("id")) {
                finalval = locator.split("=");
                ////Log.info(finalval[1]);
                ////Log.info("Indriverhelper"+driver);
                el = driver.findElement(By.id(finalval[1]));


                //el= driver.findElement(By.id(finalval[1]));
            } else if (locator.startsWith("//") || locator.startsWith("(//") || locator.startsWith("(")) {
                el = driver.findElement(By.xpath(locator));


            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
            //getwebelement(locator);
        }
        //Thread.sleep(1000);
        try {
            System.out.println("Is the element is enabled-" + el.isEnabled());
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        return el;

    }

    public WebElement getwebelement2(String locator) throws InterruptedException {
        if (locator.startsWith("//") || locator.startsWith("(//")) {
            el = (WebElement) driver.findElement(By.xpath(locator));
            return driver.findElement(By.xpath(locator));
        } else if (locator.startsWith("id")) {
            el = (WebElement) driver.findElement(By.id(locator.split("=")[1]));
            return driver.findElement(By.xpath(locator));
        } else if (locator.startsWith("name")) {
            el = (WebElement) driver.findElement(By.name(locator.split("=")[1]));
            return driver.findElement(By.xpath(locator));
        }
        Thread.sleep(1000);
        return el;
    }

    public void clickOKonError() throws Exception {
        if (isElementPresent("//*[contains(@class,'dia//Log-footer')]/button[string()='OK']")) {
            while (isElementPresent("//*[contains(@class,'dia//Log-footer')]/button[string()='OK']")) {

                Clickon(getwebelement2("//*[contains(@class,'dia//Log-footer')]/button[string()='OK']"));

            }
        }
    }

    public void clickOKonError2(String Locator) throws Exception {
        safeJavaScriptClick(getwebelement(Locator));
        WaitForpageload();
        if (isElementPresent("//*[contains(@class,'dia//Log-footer')]/button[string()='OK']")) {
            while (isElementPresent("//*[contains(@class,'dia//Log-footer')]/button[string()='OK']")) {

                Clickon(getwebelement2("//*[contains(@class,'dia//Log-footer')]/button[string()='OK']"));
                PageRefresh();
                WaitForpageload();
                safeJavaScriptClick(getwebelement(Locator));
                WaitForpageload();
                Thread.sleep(5000);
            }


        }
    }


    public void Submitform(WebElement el) {
        el.submit();
    }

    public void Clickon(WebElement el) throws Exception {
        //Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {


            //use executeScript() method and pass the arguments
            //Here i pass values based on css style. Yellow background color with solid red color border.
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
            el.click();

            System.out.println("try to click on the element ");
            //    ExtentTestManager.getTest().//Log(//LogStatus.PASS, " Step: Click On "+elementName.get().toString());
            //	System.out.println("try to click on the element "+el.toString().split("xpath:")[1]);
            //	ExtentTestManager.getTest().//Log(//LogStatus.PASS, " Step: Click On "+el.toString().split("xpath:")[1]+" Button");


        } catch (StaleElementReferenceException e) {
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);

            Thread.sleep(8000);
            WaitForpageload();
            el.click();
            System.out.println(" was staleElement but waited and try to click on the element");

        } catch (WebDriverException e)
        //Thread.sleep(3000);
        {
            //Thread.sleep(3000);
            //JavascriptExecutor js = (JavascriptExecutor) driver;
            System.out.println("Error in Clickon " + e.getMessage());
            if (!e.toString().equals("NoSuchElementException")) {
                try {
                    if (e.getMessage().contains("is not clickable at point")) {

                        Thread.sleep(8000);
                        System.out.println("Size of the element is not perfect so waited and re-tried the click actions");
                        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);

                        Thread.sleep(200);
                        js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                        //el.click();
                        //	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                    } else if (driver.findElement(By.xpath("//div[@id='lockCreateScreen' and not(@style='display: none;')]")).isDisplayed()) {
                        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
                        Thread.sleep(200);
                        js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                        System.out.println("Mask was overlayed so trying click with the Java script");
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                        //	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                    }
                } catch (NoSuchElementException e12) {
                    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
                    Thread.sleep(200);
                    js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                    //	js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
                }
            } else {
                Assert.fail("The Elelemt with locator" + el.getTagName() + " and " + el.hashCode());
            }
        }
    }

    public void safeJavaScriptClick(WebElement element) {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element " + e.getStackTrace());
        }
    }

    @Override
    public void SafeJavaScriptClick(WebElement element) {
        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element " + e.getStackTrace());
        }
    }

    @Override
    public void switchtofram(WebElement el) {

        driver.switchTo().frame(el);

    }
    @Override
    public void ScrollIntoView(WebElement element) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    @Override
    public void ScrollWindowDownVertically(WebDriver driver2) {
        // scroll screen at the bottom
        JavascriptExecutor jse = (JavascriptExecutor) driver2;
        //jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        jse.executeScript("window.scrollTo(0, 300)");
    }

    public void Waitforswitchtofram(String Locator) throws InterruptedException {
        //WaitforElementtobeclickable("id=engagementComponent");
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Locator));


    }

    public void Waitforswitchtofram2(String Locator) throws InterruptedException {

        //Thread.sleep(5000);
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Locator));

        Thread.sleep(1500);
		/*try {
			for(int i=0;i<=10;i++) {
			while(isElementPresent(Locator))
			{
				Thread.sleep(500);
			}
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		Thread.sleep(300);*/
    }

    @Override
    public void switchtodefault() {

        driver.switchTo().defaultContent();

    }

    public String Getattribute(WebElement el, String attributename) {
        //Log.info(el.getAttribute(attributename));
        return el.getAttribute(attributename);
    }

    public void ProxyLogin(String User, String Proxylink) throws Exception {
        //openurl("CPQAdmin");
        WaitforElementtobeclickable("//a[text()='Internal Users']");
        Clickon(getwebelement("//a[text()='Internal Users']"));
        Thread.sleep(2000);
        String uri = Getattribute(getwebelement(Proxylink), "href");
        System.out.println("URL is" + uri);
        String[] URL1 = uri.split("user_id=");
        System.out.println(URL1[0]);
        System.out.println(URL1[1]);
        String URL2 = (URL1[1].split("&"))[1];
        System.out.println(URL2);
        //PropertyReader pr=new PropertyReader();
        //String FinalURL=URL1[0]+"user_id="+pr.readproperty(User)+"&"+URL2;
        //System.out.println(FinalURL);

        //Log.info("CPQ_URL");
        //openurl2(FinalURL);

    }

    @Override
    public void Moveon(WebElement el) {
        Actions action = new Actions(driver);
        action.moveToElement(el).build().perform();
    }
    @Override
    public void MoveonAndClick(String locator1, String  locator2) throws InterruptedException {
        Actions action = new Actions(driver);
        WebElement main=driver.findElement(By.xpath(locator1));
        action.moveToElement(main).perform();
        Thread.sleep(4000);
        driver.findElement(By.xpath(locator2)).click();
       // action.moveToElement(main).moveToElement(driver.findElement(By.xpath(locator2))).click().build().perform();
    }

    @Override
    public void ClearAndSendKeys(WebElement el, String value) throws InterruptedException, IOException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
            el.clear();
            el.sendKeys(value);
            System.out.println("try to click on the element");
        } catch (WebDriverException e) {
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
            System.out.println("Error in Clickon " + e.getMessage());
            if (!e.toString().equals("NoSuchElementException")) {
                try {
                    Thread.sleep(8000);
                    el.sendKeys(value);
                } catch (Exception e1) {
                    System.out.println(e1.getMessage().toString());
                }
            }
        }

    }

    public boolean isElementPresent(String locator) {
        try {
            driver.findElement(By.xpath(locator));
            //Log.info("Element Found: True");
            return true;
        } catch (NoSuchElementException e) {
            //Log.info("Element Found: False");
            return false;
        }
    }

    public void Expandthesection(WebElement Section, WebElement ClickableElement) throws Exception {

        Thread.sleep(5000);
        String classvalue = Getattribute(Section, "class");
        System.out.println(classvalue);
        if (!classvalue.contains("green")) {
            System.out.println("In IF class");
            //Clickon(ClickableElement);
            ((JavascriptExecutor)

                    driver).executeScript("arguments[0].scrollIntoView();", ClickableElement);
            safeJavaScriptClick(ClickableElement);

        } else {
            System.out.println("Already expanded");
        }
    }

    public void Clickonoutofviewport(WebElement locator) throws Exception {
        ((JavascriptExecutor)

                driver).executeScript("arguments[0].scrollIntoView();", locator);
        safeJavaScriptClick(locator);
    }

    public void Clickonoutofviewportwithstring(String locator) throws Exception {

        ((JavascriptExecutor)

                driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[text()='Show Groups']")));
        //
        ((JavascriptExecutor)

                driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(locator)));

        System.out.println("See if Scrolled");
        Thread.sleep(5000);
        System.out.println("Waiting....");
        safeJavaScriptClick(driver.findElement(By.xpath(locator)));
        System.out.println("Click is performed");
    }

    public void waitandclickForworkitemsPresent(String locator, int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 60 / 20; i++) {
            try {
                if (isElementPresent(locator)) {
                    break;
                } else {
                    //Goto Error Tab
                    // Clickon(getwebelement(xml.getlocator("//locators/Tasks/Errors")));
                    //if any Error displayed
                    //if(isElementPresent("Locator for first error"))
                    //{
                    //Assert.fail("An Error Occuured on Error Tab");
                    //break;
                    //}
                    //else
                    //{
                    //Clickon(getwebelement(xml.getlocator("//locators/Tasks/Workitems")));
                    ////Log.info("Refreshing the Pages");
                    //driver.navigate().refresh();
                    ////Log.info("Waiting For 20 Sec");
                    //Thread.sleep(20000);
                    //}
                    //Assert False and Break
                    //else navigate to WorkItems and do the page refresh and weight
                    //Log.info("Refreshing the Pages");
                    driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    Thread.sleep(20000);
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
    }

    public void waitandclickForOrderCompleted(String locator, int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 60 / 20; i++) {
            try {
                if (isElementPresent(locator)) {
                    break;
                } else {
                    //Log.info("Refreshing the Pages");
                    driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    Thread.sleep(20000);
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
    }

    public void waitandclickForOrderStarted(String locator, int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 60 / 20; i++) {
            try {
                if (isElementPresent(locator)) {
                    break;
                } else {
                    //Log.info("Refreshing the Pages");
                    driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    Thread.sleep(20000);
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
    }

    public void waitandForElementDisplayed(String locator) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("In Wait for Element Clickable method for - " + locator);
        //waitForpageload();
        if (locator.startsWith("//") || locator.startsWith("(")) {

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));

            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active" + locator);
            //Thread.sleep(2000);
        } else if (locator.startsWith("name")) {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locator.split("=")[1]))));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active" + locator);
            //Thread.sleep(2000);

        } else if (locator.startsWith("id")) {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locator.split("=")[1]))));
            //getwebelement(xml.getlocator("//locators/StandrdQuote"));
            System.out.println("Waiting for Element to be clickabal and active" + locator);
            //Thread.sleep(2000);

        }

    }

    public void waitandForElementDisplay(String locator, int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 60 / 20; i++) {
            try {
                if (isElementPresent(locator)) {
                    break;
                } else {
                    //Log.info("Refreshing the Pages");
                    //driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    Thread.sleep(20000);
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
    }

    public void waitandForElementtobenotDisplay(String locator, int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 60 / 20; i++) {
            try {
                if (isElementPresent(locator)) {
                    //Log.info("Refreshing the Pages");
                    //driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    System.out.println("Waiting......");
                    Thread.sleep(3000);
                } else {
                    break;
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
    }

    public void waitandForElementDisplay2(String locator, int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 60 / 20; i++) {
            try {
                if (isElementPresent(locator)) {
                    break;
                } else {
                    ////Log.info("Refreshing the Pages");
                    //driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
    }

    public void Select3(WebElement el, String value) throws IOException, InterruptedException {
        try {
            if (el.isEnabled() && el.isDisplayed()) {
                //Log.info("Clicking on element with using java script click");

                ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + value + "'", el);
            } else {
                //Log.info("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            //Log.info("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            //Log.info("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            //Log.info("Unable to click on element "+ e.getStackTrace());
        }
    }

    public int getwebelementscount(String locator) throws InterruptedException {
        ellist = driver.findElements(By.xpath(locator));
        return ellist.size();
    }

    public void SendKeys(WebElement el, String value) throws InterruptedException, IOException {
        //Thread.sleep(3000);
        //el.
        //System.out.println(el.getRect().getHeight()+"-"+el.getRect().getWidth()+"-"+el.getRect().x+"-"+el.getRect().x);
        //ExtentTestManager.getTest().//Log(//LogStatus.PASS,ExtentTestManager.getTest().addBase64ScreenShot(capturescreenshotforelement(el)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {

            //JavascriptExecutor js = (JavascriptExecutor) driver;
            //use executeScript() method and pass the arguments
            //Here i pass values based on css style. Yellow background color with solid red color border.
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
            el.sendKeys(value);
            System.out.println("try to click on the element");
//	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);	

        } catch (WebDriverException e)
        //Thread.sleep(3000);
        {
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
            //Thread.sleep(3000);
            //JavascriptExecutor js = (JavascriptExecutor) driver;
            System.out.println("Error in Clickon " + e.getMessage());
            if (!e.toString().equals("NoSuchElementException")) {
                try {
                    Thread.sleep(8000);

                    el.sendKeys(value);
                    //		js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
                } catch (Exception e1) {
                    System.out.println(e1.getMessage().toString());
                }
            }
        }
        //Thread.sleep(3000);
    }


    public void SendkeaboardKeys(WebElement el, Keys k) throws InterruptedException {
        //Thread.sleep(3000);

        el.sendKeys(k);
        //Thread.sleep(3000);
    }

    public String GetInputValue(WebElement el) {
        String actual = el.getAttribute("value");
        return actual;
    }

    public String Getkeyvalue(String Key) throws IOException {
        //PropertyReader pr=new PropertyReader();
        String Keyvalue = null;
        //Keyvalue=pr.readproperty(Key);
        return Keyvalue;
    }

    public void VerifyTextpresent(String text) throws IOException {
        //Log.info(text);
        Assert.assertFalse(driver.findElement(By.xpath("//*[text()='" + text + "']")).isDisplayed());
    }

//	public void VerifyText(String text) throws IOException
//		{ 
//			//Log.info(text);
//			Assert.assertTrue(driver.findElement(By.xpath("//*[text()='"+text+"']")).isDisplayed());
//		}

    public String Gettext(WebElement el) throws IOException {
        String text = el.getText().toString();
        return text;
    }

    public String GetValueofInput(WebElement el) throws IOException {
        String text = el.getAttribute("value");
        return text;
    }

    public String[] GetText2(WebElement el) throws IOException {
        String text = el.getText().toString();
        String[] text2 = text.split(" \\[");
        //Log.info("New Task Name is:"+text2);
        return text2;
    }

    public String GetText3(WebElement el, String string) throws IOException {
        String text = el.getText().toString();
        return text;
    }

    public void Select(WebElement el, String value) throws IOException, InterruptedException { //Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (!value.equals("")) {
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
            Thread.sleep(200);
            js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
            Select s1 = new Select(el);
            s1.selectByVisibleText(value);
            //	js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
        } else {
            System.out.println("Noting to select");
        }
        //Thread.sleep(3000);
    }

    public void Select2(WebElement el, String value) throws IOException, InterruptedException { //Thread.sleep(3000);
        if (!value.equals("")) {
            Select s1 = new Select(el);
            s1.selectByValue(value);
        } else {
            System.out.println("Noting to select");
        }
        //Thread.sleep(3000);
    }

    public void Clear(WebElement el) throws IOException, InterruptedException { //Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", el);
        Thread.sleep(200);
        js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
        el.clear();
        //Thread.sleep(3000);
//		js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", el);
    }

    public void WaitforCPQloader() throws IOException, InterruptedException {
        System.out.println("Data missing");


    }

    public void WaitforCPQloader2() throws IOException, InterruptedException {
        for (int i = 0; i <= 20; i++) {
            //Thread.sleep(30000);
            if (driver.findElement(By.xpath("//html")).getAttribute("class").toString().contains("loading")) {
                System.out.println("In check");
                Thread.sleep(500);
                //WaitforCPQloader2( );
            } else {
                System.out.println("Loading Finnished");

            }

        }
        Thread.sleep(2000);
//		System.out.println("In second loader");
//		
//		try {
//			//Thread.sleep(3000);
//			System.out.println(driver.findElement(By.xpath("//html")).getAttribute("class"));
//			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//html[contains(@class,'loading')]"))));
//			//System.out.println(driver.findElement(By.xpath("//html")).getAttribute("class"));
//			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//html[contains(@class,'loading')]"))));
//			System.out.println(driver.findElement(By.xpath("//html")).getAttribute("class"));
//			
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//html[contains(@class,'page-loaded')]"))));
//				}
//				catch(Exception e) {
//					//Log.info("No Loader displayed");
//					System.out.println("in catch");
//					Thread.sleep(500);
//					WaitforCPQloader2();
//				}

    }

    public void WaitforC4Cloader(String el, int timeout) throws IOException, InterruptedException {
        Thread.sleep(1500);

        try {
            while (isElementPresent(el)) {
                Thread.sleep(500);
                System.out.println("in C4C loader loop");
            }
            //;
            //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(el)));
            //wait.until(ExpectedConditions.presenceOfElementLocated(driver.findElement(By.xpath(el))));
        } catch (Exception e) {
            //Log.info("No Loader displayed");
            System.out.println("No Loader");
        }
//		for(int i=0;i<=timeout*60/20;i++){
//			try {
//	            if (isElementPresent(el)){
//	            	////Log.info("Refreshing the Pages");
//		        	//driver.navigate().refresh();
//		        	//Log.info("Waiting For 20 Sec");
//		        	Thread.sleep(20000);
//	            }
//	            else{
//	            	////Log.info("Refreshing the Pages");
//		        	//driver.navigate().refresh();
//		        	break;
//	            }
//	            }
//	        catch (Exception e) {
//	        	//Log.info(e.getMessage());
//	        }
//		}
        //Thread.sleep(8000);
    }

    @Override
    public void AcceptJavaScriptMethod() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("No Alert Present");
        }
    }

    public void CancelJavaScriptMethod() throws InterruptedException {
        Thread.sleep(1000);
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        driver.switchTo().defaultContent();
    }

    @Override
    public void WaitloadingComplete() throws InterruptedException {
        Thread.sleep(1000);
        try {
            wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='modal fade show']"), "style", "display: none;"));
            System.out.println("Waiting Loading mask");
            Thread.sleep(5000);
        } catch (StaleElementReferenceException e2) {
            Thread.sleep(5000);
        } catch (TimeoutException e) {
            Thread.sleep(5000);
        }

    }

	@Override
    public void WaitForpageload() throws Exception {



        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        /*Thread.sleep(1500);
        try {
            for (int i = 0; i <= 10; i++) {
                while (isElementPresent("//html[contains(@class,'loading')]")) {
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }*/
        Thread.sleep(5000);
      //  waitForpageloadmask();
      //  WaitforPageToBeReady();
    }

    public void waitForpageloadmask() throws InterruptedException {

        //wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        Thread.sleep(1500);
        try {
            for (int i = 0; i <= 10; i++) {
                while (isElementPresent("//div[@id='lockCreateScreen' and not(@style='display: none;')]")) {
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        Thread.sleep(300);
    }

    public void waitForpageloadExplore() throws InterruptedException {

        //wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

        Thread.sleep(1500);
        try {
            for (int i = 0; i <= 2; i++) {
                while (isElementPresent("//div[contains(text(),'Loading data')]")) {
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
        Thread.sleep(1000);
    }

    public void waitForpagenavigated(int timeout) throws InterruptedException {
        for (int i = 0; i <= timeout * 100 / 5; i++) {
            try {
                if (!driver.getTitle().toString().contains("Transaction")) {
                    ////Log.info("Refreshing the Pages");
                    //driver.navigate().refresh();
                    //Log.info("Waiting For 20 Sec");
                    System.out.println("Waiting for Navigation happen");
                    Thread.sleep(500);
                } else {
                    ////Log.info("Refreshing the Pages");
                    System.out.println("Nevigated to CPQ");
                    //driver.navigate().refresh();
                    break;
                }
            } catch (Exception e) {
                //Log.info(e.getMessage());
            }
        }
        //Thread.sleep(3000);
        //wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(1000);
    }

    public void Dragedrop(WebElement source, WebElement Destination) {
        Actions action = new Actions(driver);
        //use dragAndDrop() method. It accepts two parametes source and target.
        action.dragAndDrop(source, Destination).build().perform();
    }

    public void EnterText(String s) {
        Actions keyAction = new Actions(driver);
        keyAction.sendKeys(s).perform();
    }

    public void EnterText2(Keys k) {
        Actions keyAction = new Actions(driver);
        keyAction.sendKeys(k).perform();
    }

    public void savePage() {
        Actions keyAction = new Actions(driver);
        keyAction.keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).perform();
    }

    public void KeydownKey(Keys key) {
        Actions keyAction = new Actions(driver);
        keyAction.keyDown(key).perform();
    }

    public void KeyupKey(Keys key) {
        Actions keyAction = new Actions(driver);
        keyAction.keyUp(key).perform();
    }

    public void uploadafile(String locator, String FileName) {
        String str = System.getProperty("user.dir") + "\\src\\Data\\" + FileName;
        String[] finalval = locator.split("=");
        WebElement el;
        if (locator.startsWith("id")) {
            el = driver.findElement(By.id(finalval[1]));
        } else if (locator.startsWith("name")) {
            el = driver.findElement(By.name(finalval[1]));
        } else {
            el = driver.findElement(By.xpath(finalval[1]));
        }
        el.sendKeys(str);
        // + "\\Lib\\chromedriver.exe"
        //Toolkit toolkit = Toolkit.getDefaultToolkit();
        // Clipboard clipboard = toolkit.getSystemClipboard();
        // StringSelection strSel = new StringSelection(str);
        // clipboard.setContents(strSel, null);
        // Actions keyAction = new Actions(driver);
        // keyAction.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        // keyAction.sendKeys(Keys.ENTER);
    }

    public String capturescreenshotforelement(WebElement ele) throws IOException {
        String screenshot2;
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // Get the location of element on the page
        org.openqa.selenium.Point point = ele.getLocation();

        // Get width and height of the element
        int eleWidth = ele.getSize().getWidth();
        int eleHeight = ele.getSize().getHeight();

        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX() - 20, point.getY() - 20,
                eleWidth + 20, eleHeight + 20);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(eleScreenshot, "png", bos);
        byte[] imageBytes = bos.toByteArray();
        screenshot2 = "data:image/png;base64," + Base64.getMimeEncoder().encodeToString(imageBytes);
        bos.close();
        return screenshot2;
    }

    public static String Capturefullscreenshot(WebDriver driver) throws IOException {
        String screenshot2;
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);

        // To save the screenshot in desired location
//		ImageIO.write(screenshot.getImage(), "PNG",
//				new File(System.getProperty("user.dir") + "\\screenshots\\fullpagescrn.png"));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ImageIO.write(screenshot.getImage(), "PNG", bos);

        byte[] imageBytes = bos.toByteArray();
        screenshot2 = "data:image/png;base64," + Base64.getMimeEncoder().encodeToString(imageBytes);

        bos.close();

        return screenshot2;
    }

    @Override
    public void CloseAllWindows() {
        String parentWinHandle = driver.getWindowHandle();
        Set<String> totalopenwindow = driver.getWindowHandles();
        if (totalopenwindow.size() > 1) {
            for (String handle : totalopenwindow) {
                if (!handle.equals(parentWinHandle)) {
                    driver.switchTo().window(handle);

                }
            }
            driver.close();
            driver.switchTo().window(parentWinHandle);
        } else {
            System.out.println("No popup displayed");
        }
    }



    @Override
    public void WaitforControlClickable(final String locator) throws Exception
    {
        WaitForpageload();
        if (locator.startsWith("//") || locator.startsWith("("))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            System.out.println("Code for Loading");
            Thread.sleep(2000);
        } else if (locator.startsWith("name"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.name(locator.split("=")[1])));
            System.out.println("Code for Loading");
            Thread.sleep(2000);

        } else if (locator.startsWith("id"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.id(locator.split("=")[1])));
            System.out.println("Code for Loading");
            Thread.sleep(2000);

        }
    }

    @Override
    public void SwitchToLastTab() {
        String parentWinHandle = driver.getWindowHandle();
        Set<String> totalopenwindow = driver.getWindowHandles();
        for (String handle : totalopenwindow) {
            if (!handle.equals(parentWinHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    @Override
    public int TabSize() {
        String parentWinHandle = driver.getWindowHandle();
        Set<String> totalopenwindow = driver.getWindowHandles();
        System.out.println(totalopenwindow.size());
        return totalopenwindow.size();
    }

    @Override
    public void CloseAllTabs() {
        String parentWinHandle = driver.getWindowHandle();
        Set<String> totalopenwindow = driver.getWindowHandles();
        for (String handle : totalopenwindow) {
            if (!handle.equals(parentWinHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parentWinHandle);
    }

    @Override
    public void GetIframeLoaded(String framlocator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(framlocator.split("=")[1])));
        //driver.switchTo().frame(driver.findElement(By.id(framlocator.split("=")[1])));
        System.out.println("Switched to Iframe");
    }

    @Override
    public void WaitforPageToBeReady() throws Exception {
        while (!((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")) {
            System.out.println("dom state is" + ((JavascriptExecutor) driver).executeScript("return document.readyState"));
            Thread.sleep(500);
            //wait.until(ExpectedConditions.jsReturnsValue(_PAGE_WHEN_READY_SCRIPT));
        }
    }



    @Override
    public String GetTitle() {
        // TODO Auto-generated method stub
        return driver.getTitle();
    }

    @Override
    public void OpenURL(String environment) {
        //String URL=null;
        //PropertyReader pr=new PropertyReader();
        //Log.info(environment+"_URL");
        //URL=pr.readproperty(environment+"_URL");
        //System.out.println(URL);
        driver.get(environment);

    }

    @Override
    public void OpenURLV2(String environment) throws Exception {
        String URL = null;
        //PropertyReader pr=new PropertyReader();
        ////Log.info(environment+"_URL");
        //URL=pr.readproperty(environment+"_URL");
        System.out.println(URL);
        driver.get(URL);
        Thread.sleep(5000);
    }

    @Override
    public void GetURL(String URL) {
        // TODO Auto-generated method stub
        driver.get(URL);
    }

    @Override
    public void VerifyTitle(String Expectedtitle) {
        // TODO Auto-generated method stub
        Assert.assertTrue(driver.getTitle().contains(Expectedtitle));
    }

    @Override
    public void VerifyText(WebElement el, String Expectedtext) {
        Assert.assertTrue(el.getText().contains(Expectedtext));
    }

    @Override
    public String GetText(WebElement el)
    {
      return el.getText();
    }

    @Override
    public void PageRefresh() throws Exception {
        // TODO Auto-generated method stub
        driver.navigate().refresh();
        Thread.sleep(5000);
    }

    @Override
    public String CurrentURL() {
        return driver.getCurrentUrl();
    }

    @Override
    public String CaptureScreenShotForElement(WebElement ele) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String CaptureFullScreenShot(WebDriver driver) {
        // TODO Auto-generated method stub
        return null;
    }

    /*	End of the Web Driver Helper area which contains all the base methods related to the Web Driver

     *------------------------------------------------------------------------------------------
     *
     */


    /*	Start of the API Helper area which contains all the base methods related to the API

     *------------------------------------------------------------------------------------------
     *
     */
    @Override
    public void authentication(String type, String username, String password) {
        //String userName = null,password = null,accessToken = null,consumerKey = null,consumerSecret = null,tokenSecret = null;
        //FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/test/java/Config/config.properties");
        //propinhelper.load(ip);
        switch (type) {

            case "Basic": {
                Request.auth().basic(username, password);

                break;
            }
            case "preemptive": {
                Request.auth().preemptive().basic(username, password);

                break;
            }
            case "Form": {
                Request.auth().form(username, password);
                break;
            }
            case "ComplexForm": {
                Request.auth().form(username, password, new FormAuthConfig("/perform_login", "username", "password"));
                break;
            }
            case "Oauth2": {
                Request.auth().oauth2(username);
                break;
            }
//				case "Oauth1":
//				{
//					Request.accept(ContentType.JSON)
//					  .auth()
//					  .oauth(consumerKey, consumerSecret, accessToken, tokenSecret);
//					break;
//				}
        }

    }

    @Override
    public void updateRequestHeader(String HeaderKey, String Value) {
        // TODO Auto-generated method stub

    }

    @Override
    public String readRequestTemplate(String path) throws IOException {
        // TODO Auto-generated method stub
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    @Override
    public void updateAttributeInRequestBody(String Filename, String Key, String Value) throws IOException, SAXException, ParserConfigurationException, Exception {
        if (Filename.toLowerCase().endsWith(".json")) {
            String payload = readRequestTemplate(System.getProperty("user.dir") + "\\" + Root + Filename);
            //templatefile=Inputgenerator(templatefile,"quiz.sport.q1.answer", "Testing");
            if (Payload.equals("")) {
                Payload = payload;
            }

            Payload = Payload.replace("\"" + Key + "\" : \"" + Key + "\"", "\"" + Key + "\" : \"" + Value + "\"");


            //Request.contentType(Contenttype);
            //Request.body(payload);
            //Payload=payload;
            System.out.println("Updated Paylod" + Payload);
            ////ExtentTestManager.getTest().log(LogStatus.INFO, "Updated Paylod"+Payload);
        } else if (Filename.toLowerCase().endsWith(".xml")) {
            SAXReader xmlreader = new SAXReader();
            Document doc = xmlreader.read(System.getProperty("user.dir") + "\\" + Root + Filename);
            doc.selectSingleNode(Key).setText(Value);
            Payload = doc.asXML();
            System.out.println("Updated Paylod" + Payload);
        }

    }

    @Override
    public void generatePayLoad() {
        Request.contentType(Contenttype);
        Request.body(Payload);
        Payload = "";


    }

    @Override
    public void submitRequest(Method method, String URI) {
        Resultrespoence = null;
        Resultrespoence = Request.request(method, URI);

        System.out.println("Response Body After Request: " + Resultrespoence.getBody().asString());
        ////ExtentTestManager.getTest().log(LogStatus.INFO, "Response Body After Request: "+ Resultrespoence.getBody().asString());
        Request.body("");
        Reporter.addStepLog(Resultrespoence.getBody().asString());
    }

    @Override
    public void assertStringInResponceBody(String ExpectedData) {
        String responseBody = Resultrespoence.getBody().asString();
        System.out.println("Response Body is: " + responseBody);
        //validate city name or validate the key or value
        //ExtentTestManager.getTest().log(LogStatus.PASS, "Response Body is: "+ responseBody);

        Assert.assertEquals(responseBody.contains(ExpectedData), true);
    }

    @Override
    public void assertStatusCode(int ExpectedStatusCode) {
        int statusCode = Resultrespoence.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        //ExtentTestManager.getTest().log(LogStatus.INFO, "Status code is: "+ statusCode);

        Assert.assertEquals(statusCode, ExpectedStatusCode);
        //validate city name or validate the key or value
        //return statusCode;
    }

    @Override
    public void assertStatusLine(String ExpectedStatusLine) {
        String statusCode = Resultrespoence.getStatusLine();
        System.out.println("Status Code is: " + statusCode);
        //ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: "+ statusCode);

        Assert.assertEquals(statusCode, statusCode);
        //validate city name or validate the key or value
        //return statusCode;
    }

    @Override
    public void assertHeaderattribute(String HeaderName, String ExpectedheaderValue) {
        Headers headers = Resultrespoence.getHeaders();
        System.out.println(headers);

        String headervalue = Resultrespoence.getHeader(HeaderName);
        System.out.println("The value of content-type header is: " + headervalue);
        //ExtentTestManager.getTest().log(LogStatus.INFO, "The value of content-type header is: "+ headervalue);
        Assert.assertEquals(headervalue, headervalue);
    }

    @Override
    public void assertResponceBodyAttribute(String Node, String Expectedvalue) throws SAXException, IOException, ParserConfigurationException, DocumentException {
        System.out.println("Respoence body is " + Resultrespoence.getBody().asString());
        //ExtentTestManager.getTest().log(LogStatus.INFO, "Respoence body is "+Resultrespoence.getBody().asString());
        if (Resultrespoence.getHeader("content-type").contains("json")) {
            JsonPath jsonPathValue = Resultrespoence.jsonPath();
            System.out.println("JsoneBody as String String" + jsonPathValue);
            //ExtentTestManager.getTest().log(LogStatus.INFO, "JsoneBody as String String"+jsonPathValue);

            String Nodevalue = jsonPathValue.get(Node);
            System.out.println("The value of " + Node + " is: " + Nodevalue);
            //ExtentTestManager.getTest().log(LogStatus.INFO, "The value of "+Node+" is: "+ Nodevalue);

            System.out.println("Expected Value is : " + Expectedvalue);
            //ExtentTestManager.getTest().log(LogStatus.INFO, "Expected Value is : "+ Expectedvalue);

            System.out.println("Condition Value is " + Nodevalue.contains(Expectedvalue));
            //Assert.assertTrue();
            //ExtentTestManager.getTest().log(LogStatus.INFO, "Condition Value is "+ Nodevalue.contains(Expectedvalue));

            Assert.assertTrue(Nodevalue.contains(Expectedvalue), "Expected the Value of <b>" + Node + "</b> Attribute value contains <b>" + Expectedvalue + "</b> but  Actual returned was <b>" + Nodevalue + "</b>");
        } else if (Resultrespoence.getHeader("content-type").contains("xml")) {

            SAXReader xmlreader = new SAXReader();
            Document doc = xmlreader.read(Resultrespoence.asInputStream());
            Assert.assertTrue(doc.selectSingleNode(Node).getText().equals(Expectedvalue), "Expected the Value of <b>" + Node + "</b> Attribute value contains <b>" + Expectedvalue + "</b> but  Actual returned was <b>" + doc.selectSingleNode(Node).getText() + "</b>");
        }
    }

    @Override
    public String SaveAttributevalue(String Node) {
        System.out.println("Data in Reponce is" + Resultrespoence.getBody().asString());
        JsonPath jsonPathValue = Resultrespoence.jsonPath();
        String Nodevalue = jsonPathValue.get(Node).toString();
        System.out.println("The value of " + Node + " is: " + Nodevalue);
        //ExtentTestManager.getTest().log(LogStatus.INFO, "The value of "+Node+" is: "+ Nodevalue);

        Assert.assertEquals(Nodevalue, Nodevalue);
        return Nodevalue;
    }

    @Override
    public JSONObject Inputgenerator(JSONObject templatefile, String Node, String Value) {
        JSONObject jsonObjectnodelist = null;
        JSONObject jsonObjectfinal = templatefile;
        String[] nodelist = Node.split(".");
        if (nodelist.length == 1) {
            // Update the Node
            System.out.println(jsonObjectnodelist.get(nodelist[nodelist.length - 1]));
        } else {
            for (int i = 0; i < nodelist.length - 2; i++) {
                jsonObjectnodelist = (JSONObject) templatefile.get(nodelist[i]);
                System.out.println(jsonObjectnodelist.toString());
            }
            System.out.println(jsonObjectnodelist.get(nodelist[nodelist.length - 1]));
        }

        return templatefile;
    }


    /*	End of the API Helper area which contains all the base methods related to the API

     *------------------------------------------------------------------------------------------
     *
     */
    public static String GetCurrentDate() {
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        //get current date time with Date()
        Date currentDate = new Date();
        // Now format the date
        String date1 = dateFormat.format(currentDate);
        // Print the Date
        System.out.println(date1);
        return date1;
    }

    public static String GetFutureDate() {


        // For selecting move-out date
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY ");
        //get current date time with Date()
        Date currentDate = new Date();
        System.out.println("Today's date:::" + currentDate);
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.DATE, 4);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        // Now format the date
        String date1 = dateFormat.format(currentDatePlusOne);
        // Print the Date
        System.out.println(date1);
        return date1;
    }

}