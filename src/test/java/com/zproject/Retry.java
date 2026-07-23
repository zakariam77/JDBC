package com.zproject;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int run = 0;
    int max = 1;
    @Override
    public boolean retry(ITestResult result) {
        if (run < max){
            run ++;
            return true;
        }
        else {
            return false;
        }
    }
}
