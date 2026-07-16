package com.zproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManage {


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void setDriver(WebDriver driverInstance){
        driver.set(driverInstance);
    }
    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void endDriver(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
