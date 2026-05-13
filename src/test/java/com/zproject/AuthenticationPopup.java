package com.zproject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AuthenticationPopup {
        @Test
        public  void popup() throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.get("https:admin:vinothqa@vinothqaacademy.com/basic-auth-demo/");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".demo-link")).click();

    }
}
