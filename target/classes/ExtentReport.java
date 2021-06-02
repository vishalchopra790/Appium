package org.vishalTech.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class ExtentReport {
    static ExtentReports extent;
    static ExtentSparkReporter spark;
    static ExtentSparkReporter failedSpark;
    public static ExtentReports extentReportGenerator() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\test-output\\ExtentReport.html";
        extent = new ExtentReports();
         spark = new ExtentSparkReporter(filePath);
         failedSpark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\FailedExtentReport.html").filter().statusFilter().as(new Status[]{Status.FAIL}).apply();
        final File confJson = new File("src/main/java/org/vishalTech/resources/extent-config.json");
         failedSpark.loadJSONConfig(confJson);

        final File CONF = new File("src/main/java/org/vishalTech/resources/extent-config.xml");

        spark.loadJSONConfig(confJson);
        //spark.config().setReportName("Vishal Mobile Automation");
        extent.attachReporter(spark,failedSpark);

        extent.attachReporter(spark);
        extent.setSystemInfo("Environment","Alpha");

        return extent;
    }
}
