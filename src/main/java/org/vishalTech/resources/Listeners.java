package org.vishalTech.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.vishalTech.BasePackage.Base;
import java.awt.Desktop;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {
    private ExtentReport ExtentReportUtil;
    ExtentReports extent = ExtentReport.extentReportGenerator();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTestThreadLocal= new ThreadLocal<>();

    public Listeners() throws IOException {
    }

    @Override
    public void onTestStart(ITestResult result) {
       test= extent.createTest(result.getMethod().getMethodName()).assignCategory("Regression").assignAuthor("Vishal");
       extentTestThreadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThreadLocal.get().log(Status.PASS, String.valueOf(ExtentColor.GREEN));
    }
    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver = null;
        extentTestThreadLocal.get().fail(result.getThrowable());
        String testMethodName = result.getMethod().getMethodName();
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            System.out.println("Test Failed");
        }

        try {
            extentTestThreadLocal.get().addScreenCaptureFromPath(getScreenshot(testMethodName),
                    result.getMethod().getMethodName());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

    }
}
