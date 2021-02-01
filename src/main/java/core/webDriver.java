package core;

import org.openqa.selenium.WebDriver;
import org.zaproxy.clientapi.core.ClientApi;

public interface webDriver 
{
	/** Initialized the URL on specified Browser and the Also run the Selenium Grid and Node if Grid Mode is set as true
	 * Nothing to be returned here.
	 * @param browser - Name of the browser <b> Crome,FireFox,IE, Edge etc.</b>
	 * @param BaseURL - Url of the application to be tested <b> https://360logica.com.</b>
	 * @param Grid - Boolian option to enable the Grid Mod <b> True </b>OR <b>False</b>. by Default value is False
	 * @throws Exception 
	*/
	  public WebDriver webinit(String browser, String BaseURL, Boolean Grid,Boolean proxy) throws Exception;
	  public ClientApi ReturnZapScanner();
	}
