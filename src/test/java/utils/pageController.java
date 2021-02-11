package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageHelper.web.*;
import pageHelper.api.EmployeeHelper;
import pageHelper.api.SPQR;


public class pageController {

		public static final ThreadLocal<SPQR> SP= new InheritableThreadLocal<>();
		public static final ThreadLocal<EmployeeHelper> EmployeeService= new InheritableThreadLocal<>();
		public static final ThreadLocal<DominionLoginHelper> domLogin= new InheritableThreadLocal<>();
		public static final ThreadLocal<DealerOverViewHelper> dealer= new InheritableThreadLocal<>();
		public static final ThreadLocal<InventoryHelper> invent= new InheritableThreadLocal<>();
		public static final ThreadLocal<UserHelper> user= new InheritableThreadLocal<>();
		public static final ThreadLocal<ReportHelper> report= new InheritableThreadLocal<>();

		
		public void initPage(WebDriver driver) throws IOException
		{
			DominionLoginHelper DM=new DominionLoginHelper(driver);
			domLogin.set(DM);

			DealerOverViewHelper DL=new DealerOverViewHelper(driver);
			dealer.set(DL);

			InventoryHelper In=new InventoryHelper(driver);
			invent.set(In);

			UserHelper Us=new UserHelper(driver);
			user.set(Us);

			ReportHelper Rp=new ReportHelper(driver);
			report.set(Rp);
		}
		
		public void initPage(RequestSpecification dr,Response respoence)
		{
			EmployeeHelper EH= new EmployeeHelper(dr,respoence);
			EmployeeService.set(EH);
			
			SPQR SP1= new SPQR(dr,respoence);
			SP.set(SP1);
		}
		
}


