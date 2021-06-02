package org.vishalTech.Ecommerce;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import org.vishalTech.BasePackage.Base;
import org.vishalTech.PageObjects.LoginScreen;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TC001 extends Base {

    @Test
    public void Test001() throws IOException, InterruptedException {
        service = startServer();
        AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp", "device");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoginScreen ls = new LoginScreen(driver);
        ls.countryList.click();
        ls.scrollToCountry("Argentina");
        ls.country("Argentina");
        ls.setName("Vishal");
        driver.hideKeyboard();
        ls.femaleRadio.click();
        ls.shopButton.click();
        service.stop();


    }
}
