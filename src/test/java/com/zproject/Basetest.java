package com.zproject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Basetest {
    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver", "/home/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        DriverManage.setDriver(new ChromeDriver(options));
    }
   @AfterMethod
    public void stop(){
        DriverManage.endDriver();
    }
}
