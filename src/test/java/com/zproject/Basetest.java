package com.zproject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Basetest {
    @BeforeMethod
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        // check if headless or not 
        if (System.getProperty("browser") == null ){
            DriverManage.setDriver(new ChromeDriver());
        }
        else if(System.getProperty("browser").contains("headless")) {
            DriverManage.setDriver(new ChromeDriver(options));
        }
    }
   @AfterMethod
    public void stop(){
        DriverManage.endDriver();
    }
}
