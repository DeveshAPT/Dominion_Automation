package testScripts;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.DataReader;
import utils.driver;

public class Employee extends driver{
	
	
	@Test(dataProviderClass=DataReader.class,dataProvider="AllData",groups = { "api" })
	public void EmployeeAPITest(Object[][] Data) throws Exception{
		
		EmployeeService.get().CreateEmploye(Data);
		EmployeeService.get().GetEmployee();
		EmployeeService.get().VerifyEmployeeDetail("employee_name", Data[0][0].toString());
		EmployeeService.get().VerifyEmployeeDetail("employee_salary", Data[0][1].toString());
		EmployeeService.get().VerifyEmployeeDetail("employee_age", Data[0][2].toString());
		EmployeeService.get().UpdateEmployee(Data);
		EmployeeService.get().GetEmployee();
		EmployeeService.get().VerifyEmployeeDetail("employee_name", Data[0][3].toString());
		EmployeeService.get().VerifyEmployeeDetail("employee_salary", Data[0][4].toString());
		EmployeeService.get().VerifyEmployeeDetail("employee_age", Data[0][5].toString());
		EmployeeService.get().DeleteEmployee();
		EmployeeService.get().VerifyDeletedEmployee();
	}
	
	
	
	
	
	
	
	
	

}
