package com.zproject;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
public class Listener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        if(DriverManage.getDriver() != null){

            byte[] screenshot =  ((TakesScreenshot) DriverManage.getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(result.getName() + "_On_Start_Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
    }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if(DriverManage.getDriver() != null) {

            byte[] screenshot2 = ((TakesScreenshot) DriverManage.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(result.getName() + "_Failure_Screenshot", "image/png", new ByteArrayInputStream(screenshot2), ".png");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if(DriverManage.getDriver() != null){
            byte[] screenshot2 =  ((TakesScreenshot) DriverManage.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("_Finish_Screenshot", "image/png", new ByteArrayInputStream(screenshot2), ".png");
        }
    }
}
