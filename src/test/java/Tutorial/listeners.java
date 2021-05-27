package Tutorial;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.base;
import resources.extentReportsNG;

public class listeners extends base implements ITestListener {
	ExtentTest test;
	ExtentReports extent = extentReportsNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "It looks good");
		

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//Screenshot
		
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		try {
			
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		}

		catch (Exception e) {

		}

		try {
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(testMethodName, driver), result.getMethod().getMethodName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		extent.flush();
		
	}

}
