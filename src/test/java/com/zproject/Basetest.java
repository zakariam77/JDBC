package com.zproject;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;

public class Basetest {

    @BeforeMethod
    public void start(){

        // 1. Point to the Linux ARM64 ChromeDriver binary location
        // Common paths: "/usr/bin/chromedriver" or "/usr/lib/chromium-browser/chromedriver"
        File driverBinary = new File("/usr/bin/chromedriver");

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(driverBinary)
                .usingAnyFreePort()
                .build();

        // 2. Point to the Chromium Browser instance
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium-browser");

        // Highly recommended flag configurations for headless environments (Docker/CI)
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // 3. Initialize the driver using the custom service and options
        DriverManage.setDriver(new ChromeDriver(service, options));
    }
   @AfterMethod
    public void stop(){
        DriverManage.endDriver();
    }


}
