package org.vishalTech.PageObjects;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.vishalTech.BasePackage.Base;

public class LoginScreen extends Base {
    AndroidDriver<AndroidElement> driver;

    public LoginScreen(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this.driver=driver;
    }

    @AndroidFindBy(xpath = "//android.widget.Spinner")
            public WebElement countryList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement name;

    @AndroidFindBy(xpath = "//*[@text='Female']")
    public WebElement femaleRadio;

    @AndroidFindBy(xpath = "//android.widget.Button")
    public WebElement shopButton;


    public void scrollToCountry(String Country){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+Country+"\"));");
    }

    public void country(String country){
        driver.findElementByXPath("//*[@text='"+country+"']").click();
    }


     public void setName(String Name){
         name.sendKeys(Name);
     }

}
