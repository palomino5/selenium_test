package com.saucelabs.cc;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class FQMovilSearchTest {


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

        //  WebDriver driver = new FirefoxDriver();
        driver.get("http://www.floraqueen.com/?curr=EUR");
      //  driver.manage().window().setSize(new Dimension(400, 800));

        System.out.println("Maximizing window");
        System.out.println("The Search bar Test");

        //WebDriverWait wait = new WebDriverWait(driver, 30); //you can play with the time integer  to wait for longer than 15 seconds.`
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toggle-search"))); //if you want to wait for a particular title to show up



        driver.findElement(By.xpath("html/body/div[3]/div[1]/section[1]/ul[2]/li[2]/a/img")).click();

        WebElement element = driver.findElement(By.className("toggle-search"));

        Actions actions = new Actions(driver);

        actions.moveToElement(element).click().perform();

        // click on the search bar
       //  driver.findElement(By.className("toggle-search")).click();///


        /// search bar
        WebElement search = driver.findElement(By.id("q_mobile"));
        search.sendKeys(this.search_item);
        search.submit();

        List<WebElement> catalogue = driver.findElements(By.className("info"));


        for (WebElement e : catalogue) {
            //System.out.println(e.getText().contains(this.search_item));
            //System.out.println(e.getText());
            //  this.contains_roses = e.getText().contains(this.search_item);

            if (e.getText().contains(this.search_item)) {
                this.contains_roses = e.getText().contains(this.search_item);


                System.out.println("found roses");

            }
        }
    }


    public void asserttrue1() throws MessagingException, IOException {


        try {
            org.junit.Assert.assertTrue("TEST OK", this.contains_roses == true);
            // Assert.assertTrue(this.contains_roses);
            this.test_result_ok = true;
        } catch (Exception e) {
            System.out.println("NO VA");
        }
    }


}
