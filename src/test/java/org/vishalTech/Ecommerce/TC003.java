package org.vishalTech.Ecommerce;


import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.vishalTech.BasePackage.Base;
import org.vishalTech.PageObjects.LoginScreen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class TC003 extends Base {

    public static void main(String[] args) throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp", "device");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LoginScreen ls=new LoginScreen(driver);
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



        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId" +
                "(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"LeBron Soldier 12 \").instance(0))"));

        int count=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for(int i=0;i<count;i++){
           String text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
           if(text.equalsIgnoreCase("LeBron Soldier 12 ") ||text.equalsIgnoreCase("Jordan Lift Off ")){
               driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();

           }
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

        /*String finalText=driver.findElementById("com.androidsample.generalstore:id/productName").getText();
        Assert.assertEquals("LeBron Soldier 12 ",finalText);*/

    }
}
