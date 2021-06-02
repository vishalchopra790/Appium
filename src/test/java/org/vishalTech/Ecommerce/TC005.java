package org.vishalTech.Ecommerce;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.vishalTech.BasePackage.Base;
import org.vishalTech.PageObjects.LoginScreen;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;


public class TC005 extends Base {

    @Test
    public void Test005() throws IOException, InterruptedException {
        service = startServer();
        AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp", "device");
        LoginScreen ls = new LoginScreen(driver);
        ls.countryList.click();
        ls.scrollToCountry("Argentina");
        ls.country("Argentina");
        ls.setName("Vishal");
        driver.hideKeyboard();
        ls.femaleRadio.click();
        ls.shopButton.click();
        /*String toast= driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
        System.out.println(toast);*/
        //Assert.assertEquals("Please enter your nam",toast);
        driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
        driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();

        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
        Thread.sleep(5000);
        double sum = 0;
        int count = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
        for (int i = 0; i < count; i++) {
            String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
            double amount = getAmount(amount1);
            sum = sum + amount;
        }


        System.out.println(sum);
        String total = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
        total = total.substring(1);
        double totalval = Double.parseDouble(total);
        System.out.println(totalval);
        Assert.assertEquals(sum, totalval, 0.0);
        WebElement d = driver.findElementByClassName("android.widget.CheckBox");
        TouchAction tap = new TouchAction(driver);
        tap.tap(TapOptions.tapOptions().withElement(ElementOption.element(d))).perform();

        WebElement terms = driver.findElementById("com.androidsample.generalstore:id/termsButton");
        tap.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(terms)).withDuration(Duration.ofSeconds(2))).perform();
        driver.findElementById("android:id/button1").click();
        driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
        Thread.sleep(5000);

        Set<String> context = driver.getContextHandles();
        for (String contextName : context) {
            System.out.println(contextName);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");

        System.out.println(driver.getTitle());
        driver.findElementByXPath("//*[@name='q']").click();
        driver.findElementByXPath("//*[@name='q']").sendKeys("facebook");
        driver.findElementByXPath("//*[@name='q']").sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
        driver.findElementByXPath("//*[@text='Female']").click();
        service.stop();
    }

    public static double getAmount(String value) {
        value = value.substring(1);
        double amount = Double.parseDouble(value);
        return amount;
    }

}
