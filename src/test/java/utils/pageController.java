package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageHelper.web.DominionLoginHelper;
import pageHelper.api.EmployeeHelper;
import pageHelper.api.SPQR;



public class pageController {

		public static final ThreadLocal<SPQR> SP= new InheritableThreadLocal<>();
		public static final ThreadLocal<EmployeeHelper> EmployeeService= new InheritableThreadLocal<>();
		public static final ThreadLocal<DominionLoginHelper> domLogin= new InheritableThreadLocal<>();
		
		public void initPage(WebDriver driver) throws IOException
		{
			DominionLoginHelper DM=new DominionLoginHelper(driver);
			domLogin.set(DM);
		}
		
		public void initPage(RequestSpecification dr,Response respoence)
		{
			EmployeeHelper EH= new EmployeeHelper(dr,respoence);
			EmployeeService.set(EH);
			
			SPQR SP1= new SPQR(dr,respoence);
			SP.set(SP1);
		}
		
}


