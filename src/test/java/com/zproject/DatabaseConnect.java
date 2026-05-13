package com.zproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.Duration;

public class DatabaseConnect {


    @Test
    public void JDBC() throws SQLException, InterruptedException {
       String host = "localhost";
       String port = "3306";
       String dbName = "jdbc";
       String username = "";
       String password = "";

        Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbName, "root", "root");
           Statement statement = conn.createStatement();
           ResultSet rs =  statement.executeQuery("select * from info where id = 5");
           if (rs.next()){
               System.out.println(rs);
               username = rs.getString("username");
               password = rs.getString("passwords");

           }


            WebDriver driver = new ChromeDriver();
           driver.get("https://rahulshettyacademy.com/client");

           driver.findElement(By.id("userEmail")).sendKeys(username);
           driver.findElement(By.id("userPassword")).sendKeys(password);
            driver.findElement(By.id("login")).click();
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
           wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".invalid-feedback")));
           Thread.sleep(2000);
           driver.quit();
    }

}