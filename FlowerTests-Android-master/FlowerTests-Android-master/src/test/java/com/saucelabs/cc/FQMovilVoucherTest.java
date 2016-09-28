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
public class FQMovilVoucherTest {


    String subject_text;
    double expected_result = 15;
    double real_result = 16;
    String file_name = "";
    boolean test_result_ok = false;
    private WebDriver driver;
    private String baseUrl;
    private String browsertype;

    @Test
    public void testG1() throws Exception {

        /*DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");*/
/*DesiredCapabilities capability = DesiredCapabilities.chrome();
capability.setBrowserName("chrome");*/
        /*capability.setPlatform(Platform.WIN8_1);
        capability.setCapability("screen-resolution", "400x800");*/

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

     //   WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setSize(new Dimension(400, 800));
        driver.get("http://www.floraqueen.com/?curr=EUR");

        System.out.println("Maximizing window");

        driver.findElement(By.xpath("html/body/div[3]/div[1]/section[1]/ul[2]/li[2]/a/img")).click();


        // pais
        Select select = new Select(driver.findElement(By.className("countrysel")));
        select.selectByValue("ES");


        //Coupon
        driver.findElement(By.className("promo-link")).click();
        WebElement coupon = driver.findElement(By.id("pc"));
        coupon.sendKeys("AZAE2FXTJ");
        driver.findElement(By.id("promocode")).click();


        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // calendario
        driver.findElement(By.className("hasDatepicker")).click();
        WebElement date_grid = driver.findElement(By.className("ui-datepicker-calendar"));
        List<WebElement> dates = date_grid.findElements(By.tagName("a"));
        dates.get(3).click();
        // driver.findElement(By.id("76fe76c9808d17d820e11331a237a6a4")).click();
        WebElement active = driver.findElement(By.className("sizes-items")).findElement(By.className("active"));
        WebElement price = active.findElement(By.className("price"));
        java.lang.String price_value = price.getText();
        System.out.println(price_value);

        WebElement disc = active.findElement(By.className("before"));
        java.lang.String disc_value = disc.getText();
        System.out.println(disc_value);

        java.lang.String new_value = disc_value.replaceAll("[^\\d.]", "");
        int foo = Integer.parseInt(new_value);

        java.lang.String cool_value = price_value.replaceAll("[^\\d.]", "");
        int roo = Integer.parseInt(cool_value);

        Float Z = (float) foo - roo;
        // Float per = (float) Z / foo;
        //double new_percent = Math.ceil(per * 100);

        Float real_result = Float.valueOf(Z * 1);
        System.out.println(real_result);
        //System.out.println(Math.round(Math.round(real_result * 100)));{

        this.real_result = Math.ceil(real_result);
        System.out.println("-----");
        System.out.println(this.real_result);

    }


    @After
    public void asserttrue1() throws MessagingException, IOException {

        try {
            Assert.assertTrue("Test OK", this.expected_result == this.real_result);
            this.test_result_ok = true;
            //this.sendEmail("TEST OK");
        } catch (Exception e) {
            System.out.println("NO VA");

        }

    }
}


