package com.saucelabs.cc;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by emmanuel.u on 01/09/2016.
 */
public class FQMovilBuyingTest {


    boolean test_result_ok = false;
    private WebDriver driver;
    private String baseUrl;
    private String browsertype;
    String test = "Pass";
    //  boolean test_result_ok = true;
    String file_name = "";
    boolean contains_roses = false;
    String search_item = "roses";

    @Test
    public void testG1() throws Exception {

        Map<String, String> mobileEmulation = new HashMap<String, String>();
        mobileEmulation.put("deviceName", "Google Nexus 5");

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        WebDriver driver = new RemoteWebDriver(new URL("http://54.93.202.15:4444/wd/hub"), capabilities);
        driver.manage().window().setSize(new Dimension(500, 900));

        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        System.out.println(sessionId);



       // WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(400, 800));
        driver.get("http://www.floraqueen.com/?curr=EUR");


        System.out.println("Maximizing window");


        // click  a product
        driver.findElement(By.xpath("html/body/div[3]/div[1]/section[1]/ul[2]/li[3]/a/div[1]/img[2]")).click();



        // pais
        Select select = new Select(driver.findElement(By.className("countrysel")));
        select.selectByValue("ES");


        // calendario
        driver.findElement(By.className("hasDatepicker")).click();
        WebElement date_grid = driver.findElement(By.className("ui-datepicker-calendar"));
        List<WebElement> dates = date_grid.findElements(By.tagName("a"));
        dates.get(2).click();


        // continuar button
        driver.findElement(By.className("butt")).click();



        /// continue button
        driver.findElement(By.id("submit_order")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // pop up click
        driver.findElement(By.id("upsell-add-btn")).click();

    }

    }







