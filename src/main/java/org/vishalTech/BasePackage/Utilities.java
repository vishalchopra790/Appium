package org.vishalTech.BasePackage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import javafx.scene.layout.AnchorPane;

public class Utilities {
AndroidDriver<AndroidElement> driver;
    public Utilities(AndroidDriver<AndroidElement> driver) {
        this.driver=driver;
    }

    public void scrollToElement(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }
}
