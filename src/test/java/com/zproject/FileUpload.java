package com.zproject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class FileUpload {


    @Test
    public void fileUpload() throws IOException, InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.ilovepdf.com/pdf_to_jpg");
        driver.findElement(By.id("pickfiles")).sendKeys("C:\\Users\\car\\Downloads\\SeleniumGrid.pdf");



        //Thread.sleep(3000);
        //Runtime.getRuntime().exec("C:\\Users\\car\\Desktop\\uploadFile.exe");



        // not working
        // sendkeys is preferred in real work




    }

}