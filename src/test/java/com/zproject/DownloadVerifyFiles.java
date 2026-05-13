package com.zproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v147.fetch.Fetch;
import org.openqa.selenium.devtools.v147.network.model.ErrorReason;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DownloadVerifyFiles {

    @Test
    public void verifyDownloads() throws InterruptedException {

        Map<String, Object> prefs = new HashMap<>();
        String downloadFilePath = System.getProperty("user.dir");
        // 2. Specify the default download directory
        prefs.put("download.default_directory", downloadFilePath);
        // Keep Safe Browsing on
        prefs.put("safebrowsing.enabled", true);
        // Prevents the "multiple downloads" block prompt
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        // (Optional) Disable the "Save As" popup for a seamless automation flow
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.disable_download_protection", true); // Disable download protection


        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", prefs);
        //allow unverified downloads

        options.addArguments("--disable-features=InsecureDownloadWarnings");
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-web-security");  // optional, use with caution


        ChromeDriver driver = new ChromeDriver(options);

        // block ads
        {
            DevTools devTools = driver.getDevTools();
            devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
            devTools.addListener(Fetch.requestPaused(), request -> {
                if (request.getRequest().getUrl().contains("google") || request.getRequest().getUrl().endsWith(".js")) {
                    devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.BLOCKEDBYCLIENT));
                } else {
                    devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
                }

            });
        }


        driver.get("https://rufus.ie/en/");
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("(//tr/td/a)[1]"))).build().perform();
        actions.click(driver.findElement(By.xpath("(//tr/td/a)[1]"))).build().perform();
        Thread.sleep(5000);
        File file = new File(downloadFilePath + "/rufus-4.14.exe");
        System.out.println(file.exists());
        file.delete();









    }
}
