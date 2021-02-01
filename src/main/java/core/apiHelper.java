package core;

import io.restassured.http.Method;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.xml.sax.SAXException;

public interface apiHelper {
	
	public final static int RESPONSE_CODE_200 = 200;
	public final static int RESPONSE_CODE_201 = 201;
	public final static int RESPONSE_CODE_400 = 400;
	public final static int RESPONSE_CODE_401 = 401;
	/** Method to Authenticate the Requests.
	 * @param type - This the type of Authentication used by Service. for Example <b>"Basic"</b>,<b>"preemptive"</b> etc.
	 * @param username - This user name.
	 * @param password - This password or any secret key ot Token key which required to authenticate.
	*/
	public void authentication(String type, String username, String password);
	/** Method to update the Request Header Attributes.
	 * @param HeaderKey - This is the name of the header key needs to be updated.
	 * @param Value - This is the new value which needs to be updated for the Header key.
	*/
	public void updateRequestHeader(String HeaderKey, String Value);
	/** This method is to read the input requests template which latter used to generate the pay load. 
	 * @param path - Path of the Input template file
	 * @throws IOException 
	*/
	public String readRequestTemplate(String path) throws IOException;
	/** This method is update the attribute of the a key into the Request template.
	 * @param Filename - Path of the Input template file
	 * @param Key - Name of the Key/NODE in case of JSON and XML.<b> "Customer.Name. FirstName"</b>
	 * @param Value - Updated value of the Attribute.
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * @throws Exception 
	*/
	public void updateAttributeInRequestBody(String Filename, String Key, String Value) throws IOException, SAXException, ParserConfigurationException, Exception;
	/** Generate the Final Pay load with the updated input data.
	 * Nothing to Return
	*/
	public  void generatePayLoad();
	/** Submit the Request to Server with Specified Resource and Method.
	 * @param method - Name of the Methode to be used. for Example <b> GET</B>, <b> POST</B> etc.
	 * @param URI - Service URI. For Example - <b>"/Create"</b>
	 * Nothing to Return
	*/
	public void submitRequest(Method method, String URI);
	/** Assert an string to be contains on the response Body
	 * @param ExpectedData - Expected String to contains in response body.
	 * Nothing to Return
	*/
	public void assertStringInResponceBody(String ExpectedData);
	/** Assert Status code of the Response
	 * @param ExpectedStatusCode - Expected Status code return by Service call.
	 * Nothing to Return
	*/
	public void assertStatusCode(int ExpectedStatusCode);
	/** Assert Status line of the Response
	 * @param ExpectedStatusLine - Expected Status line return by Service call.
	 * Nothing to Return
	*/
	public void assertStatusLine(String ExpectedStatusLine);
	/** Assert specific header key value in the response.
	 * @param HeaderName - Name of the HeaderKey to be Verified.
	 * @param ExpectedheaderValue - Expected value for the HeaderKey return by Service call.
	 * Nothing to Return
	*/
	public void assertHeaderattribute(String HeaderName, String ExpectedheaderValue);
	/** Assert specific Body Key value in the response.
	 * @param Node - Name of the Body Key to be Verified.
	 * @param Expectedvalue - Expected value for the Body Key return by Service call.
	 * Nothing to Return
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws DocumentException 
	*/
	public void assertResponceBodyAttribute(String Node, String Expectedvalue) throws SAXException, IOException, ParserConfigurationException, DocumentException;
	/** Store specific Body Key returned in response.
	 * @param Node - Name of the Body Key to be Verified.
	 * @return a String value which conatns the Value of Node in s
	*/
	public String SaveAttributevalue(String Node);
	/** JSON input generator
	 * @param templatefile - JSON input file Template file path.
	 * @param  Node - Name of the node needs to be updated into Input Body
	 * @param  Value - New Value of the Node.
	*/
	public JSONObject Inputgenerator(JSONObject templatefile,String Node, String Value);
	}
