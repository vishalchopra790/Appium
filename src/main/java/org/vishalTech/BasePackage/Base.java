package org.vishalTech.BasePackage;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public AppiumDriverLocalService service;
    static AndroidDriver<AndroidElement> driver;


    public AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerIsRunning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    @BeforeTest
    public void killAllNodes() throws InterruptedException, IOException {
        Runtime.getRuntime().exec("taskkill /f /im node.exe");
        Thread.sleep(3000);
    }

    public static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }


    public static String getScreenshot(String s) throws IOException {
       File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String target="target/screenshots/"+s+".png";
       File destination=new File(target);
       FileUtils.copyFile(src,destination);
        return target;
    }

    public static void startEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("C:\\Windows\\System32\\start.bat");
        Thread.sleep(10000);
    }

    public static AndroidDriver<AndroidElement> Capabilities(String appName, String device) throws IOException, InterruptedException {


        FileInputStream fis = new FileInputStream("src/main/java/org/vishalTech/global.properties");
        Properties prop = new Properties();
        prop.load(fis);


        File appdir = new File("src");
        File fs = new File(appdir, (String) prop.get(appName));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String deviceName = (String) prop.get(device);
        if (deviceName.contains("Emu")) {
            startEmulator();
        }

        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        //capabilities.setCapability(MobileCapabilityType.UDID, "28c150440e3f7ece");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        //capabilities.setCapability(AndroidMobileCapabilityType.ti, "uiautomator2");
        //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("chromedriverExecutable", "C:/chromedriver.exe");
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
