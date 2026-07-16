package com.zproject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Basetest {
    static Logger logger = LogManager.getLogger(UltimateExampleTest.class);
    @BeforeMethod
    public void start() throws URISyntaxException, MalformedURLException {
    /*
        // 1. Point to the Linux ARM64 ChromeDriver binary location
        // Common paths: "/usr/bin/chromedriver" or "/usr/lib/chromium-browser/chromedriver"
        File driverBinary = new File("/usr/bin/chromedriver");

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(driverBinary)
                .usingAnyFreePort()
                .build();
                */

        // 2. Point to the Chromium Browser instance
        ChromeOptions options = new ChromeOptions();
       // options.setBinary("/usr/bin/chromium-browser");

        // Highly recommended flag configurations for headless environments (Docker/CI)
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        // 3. Initialize the driver using the custom service and options

        //temporary testing remote webdriver to docker compose
        logger.info("instantiating chrome driver");
        WebDriver driver = new RemoteWebDriver(new URI("http://172.19.0.2:4444").toURL(), options);
        logger.info("thread number: {}",  Thread.currentThread().threadId());
        DriverManage.setDriver(driver);
    }
   @AfterMethod
    public void stop(){
        DriverManage.endDriver();
    }


}
