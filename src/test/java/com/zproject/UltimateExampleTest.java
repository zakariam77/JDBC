package com.zproject;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UltimateExampleTest extends Basetest{

    @Test(dataProvider = "dataProvider")
    public void test(String name, String password) {

        DriverManage.getDriver().get("https://celebsarea.com/neo");
        logger.info("logging in with username: {}", name);
        DriverManage.getDriver().findElement(By.id("user_login")).sendKeys(name);
        DriverManage.getDriver().findElement(By.id("user_pass")).sendKeys(password);

    }

    @DataProvider(name = "dataProvider" , parallel = true)
    public Iterator<Object[]> dataProvider() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/newdb";
        String rootUsername = "ziko";
        String rootPassword = "Azerty----@2";

        logger.info("connecting to mysql server url: {}", url);
        Connection conn = DriverManager.getConnection(url, rootUsername, rootPassword);
        logger.info("mysql server connection success");
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select username, passwords from info");
        List<Object[]> dataList = new ArrayList<>();

        while (rs.next()){
            String username = rs.getString("username");
            String password = rs.getString("passwords");
            dataList.add(new Object[]{username, password});
        }

        conn.close();
        rs.close();
        statement.close();

        return dataList.iterator();
    }
}























/*
    @DataProvider(name = "data")
    public Iterator<Object[]> data() throws SQLException {

        List<Object[]> datalist = new ArrayList<>();

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc", "root", "root");

            String query = "SELECT username, passwords FROM info";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Object[]> data = new ArrayList<>();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("passwords");

                Object[] row = new Object[2];
                row[0] = username;
                row[1] = password;

                data.add(row);
            }

            rs.close();
            stmt.close();
            conn.close();

            return data.iterator();
        }
}





 */
