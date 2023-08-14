package api.utilities;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	
	public  ExtentReports extent;
	public  ExtentSparkReporter report;
	public  ExtentTest test;
	

	public  void onStart(ITestContext testContext) {
		
		// File Name formatting of your Report
		String reportPath = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String time = formatter.format(date);
		System.out.println(time);
		String newTimeFormat = time.replaceAll("/", "_").replaceAll(" ", "_").replaceAll(":", "_");
		System.out.println(newTimeFormat);
		reportPath = ".\\reports\\" +"Reqres-Report"+ "_" + newTimeFormat + ".html";
		System.out.println(reportPath);

		// Declaration of Extent Classs
		report = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();

		
			report.config().setReportName("Reqres API");
			report.config().setDocumentTitle("RestAssuredAutomationProject");
			extent.setSystemInfo("Orange App Version", "4.8");
		
		extent.setSystemInfo("Environment" ,"Production");
		extent.setSystemInfo("Author", "Mohd Sufiyan");
		extent.setSystemInfo("Java Version", "JDK 8");
		report.config().setTheme(Theme.DARK);
		report.config().setEncoding("utf-8");
		extent.attachReporter(report);
		//return extent;
	}
	
	public void onTestPass(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}


}
