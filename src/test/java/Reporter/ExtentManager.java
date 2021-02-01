package Reporter;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

  private static ExtentReports extent;

  public synchronized static ExtentReports getReporter(){
      if(extent == null){
          //Set HTML reporting file location
          String workingDir = System.getProperty("user.dir");
          //File config=new File();
          extent = new ExtentReports(workingDir+"\\ExtentReports\\ExtentReportResults.html", true);
          extent.loadConfig(new File(workingDir+"\\config-report.xml"));
      }
      return extent;
  }
}
