package org.vishalTech.Ecommerce;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.vishalTech.BasePackage.Base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class TC002 extends Base {

    public static void main(String[] args) throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver =Capabilities("","");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByXPath("//android.widget.Spinner").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
        driver.findElementByXPath("//*[@text='Argentina']").click();
        //driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("hello");
        driver.hideKeyboard();
        driver.findElementByXPath("//*[@text='Female']").click();
        driver.findElementByXPath("//android.widget.Button").click();
        String toast= driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
        System.out.println(toast);
        Assert.assertEquals("Please enter your nam",toast);


    }
}
