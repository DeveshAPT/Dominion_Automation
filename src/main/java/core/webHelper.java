package core;
import java.io.IOException;
/** WebHelper Interface.
 * @author Ashwani Kumar Singh
 * @author www.qahub.com
 * @version 1.0
 * @since 1.0
*/
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
/** <p>Three category of the Methods are included into this Interface
 * <ol><b>Browser Window</b>
 * <li>public void CloseAllWindows()</li>
 * <li>public void SwitchToNextTab()</li>
 * <li>public void GetIframeLoaded(String framlocator, String l1)</li>
 * <li>public void WaitforPageToBeReady()</li>
 * <li>public String GetTitle()</li>
 * <li>public void OpenURL(String environment)</li>
 * <li>public void OpenURLV2(String environment)</li>
 * <li>public void GetURL(String URL)</li>
 * <li>public void PageRefresh()</li>
 * <li>public void CurrentURL()</li>
 * <li>public String CaptureScreenShotForElement(WebElement ele)</li>
 * <li>public static String CaptureFullScreenShot(WebDriver driver)</li></ol>
 * <ol><b>Elements</b>
 * <li>public void WaitForElementToBeClickable(String locator)</li>
 * <li>public WebElement GetWebelement(String locator)</li>
 * <li>public WebElement GetWebelementV3(String locator)</li>
 * <li>public WebElement GetWebelementV2(String locator)</li>
 * <li>public String GetAttribute(WebElement el,String attributename) </li>
 * <li>public int GetWebElementsCount(String locator)</li>
 * </ol>
 * <ol><b>Waits</b>
 * <li>public void WaitAndClickForWorkItemsPresent(String locator, int timeout)</li>
 * <li>public void WaitAndClickForOrderCompleted(String locator, int timeout)</li>
 * <li>public void WaitAndClickForOrderStarted(String locator, int timeout)</li>
 * <li>public void WaitAndForElementDisplayed(String locator)</li>
 * <li>public void WaitAndForElementDisplay(String locator, int timeout)</li>
 * <li>public void WaitTillElementDisapear(String locator, int timeout)</li>
 * <li>public void WaitandForElementDisplayV2(String locator, int timeout)</li>
 * <li>public void WaitForpageload()</li>
 * <li>public void WaitForpageloadmask()</li>
 * <li>public void WaitForpageloadExplore()</li>
 * <li>public void WaitForpagenavigated(int timeout)</li>
 * </ol>
 * <ol><b>Actions</b>
 * <li></li></ol>
 * <ol><b>Application Specific</b>
 * <li></li></ol>
 * </p>
*/


public interface webHelper {
	
	/** Close all the Child Windows if any open excepts the parent Window/Default window.
	 * Nothing to be returned here.
	*/
	public void CloseAllWindows();
	/** Switch to the last open browser tab. If no addition tab opened, Driver will be remain on the Default Tab
	 * Nothing to be returned here.
	*/
	public void SwitchToLastTab();
	/** Wait till IFrame/Frame on the page get loaded
	 * Nothing to be returned here.
	 * @param framelocator Web element locator for the iframe/frame. For example: CSS selector/XPATH etc.
	*/
	public void GetIframeLoaded(String framelocator);
	/** Waiting for the Page to be ready. This method is looking of the Ready State of the Current Page using Java script commands.
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
	public void WaitforPageToBeReady() throws Exception;
	/** Get the Title of the current opened page.
	 * @return Page title as String.
	*/
	public String GetTitle();
	/** Open the URL using get method of WebDriver. The URL read from Configuration file as Key value pair.
	 * @param environment Name of the Environment mentioned into the Configuration file.
	 * Nothing to be returned here.
	*/
	public void OpenURL(String environment);
	/** Open the URL using get method of WebDriver. This is the Updated version OpenURL which includes timeout for the page to be loaded
	 * @param environment Name of the Environment mentioned into the Configuration file.
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
	public void OpenURLV2(String environment) throws Exception;
	/** Open the URL using get method of WebDriver. Here the URL can be passed as string at the time of calling this function
	 * @param URL Complete Web address(URL)
	 * Nothing to be returned here.
	*/
	public void GetURL(String URL);
	/** Refreshes/Reload the current page
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
	public void PageRefresh() throws Exception;
	/**This method returns the Current Page URL
	 * @return the URL of the Current page as String
	*/
	public String CurrentURL();
	/** Capture the Screenshot of Web Element and Returned as Base64 String.
	 * @param ele Web Element which Screenshot needs to be captured
	 * @return the URL of the Current page as String
	*/
	public String CaptureScreenShotForElement(WebElement ele);
	/** Capture the Screenshot ofFull screen even with Scroll on the page and Returned as Base64 String.
	 * @param driver WebDriver instance active.
	 * @return the URL of the Current page as String
	*/
	public String CaptureFullScreenShot(WebDriver driver);
	/** Click on an Web element. 
	 * @param ele Web element instance need to be clicked on
	 * Nothing to be returned here.
	 * @throws Exception 
	*/
	public void Clickon(WebElement ele) throws Exception;
	
	/** Enter the value in an input field. 
	 * @param el Web element instance need to be be enter
	 * @param value is the value which needs to be entered i the input field
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
	public void SendKeys(WebElement el,String value) throws InterruptedException, IOException;
	/** Verify the page title
	 * @param Expectedtitle expected title should be of the current web page
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
	
	/** Verify the visual text for the element

	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
	public void VerifyTitle(String Expectedtitle);
	/** Verify the visual text for the element
	 * @param el Webelement under test
	 * @param Expectedtext expected text should displayed
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
	public void VerifyText(WebElement el,String Expectedtext);
	/** Get the Web elemment based on the locator
	 * @param locator Locotor of the element
	 * @return Webelement instance
	 * Nothing to be returned here.
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	*/
	public WebElement getwebelement(String locator) throws InterruptedException;
	public List<WebElement> getwebelements(String locator) throws InterruptedException;
	boolean IsPresent(String locator);
	boolean IsNotPresent(String locator);
	public void ClearAndSendKeys(WebElement el,String value) throws InterruptedException, IOException;
	public void SafeJavaScriptClick(WebElement element) throws InterruptedException;
	public void Moveon(WebElement el);
	public void switchtofram(WebElement el);
	public void switchtodefault();
	public void ScrollWindowDownVertically(WebDriver driver) throws InterruptedException;
	
	public void AcceptJavaScriptMethod()throws InterruptedException;
	public int TabSize();
	public void CloseAllTabs();
	public void WaitForpageload() throws Exception;
	public void WaitforControlClickable(String loc) throws Exception;
	public void WaitloadingComplete() throws InterruptedException;
	public void MoveonAndClick(String  loc1, String loc2) throws InterruptedException;
	public void JavaScriptMouseHover(WebElement element);
	public void ScrollIntoView(WebElement element) throws InterruptedException;
	public String GetText(WebElement el);

}
