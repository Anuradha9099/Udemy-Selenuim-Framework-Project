package TestComponents;

import Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
/*
explain this what does it do?
it is a listener class that implements the ITestListener interface.
it is used to listen to the test events and report the test results.
it is used to create the extent report.
it is used to add the test results to the extent report.
it is used to add the test results to the extent report.

*/
    ExtentTest extentTest;
    ExtentReports extentReports = ExtentReporterNG.getReportObject();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.fail(result.getThrowable());

        try {
            webDriver = (WebDriver) result.getTestClass().getRealClass().getField("webDriver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //1. take screenshot
        String filePath = null;
        try {
            filePath = getScreenShot(result.getMethod().getMethodName(), webDriver);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }
}
