package com.zproject;

import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManage {


    private static ThreadLocal<ChromeDriver> driver = new ThreadLocal<>();


    public static void setDriver(ChromeDriver driverInstance){
        driver.set(driverInstance);
    }
    public static ChromeDriver getDriver(){
        return driver.get();
    }
    public static void endDriver(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}
