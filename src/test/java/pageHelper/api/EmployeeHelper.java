package pageHelper.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import core.apiHelper;
import core.baseDriverHelper;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageHelper.bddDriver;
import com.cucumber.listener.Reporter;


public class EmployeeHelper{
	String URI;
	
	String EmployeeID;
	public apiHelper apiDriver;
	private bddDriver DriverInstance;
	
	public EmployeeHelper(RequestSpecification dr,Response respoence)
	{
		apiDriver=new baseDriverHelper(dr,respoence);	
	}

	public EmployeeHelper(bddDriver contextSteps) throws Exception {
		this.DriverInstance = contextSteps;
		
		System.out.println(this.DriverInstance);
		apiDriver=new baseDriverHelper(DriverInstance.getApiDriver(),DriverInstance.API_RESPONCE_THREAD_LOCAL.get());
	}
	
	
	
	
	public void CreateEmploye(Object[][] InputData) throws Exception {
		URI="create";
		
		System.out.println("Name Value is"+InputData[0][0].toString());
		System.out.println("Salary Value is"+InputData[0][1].toString());
		System.out.println("Age Value is"+InputData[0][2].toString());
		Date dt=new Date();
		apiDriver.updateAttributeInRequestBody("Create.json","name",InputData[0][0].toString()+dt.toString());
		apiDriver.updateAttributeInRequestBody("Create.json","salary",InputData[0][1].toString());
		apiDriver.updateAttributeInRequestBody("Create.json","age",InputData[0][2].toString());
		apiDriver.generatePayLoad();
		apiDriver.submitRequest(Method.POST,URI);
		apiDriver.assertStatusCode(apiDriver.RESPONSE_CODE_200);
		apiDriver.assertStringInResponceBody(InputData[0][0].toString());
		EmployeeID=apiDriver.SaveAttributevalue("id");
		System.out.println("New Employee ID is:" +EmployeeID);
		
	}
	
	@Given("^I have created an employee$") 	
	public void CreateEmploye(DataTable data) throws Exception {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		URI="create";
		
		System.out.println("Name Value is"+list.get(0).get("Name"));
		System.out.println("Salary Value is"+list.get(0).get("Salary"));
		System.out.println("Age Value is"+list.get(0).get("Age"));
		Date dt=new Date();
		apiDriver.updateAttributeInRequestBody("Create.json","name",list.get(0).get("Name")+dt.toString());
		apiDriver.updateAttributeInRequestBody("Create.json","salary",list.get(0).get("Salary"));
		apiDriver.updateAttributeInRequestBody("Create.json","age",list.get(0).get("Age"));
		apiDriver.generatePayLoad();
		apiDriver.submitRequest(Method.POST,URI);
		apiDriver.assertStatusCode(apiDriver.RESPONSE_CODE_200);
		apiDriver.assertStringInResponceBody(list.get(0).get("Name"));
		EmployeeID=apiDriver.SaveAttributevalue("data.id");
		System.out.println("New Employee ID is:" +EmployeeID);
	}
	
	@Then("^Get the Employee Details$") 
	public void GetEmployee() throws IOException {
		URI="employee/"+EmployeeID;
		System.out.println(URI);
		apiDriver.submitRequest(Method.GET,URI);
		apiDriver.assertStatusCode(apiDriver.RESPONSE_CODE_200);
		apiDriver.assertStringInResponceBody("data");
	}
	
	public void UpdateEmployee(Object[][] InputData) throws Exception {
		URI="update/"+EmployeeID;
		System.out.println(URI);
		Date dt=new Date();
		String Updatedname=InputData[0][3].toString()+dt.toString();
		apiDriver.updateAttributeInRequestBody("Update.json","data.name",Updatedname);
		apiDriver.updateAttributeInRequestBody("Update.json","salary",InputData[0][4].toString());
		apiDriver.updateAttributeInRequestBody("Update.json","age",InputData[0][5].toString());
		apiDriver.generatePayLoad();
		apiDriver.submitRequest(Method.PUT,URI);
		apiDriver.assertStatusCode(apiDriver.RESPONSE_CODE_200);
		
		
	}
	
	@Then("^I have Update the Employee details$") 
	public void UpdateEmployee(DataTable data) throws Exception {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		URI="update/"+EmployeeID;
		System.out.println(URI);
		Date dt=new Date();
		String Updatedname=list.get(0).get("Updated_Name")+dt.toString();
		apiDriver.updateAttributeInRequestBody("Update.json","name",Updatedname);
		apiDriver.updateAttributeInRequestBody("Update.json","salary",list.get(0).get("Updated_Salary"));
		apiDriver.updateAttributeInRequestBody("Update.json","age",list.get(0).get("Updated_Age"));
		apiDriver.generatePayLoad();
		apiDriver.submitRequest(Method.PUT,URI);
		apiDriver.assertStatusCode(apiDriver.RESPONSE_CODE_200);
		
		
	}
	
	@Then("^Verify the Create Employee Data$")
	public void VerifyEmployeeDetailCreated(DataTable data) throws IOException, SAXException, ParserConfigurationException, DocumentException {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		apiDriver.assertResponceBodyAttribute("employee_name", list.get(0).get("Name"));
		apiDriver.assertResponceBodyAttribute("employee_name", list.get(0).get("Salary"));
		apiDriver.assertResponceBodyAttribute("employee_name", list.get(0).get("Age"));
	}
	
	@Then("^Verify the updated Employee Data$")
	public void VerifyEmployeeDetailUpdate(DataTable data) throws IOException, SAXException, ParserConfigurationException, DocumentException {
		List<Map<String, String>> list = data.asMaps(String.class, String.class);
		apiDriver.assertResponceBodyAttribute("employee_name", list.get(0).get("Updated_Name"));
		apiDriver.assertResponceBodyAttribute("employee_name", list.get(0).get("Updated_Salary"));
		apiDriver.assertResponceBodyAttribute("employee_name", list.get(0).get("Updated_Age"));
	}
	
	public void VerifyEmployeeDetail(String Key,String Value) throws IOException, SAXException, ParserConfigurationException, DocumentException {
		
		apiDriver.assertResponceBodyAttribute(Key, Value);	
	}
	
	@Then("^Verify Employee has been deleted$")
	public void VerifyDeletedEmployee() throws IOException {
		
	apiDriver.assertStringInResponceBody("successfully! deleted Records");
		
	}
	
	@Then("^I deleted the Employee$")
	public void DeleteEmployee() throws IOException {
		URI="delete/"+EmployeeID;
		apiDriver.submitRequest(Method.DELETE,URI);
		apiDriver.assertStringInResponceBody("successfully! deleted Records");
	}
	
	}

