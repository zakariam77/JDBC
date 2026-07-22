package com.zproject;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
public class Listener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

       byte[] src =  ((TakesScreenshot) DriverManage.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("screenshot_fail", new ByteArrayInputStream(src));

    }
}
