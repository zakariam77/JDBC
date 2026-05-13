package com.zproject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Basetest {
    @BeforeMethod
    public void start(){
        DriverManage.setDriver(new ChromeDriver());
    }
   @AfterMethod
    public void stop(){
        DriverManage.endDriver();
    }
}
